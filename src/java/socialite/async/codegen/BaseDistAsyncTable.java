package socialite.async.codegen;


import socialite.async.AsyncConfig;
import socialite.async.test.MessageTable;
import socialite.async.util.SerializeTool;
import socialite.resource.DistTablePartitionMap;
import socialite.visitors.VisitorImpl;
import socialite.yarn.ClusterConf;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import socialite.async.util.PrepareSendTable;

import socialite.async.util.NetworkThread;
import socialite.async.util.NetworkUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicIntegerArray;

public abstract class BaseDistAsyncTable extends BaseAsyncTable {
    private AtomicIntegerArray messageTableSelector;
    private MessageTableBase[][] messageTableListPair;
    private MessageTableBase[] messageTableList;

    protected final int workerNum;
    protected final int myWorkerId;
    protected final DistTablePartitionMap partitionMap;
    protected final int tableIdForIndex;
    protected final int messageTableUpdateThreshold;
    protected final int initSize;

    public BaseDistAsyncTable(Class<?> messageTableClass, DistTablePartitionMap partitionMap, int tableIdForIndex) {
        this.workerNum = ClusterConf.get().getNumWorkers();
        this.myWorkerId = ClusterConf.get().getRank() - 1;
        this.partitionMap = partitionMap;
        this.tableIdForIndex = tableIdForIndex;
        this.messageTableUpdateThreshold = AsyncConfig.get().getMessageTableUpdateThreshold();
        this.initSize = AsyncConfig.get().getInitSize();

        if (AsyncConfig.get().isMVCC()) {
            messageTableSelector = new AtomicIntegerArray(workerNum);
            messageTableListPair = new MessageTableBase[workerNum][2];
            try {
                Constructor constructor = messageTableClass.getConstructor();

                for (int wid = 0; wid < workerNum; wid++) {
                    if (wid == myWorkerId) continue;//for worker i, it have 0,1,...,i-1,null,i+1,...n-1 buffer table
                    messageTableListPair[wid][0] = (MessageTableBase) constructor.newInstance();
                    messageTableListPair[wid][1] = (MessageTableBase) constructor.newInstance();
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        } else {
            messageTableList = new MessageTableBase[workerNum];
            try {
                Constructor constructor = messageTableClass.getConstructor();

                for (int wid = 0; wid < workerNum; wid++) {
                    if (wid == myWorkerId) continue;//for worker i, it have 0,1,...,i-1,null,i+1,...n-1 buffer table
                    messageTableList[wid] = (MessageTableBase) constructor.newInstance();
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public MessageTableBase[][] getMessageTableListPair() {
        return messageTableListPair;
    }

    public MessageTableBase[] getMessageTableList() {
        return messageTableList;
    }


    public MessageTableBase getWritableMessageTable(int workerId) {
        if (AsyncConfig.get().isMVCC())
            return messageTableListPair[workerId][messageTableSelector.get(workerId)];
        else
            return messageTableList[workerId];
    }


    public  int getsize(int sendToWorkerId){
        MessageTableBase sendableMessageTable = messageTableList[sendToWorkerId];
        return  sendableMessageTable.size();

    }
    public MessageTableBase[] getMessageTable(){
        return messageTableList;
    }


    public ByteBuffer getSendableMessageTableByteBuffer(int sendToWorkerId, SerializeTool serializeTool) throws InterruptedException {
        MessageTableBase sendableMessageTable = messageTableList[sendToWorkerId];
        // MessageTableBase tmptable=  messageTableList[sendToWorkerId];
        long startTime = System.currentTimeMillis();
        ByteBuffer buffer=null;
        //in sync mode, all computing thread write to message table when barrier is triggered, so we don't have to wait
        if (AsyncConfig.get().getEngineType() == AsyncConfig.EngineType.ASYNC) {
            while (sendableMessageTable.size() < messageTableUpdateThreshold) {
                Thread.sleep(10);
                if ((System.currentTimeMillis() - startTime) >= AsyncConfig.get().getMessageTableWaitingInterval())
                    break;
            }
        }
        // sleep to ensure switched, this is important
        // even though selector is atomic type, but computing thread cannot see the switched result immediately, i don't know why :(
        //System.out.println(sendToWorkerId+"ssss"+sendableMessageTable.size());
        synchronized (sendableMessageTable) {
            buffer = serializeTool.toByteBuffer(4096 + sendableMessageTable.size() * (8 +8+ 8), sendableMessageTable);
            sendableMessageTable.resetDelta();
        }
        // buffer = serializeTool.toByteBuffer(1024 + sendableMessageTable.size() * (8 +4+ 8), sendableMessageTable);
        return buffer;
    }

    public ByteBuffer getByteBuffer(MessageTableBase messageTable, SerializeTool serializeTool) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ByteBuffer buffer=null;
//        if (AsyncConfig.get().getEngineType() == AsyncConfig.EngineType.ASYNC) {
//            while (messageTable.size() < messageTableUpdateThreshold) {
//                Thread.sleep(10);
//                if ((System.currentTimeMillis() - startTime) >= AsyncConfig.get().getMessageTableWaitingInterval())
//                    break;
//            }
//        }
        System.out.println(messageTable.size());
        synchronized (messageTable) {
            buffer = serializeTool.toByteBuffer(4096 + messageTable.size() * (8 +8+ 8), messageTable);
            messageTable.resetDelta();
        }
        return buffer;
    }

    /**
     * this method is inefficient, although MVCC will avoid lock, but will cause "invalid information" flushing the network.
     *
     * @param sendToWorkerId
     * @param serializeTool
     * @return
     * @throws InterruptedException
     */
    @Deprecated
    public ByteBuffer getSendableMessageTableByteBufferMVCC(int sendToWorkerId, SerializeTool serializeTool) throws InterruptedException {
        int writingTableInd = messageTableSelector.get(sendToWorkerId);//获取计算线程正在写入的表序号
        MessageTableBase sendableMessageTable = messageTableListPair[sendToWorkerId][writingTableInd];
        long startTime = System.currentTimeMillis();
        //in sync mode, all computing thread write to message table when barrier is triggered, so we don't have to wait
        if (AsyncConfig.get().getEngineType() == AsyncConfig.EngineType.ASYNC) {
            while (sendableMessageTable.size() < messageTableUpdateThreshold) {
                Thread.sleep(1);
                if ((System.currentTimeMillis() - startTime) >= AsyncConfig.get().getMessageTableWaitingInterval())
                    break;
            }
        }
        messageTableSelector.set(sendToWorkerId, writingTableInd == 0 ? 1 : 0);
        // sleep to ensure switched, this is important
        // even though selector is atomic type, but computing thread cannot see the switched result immediately, i don't know why :(
        int lastSize;
        do {
            lastSize = sendableMessageTable.size();
            Thread.sleep(10);
        } while (sendableMessageTable.size() != lastSize);
        ByteBuffer buffer = serializeTool.toByteBuffer(4096 + sendableMessageTable.size() * (8 + 8 + 8), sendableMessageTable);
        sendableMessageTable.resetDelta();
        return buffer;
    }

    public abstract void applyBuffer(MessageTableBase messageTable);

    public VisitorImpl getEdgeVisitor() {
        throw new NotImplementedException();
    }

    public VisitorImpl getInitVisitor() {
        throw new NotImplementedException();
    }
}
