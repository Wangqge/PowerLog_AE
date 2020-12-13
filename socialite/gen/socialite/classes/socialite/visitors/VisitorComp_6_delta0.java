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
import socialite.tables.Delta_Comp_int_int_groupby0_id2;
import socialite.tables.Edge_int_int_nest1_indexby0_range0to875713_groupby0_id0;
import socialite.tables.Comp_int_int_indexby0_range0to875713_groupby0_id2;


public final class VisitorComp_6_delta0 extends VisitorImpl implements Runnable {
    private Delta_Comp_int_int_groupby0_id2 $_delta_Comp0;
    private Edge_int_int_nest1_indexby0_range0to875713_groupby0_id0 $_edge1;
    private Comp_int_int_indexby0_range0to875713_groupby0_id2 $headTable;
    private Delta_Comp_int_int_groupby0_id2 delta$Comp;
    private Delta_Comp_int_int_groupby0_id2 ret$delta$Comp;
    private SRuntime $runtime;
    private final int $epochId;
    private final int $ruleId;
    private TableInstRegistry $registry;
    private TableSliceMap $sliceMap;
    private LockMap $lockMap;
    private int p;
    private int i;
    private int n;
    private int aggr$ret;
    private int $aggrVar;
    private int $currentPredicate;
    private int $firstTableIdx;
    private int $userlogCount;
    private RemoteBodyTable_6_1_int_int_groupby0_id9[] remoteTable_1=new RemoteBodyTable_6_1_int_int_groupby0_id9[2];
    private Remote_Comp_6_int_int_groupby0_id2[] remoteTable_head=new Remote_Comp_6_int_int_groupby0_id2[2];
;
    public  VisitorComp_6_delta0(int _$epochId, int _$ruleId, Delta_Comp_int_int_groupby0_id2 _$$_delta_Comp0, Edge_int_int_nest1_indexby0_range0to875713_groupby0_id0 _$$_edge1, Comp_int_int_indexby0_range0to875713_groupby0_id2 $$headTable, SRuntime _$runtime, int _$firstTableIdx) {
        $_delta_Comp0 = _$$_delta_Comp0;
        $_edge1 = _$$_edge1;
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
          $currentPredicate=0;
          if ($_delta_Comp0.virtualSliceNum()>1) {
              $_delta_Comp0.iterate_range((($_delta_Comp0.size()+$_delta_Comp0.virtualSliceNum()-1)/$_delta_Comp0.virtualSliceNum())*$firstTableIdx,(($_delta_Comp0.size()+$_delta_Comp0.virtualSliceNum()-1)/$_delta_Comp0.virtualSliceNum())*($firstTableIdx+1)-1,this);
          } else {
              $_delta_Comp0.iterate(this);
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
        for(int _$i=0; _$i<remoteTable_1.length; _$i++) {
            TmpTableInst _$t=remoteTable_1[_$i];
            if (_$t!=null && _$t.size()>0) {
                SRuntime _$rt=SRuntimeWorker.getInst();
                RuleMap _$rm=_$rt.getRuleMap(getRuleId());
                int _$depRuleId=_$rm.getRemoteBodyDep(getRuleId(),1);
                EvalWithTable _$cmd=new EvalWithTable($epochId,_$depRuleId,_$t,0);
                boolean _$reuse = _$rt.sender().send(_$i, _$cmd);
                if(!_$reuse)nullifyRemoteTable_1(_$i);
            };
        };
        TmpTablePool.free(worker.id(),remoteTable_head);
        TmpTablePool.free(worker.id(),remoteTable_1);
    }

    public boolean visit(int _0, int _1) {
        do {
          switch ($currentPredicate) {
              case 0: 
              {p=(int)_0;
              i=(int)_1;
              if ($sliceMap.isLocal(0, p)) {
                  {$currentPredicate=1;
                  $_edge1.iterate_by_0(p, this);};
              } else {
                  RemoteBodyTable_6_1_int_int_groupby0_id9 _$remoteT=getRemoteTable_1(p);
                  _$remoteT.insert(p, i);
                  {if (_$remoteT.size()==_$remoteT.capacity()) {
                      SRuntime _$rt=SRuntimeWorker.getInst();
                      RuleMap _$rm=_$rt.getRuleMap(getRuleId());
                      int _$depRuleId=_$rm.getRemoteBodyDep(getRuleId(),1);
                      EvalWithTable _$cmd=new EvalWithTable($epochId,_$depRuleId,_$remoteT,0);
                      boolean _$reuse = _$rt.sender().send($sliceMap.machineIndexFor(0,p), _$cmd);
                      if(!_$reuse)nullifyRemoteTable_1($sliceMap.machineIndexFor(0,p));
                  };};
              };
              $currentPredicate=0;
              ;
              break;
              }

          };
        } while (false);;
        return true;
    }

    public boolean visit_0(int _0) {
        do {
          switch ($currentPredicate) {
              case 1: 
              {if (p!=_0) {
                  return false;
              };
              ;
              break;
              }

          };
        } while (false);;
        return true;
    }

    public boolean visit(int _0) {
        do {
          switch ($currentPredicate) {
              case 1: 
              {n=(int)_0;
              boolean $isUpdated=false;
              if ($sliceMap.isLocal(2,n)) {
                  {Comp_int_int_indexby0_range0to875713_groupby0_id2 _$$headTable=$headTable;
                  if (_$$headTable.contains(n)) {
                      int _$oldAns;
                      _$oldAns=_$$headTable.groupby(n);
                      aggr$ret=Builtin.min(_$oldAns, i) ;
                      if (_$oldAns!=0 && _$oldAns==aggr$ret) continue;
                  };
                  int $_slice_11=$sliceMap.getRangeIndex(2,0,n);
                  try {
                      $lockMap.lock(2, $_slice_11);
                      if (!_$$headTable.contains(n)) {
                          $aggrVar=i; ;
                          _$$headTable.insert(n, $aggrVar);
                          $isUpdated=true;
                      } else {
                          {int _$oldAns;
                          _$oldAns=_$$headTable.groupby(n);
                          $aggrVar=Builtin.min(_$oldAns, i) ;
                          if($aggrVar==(_$oldAns)) continue;
                          $isUpdated=_$$headTable.update(n, $aggrVar);};
                      };
                  } finally {
                      $lockMap.unlock(2, $_slice_11);
                  };
                  if ($isUpdated) {
                      {getDeltaTable().update(n, $aggrVar);};
                  };};
              } else {
                  Remote_Comp_6_int_int_groupby0_id2 _$remoteT;
                  int _$machineIdx=$sliceMap.machineIndexFor(2,n);
                  _$remoteT=getRemoteTable_head(n);
                  {_$remoteT.insert(n, i);};
                  {if (_$remoteT.vacancy()==0) {
                      SRuntime _$rt=SRuntimeWorker.getInst();
                      RuleMap _$rm=_$rt.getRuleMap(getRuleId());
                      int _$depRuleId=_$rm.getRemoteHeadDep(getRuleId());
                      EvalWithTable _$cmd=new EvalWithTable($epochId, _$depRuleId,_$remoteT,0);
                      boolean _$reuse = _$rt.sender().send(_$machineIdx,_$cmd);
                      if(!_$reuse)nullifyRemoteTable_head(_$machineIdx);
                  };};
              };
              ;
              break;
              }

          };
        } while (false);;
        return true;
    }
    /* generated by genGetRemoteBodyTable() */
    public RemoteBodyTable_6_1_int_int_groupby0_id9 getRemoteTable_1(int rangeOrHash) {
        int _$machineIdx=$sliceMap.machineIndexFor(0, rangeOrHash);
        RemoteBodyTable_6_1_int_int_groupby0_id9 _$remoteT=remoteTable_1[_$machineIdx];
        if (_$remoteT==null) {
            _$remoteT=(RemoteBodyTable_6_1_int_int_groupby0_id9)TmpTablePool._get(worker.id(),RemoteBodyTable_6_1_int_int_groupby0_id9.class);
            remoteTable_1[_$machineIdx]=_$remoteT;
        };
        return _$remoteT;
    }

    public void nullifyRemoteTable_1(int machineIdx) {
        remoteTable_1[machineIdx]=null;
    }

    public Remote_Comp_6_int_int_groupby0_id2 getRemoteTable_head(int _$rangeOrHash) {
        int _$machineIdx=$sliceMap.machineIndexFor(2, _$rangeOrHash);
        Remote_Comp_6_int_int_groupby0_id2 _$remoteT=remoteTable_head[_$machineIdx];
        if (_$remoteT==null) {
            _$remoteT=(Remote_Comp_6_int_int_groupby0_id2)TmpTablePool._get(worker.id(),Remote_Comp_6_int_int_groupby0_id2.class);
            remoteTable_head[_$machineIdx]=_$remoteT;
        };
        return _$remoteT;
    }

    public void nullifyRemoteTable_head(int _$machineIdx) {
        Remote_Comp_6_int_int_groupby0_id2 _$remoteT=remoteTable_head[_$machineIdx];
        assert _$remoteT!=null;
        remoteTable_head[_$machineIdx]=null;
    }

    public TableInst[] getDeltaTables() {
        return new TableInst[]{ret$delta$Comp};
    }

    public Delta_Comp_int_int_groupby0_id2 getDeltaTable() {
        if (delta$Comp==null) {
            delta$Comp=(Delta_Comp_int_int_groupby0_id2)TmpTablePool._get(getWorkerId(),Delta_Comp_int_int_groupby0_id2.class,0);
            ret$delta$Comp=delta$Comp.isEmpty()?delta$Comp:null;
        };
        if (delta$Comp.vacancy()==0) {
            if(ret$delta$Comp!=null) getWorker().addTasksForDelta(getRuleId(),delta$Comp, 0);
            delta$Comp=(Delta_Comp_int_int_groupby0_id2)TmpTablePool._get(getWorkerId(),Delta_Comp_int_int_groupby0_id2.class,0);
            ret$delta$Comp=delta$Comp.isEmpty()?delta$Comp:null;
        };
        return delta$Comp;
    }

    public String toString() {
        String str="Comp(n,$Builtin.min(i)) :- Delta_Comp(p,i),Edge(p,n). epoch:"+$epochId;
        return str;
    }

}