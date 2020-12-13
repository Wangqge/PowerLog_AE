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
import socialite.tables.Edge_int_int_nest1_indexby0_range0to875713_groupby0_id0;


public final class VisitorEdge_0 extends VisitorImpl implements Runnable {
    private Edge_int_int_nest1_indexby0_range0to875713_groupby0_id0 $headTable;
    private SRuntime $runtime;
    private final int $epochId;
    private final int $ruleId;
    private TableInstRegistry $registry;
    private TableSliceMap $sliceMap;
    private LockMap $lockMap;
    private final java.lang.String $const0;
    private final java.lang.String $const1;
    private java.lang.String l;
    private java.lang.String s1;
    private java.lang.String s2;
    private int s;
    private int t;
    private int $currentPredicate;
    private int $firstTableIdx;
    private int $userlogCount;
    private Remote_Edge_0_int_int_groupby0_id0[] remoteTable_head=new Remote_Edge_0_int_int_groupby0_id0[2];
;
    public  VisitorEdge_0(int _$epochId, int _$ruleId, String _$$const0, String _$$const1, Edge_int_int_nest1_indexby0_range0to875713_groupby0_id0 $$headTable, SRuntime _$runtime, int _$firstTableIdx) {
        $const0=_$$const0;
        $const1=_$$const1;
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
              Iterator $iter0 = Builtin.read($const0) ;
              while ($iter0.hasNext()) {
              	java.lang.String $v1 = (java.lang.String)$iter0.next();
              	l=(java.lang.String)$v1;
              	;
              	try {
              	    {Object[] $array2=(Object[])Builtin.split(l, $const1) ;
              	    s1=(java.lang.String)$array2[0];
              	    s2=(java.lang.String)$array2[1];};
              	    ;
              	    try {
              	        {s=Builtin.toInt(s1) ;};
              	        ;
              	        try {
              	            {t=Builtin.toInt(s2) ;};
              	            boolean $isUpdated=false;
              	            if ($sliceMap.isLocal(0,s)) {
              	                {Edge_int_int_nest1_indexby0_range0to875713_groupby0_id0 _$$headTable=$headTable;
              	                int $_slice_3=$sliceMap.getRangeIndex(0,0,s);
              	                try {
              	                    $lockMap.lock(0, $_slice_3);
              	                    $isUpdated=_$$headTable.insert(s, t);
              	                    if ($isUpdated) {
              	                        {;};
              	                    };
              	                } finally {
              	                    $lockMap.unlock(0, $_slice_3);
              	                };
              	                if ($isUpdated) {
              	                    {;};
              	                };};
              	            } else {
              	                Remote_Edge_0_int_int_groupby0_id0 _$remoteT;
              	                int _$machineIdx=$sliceMap.machineIndexFor(0,s);
              	                _$remoteT=getRemoteTable_head(s);
              	                {_$remoteT.insert(s, t);};
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
              	            throw new SociaLiteException("Error while invoking $Builtin.toInt("+"\""+s2+"\""+"), "+_$e+""+", "+_$e);
              	        } finally {
              	        ;
              	        };
              	    } catch(Exception _$e) {
              	        if(_$e instanceof SociaLiteException) throw (SociaLiteException)_$e;
              	        VisitorImpl.L.error(ExceptionUtils.getStackTrace(_$e));
              	        throw new SociaLiteException("Error while invoking $Builtin.toInt("+"\""+s1+"\""+"), "+_$e+""+", "+_$e);
              	    } finally {
              	    ;
              	    };
              	} catch(Exception _$e) {
              	    if(_$e instanceof SociaLiteException) throw (SociaLiteException)_$e;
              	    VisitorImpl.L.error(ExceptionUtils.getStackTrace(_$e));
              	    throw new SociaLiteException("Error while invoking $Builtin.split("+"\""+l+"\""+","+"\""+$const1+"\""+"), "+_$e+""+", "+_$e);
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

    public Remote_Edge_0_int_int_groupby0_id0 getRemoteTable_head(int _$rangeOrHash) {
        int _$machineIdx=$sliceMap.machineIndexFor(0, _$rangeOrHash);
        Remote_Edge_0_int_int_groupby0_id0 _$remoteT=remoteTable_head[_$machineIdx];
        if (_$remoteT==null) {
            _$remoteT=(Remote_Edge_0_int_int_groupby0_id0)TmpTablePool.get(worker.id(),Remote_Edge_0_int_int_groupby0_id0.class);
            remoteTable_head[_$machineIdx]=_$remoteT;
        };
        return _$remoteT;
    }

    public void nullifyRemoteTable_head(int _$machineIdx) {
        Remote_Edge_0_int_int_groupby0_id0 _$remoteT=remoteTable_head[_$machineIdx];
        assert _$remoteT!=null;
        remoteTable_head[_$machineIdx]=null;
    }

    public TableInst[] getDeltaTables() {
        return null;
    }

    public String toString() {
        String str="Edge(s,t) :- l=$Builtin.read(\"hdfs://master:9000/experiment/google/edge.txt\"),(s1, s2)=$Builtin.split(l, \"\t\"),s=$Builtin.toInt(s1),t=$Builtin.toInt(s2). epoch:"+$epochId;
        return str;
    }

}