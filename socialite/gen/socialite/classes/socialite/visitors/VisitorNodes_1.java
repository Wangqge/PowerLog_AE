package socialite.visitors;
/* 
 
*/
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.map.hash.TDoubleObjectHashMap;
import gnu.trove.TIntCollection;
import gnu.trove.TDoubleCollection;
import gnu.trove.iterator.TIntIterator;
import gnu.trove.iterator.TDoubleIterator;
import gnu.trove.list.array.TIntArrayList;
import gnu.trove.list.array.TDoubleArrayList;
import gnu.trove.list.array.TDoubleArrayList;
import gnu.trove.map.hash.TLongObjectHashMap;
import gnu.trove.iterator.TLongObjectIterator;

import org.apache.commons.lang3.exception.ExceptionUtils;

import socialite.collection.*;

import socialite.resource.*;
import socialite.tables.*;
import socialite.functions.Math;
import socialite.functions.*;
import socialite.visitors.*;
import java.util.Iterator;
import java.util.Collection;
import java.util.List;

import socialite.resource.RuleMap;
import socialite.eval.EvalWithTable;
import socialite.dist.msg.WorkerMessage;

import socialite.eval.TmpTablePool;
import socialite.eval.Worker;
import socialite.eval.DeltaStepWindow;

import socialite.type.*;
import socialite.util.HashCode;
import socialite.util.Assert;
import socialite.util.SociaLiteException;
import socialite.util.SocialiteFinishEval;
import socialite.util.UserLog;

import org.python.core.PyTuple;
import org.python.core.PyList;
import org.python.core.PySequenceList;
import socialite.tables.Nodes_int_indexby0_range0to875713_groupby0_id1;


public final class VisitorNodes_1 extends VisitorImpl implements Runnable {
    private Nodes_int_indexby0_range0to875713_groupby0_id1 $headTable;
    private SRuntime $runtime;
    private final int $epochId;
    private final int $ruleId;
    private TableInstRegistry $registry;
    private TableSliceMap $sliceMap;
    private LockMap $lockMap;
    private final java.lang.String $const0;
    private java.lang.String l;
    private int n;
    private int $currentPredicate;
    private int $firstTableIdx;
    private int $userlogCount;
    private Remote_Nodes_1_int_groupby0_id1[] remoteTable_head=new Remote_Nodes_1_int_groupby0_id1[2];
;
    public  VisitorNodes_1(int _$epochId, int _$ruleId, String _$$const0, Nodes_int_indexby0_range0to875713_groupby0_id1 $$headTable, SRuntime _$runtime, int _$firstTableIdx) {
        $const0=_$$const0;
        $headTable=$$headTable;
        $epochId=_$epochId;
        $ruleId=_$ruleId;
        $runtime=_$runtime;
        $sliceMap = _$runtime.getSliceMap();
        $lockMap = _$runtime.getLockMap();
        $registry= _$runtime.getTableRegistry();
        $firstTableIdx = _$firstTableIdx;
        $currentPredicate=0;
    }

    public int getRuleId() {
        return $ruleId;
    }

    public int getEpochId() {
        return $epochId;
    }

