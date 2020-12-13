package socialite.async.util;

import java.nio.ByteBuffer;

public class PrepareSendTable {
    private int workID;
    private ByteBuffer buffer;
    public PrepareSendTable(){
        workID=-1;
        buffer=null;
    }

    public int getWorkID() {
        return workID;
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    public void setWorkID(int workID) {
        this.workID = workID;
    }
}