    public void run() {
        do {
          ;
          try {
              Iterator $iter5 = Builtin.read($const0) ;
              while ($iter5.hasNext()) {
              	java.lang.String $v6 = (java.lang.String)$iter5.next();
              	l=(java.lang.String)$v6;
              	;
              	try {
              	    {n=Builtin.toInt(l) ;};
              	    boolean $isUpdated=false;
              	    if ($sliceMap.isLocal(1,n)) {
              	        {Nodes_int_indexby0_range0to875713_groupby0_id1 _$$headTable=$headTable;
              	        int $_slice_7=$sliceMap.getRangeIndex(1,0,n);
              	        try {
              	            $lockMap.lock(1, $_slice_7);
              	            $isUpdated=_$$headTable.insert(n);
              	            if ($isUpdated) {
              	                {;};
              	            };
              	        } finally {
              	            $lockMap.unlock(1, $_slice_7);
              	        };
              	        if ($isUpdated) {
              	            {;};
              	        };};
              	    } else {
              	        Remote_Nodes_1_int_groupby0_id1 _$remoteT;
              	        int _$machineIdx=$sliceMap.machineIndexFor(1,n);
              	        _$remoteT=getRemoteTable_head(n);
              	        {_$remoteT.insert(n);};
              	        {if (_$remoteT.vacancy()==0) {
              	            SRuntime _$rt=SRuntimeWorker.getInst();
              	            RuleMap _$rm=_$rt.getRuleMap(getRuleId());
              	            int _$depRuleId=_$rm.getRemoteHeadDep(getRuleId());
              	            EvalWithTable _$cmd=new EvalWithTable($epochId, _$depRuleId,_$remoteT,0);
              	            boolean _$reuse = _$rt.sender().send(_$machineIdx,_$cmd);
              	            if(!_$reuse)nullifyRemoteTable_head(_$machineIdx);
              	        };};
              	    };
              	} catch(Exception _$e) {
              	    if(_$e instanceof SociaLiteException) throw (SociaLiteException)_$e;
              	    VisitorImpl.L.error(ExceptionUtils.getStackTrace(_$e));
              	    throw new SociaLiteException("Error while invoking $Builtin.toInt("+"\""+l+"\""+"), "+_$e+""+", "+_$e);
              	} finally {
              	;
              	};
              };
          } catch(Exception _$e) {
              if(_$e instanceof SociaLiteException) throw (SociaLiteException)_$e;
              VisitorImpl.L.error(ExceptionUtils.getStackTrace(_$e));
              throw new SociaLiteException("Error while invoking $Builtin.read("+"\""+$const0+"\""+"), "+_$e+""+", "+_$e);
          } finally {
          ;
          };
        } while (false);;
        for(int _$i=0; _$i<remoteTable_head.length; _$i++) {
            TmpTableInst _$t=remoteTable_head[_$i];
            if (_$t!=null && _$t.size()>0) {
                SRuntime _$rt=SRuntimeWorker.getInst();
                RuleMap _$rm=_$rt.getRuleMap(getRuleId());
                int _$depRuleId=_$rm.getRemoteHeadDep(getRuleId());
                EvalWithTable _$cmd=new EvalWithTable($epochId, _$depRuleId,_$t,0);
                boolean _$reuse = _$rt.sender().send(_$i,_$cmd);
                if(!_$reuse)nullifyRemoteTable_head(_$i);
            };
        };
        TmpTablePool.free(worker.id(),remoteTable_head);
    }

    public Remote_Nodes_1_int_groupby0_id1 getRemoteTable_head(int _$rangeOrHash) {
        int _$machineIdx=$sliceMap.machineIndexFor(1, _$rangeOrHash);
        Remote_Nodes_1_int_groupby0_id1 _$remoteT=remoteTable_head[_$machineIdx];
        if (_$remoteT==null) {
            _$remoteT=(Remote_Nodes_1_int_groupby0_id1)TmpTablePool.get(worker.id(),Remote_Nodes_1_int_groupby0_id1.class);
            remoteTable_head[_$machineIdx]=_$remoteT;
        };
        return _$remoteT;
    }

    public void nullifyRemoteTable_head(int _$machineIdx) {
        Remote_Nodes_1_int_groupby0_id1 _$remoteT=remoteTable_head[_$machineIdx];
        assert _$remoteT!=null;
        remoteTable_head[_$machineIdx]=null;
    }

    public TableInst[] getDeltaTables() {
        return null;
    }

    public String toString() {
        String str="Nodes(n) :- l=$Builtin.read(\"hdfs://master:9000/experiment/google/node.txt\"),n=$Builtin.toInt(l). epoch:"+$epochId;
        return str;
    }

}