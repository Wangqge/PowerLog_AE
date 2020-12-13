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
import socialite.tables.Rank$0_int_double_indexby0_range0to875713_groupby0_id3;
import socialite.tables.EdgeCnt_int_int_indexby0_range0to875713_groupby0_id1;
import socialite.tables.Edge_int_int_nest1_indexby0_range0to875713_groupby0_id0;
import socialite.tables.Rank$1_int_double_indexby0_range0to875713_groupby0_id4;


public final class VisitorRank$1_6 extends VisitorImpl implements Runnable {
    private Rank$0_int_double_indexby0_range0to875713_groupby0_id3 $_rank$00;
    private EdgeCnt_int_int_indexby0_range0to875713_groupby0_id1 $_edgeCnt1;
    private Edge_int_int_nest1_indexby0_range0to875713_groupby0_id0 $_edge3;
    private Rank$1_int_double_indexby0_range0to875713_groupby0_id4 $headTable;
    private SRuntime $runtime;
    private final int $epochId;
    private final int $ruleId;
    private TableInstRegistry $registry;
    private TableSliceMap $sliceMap;
    private LockMap $lockMap;
    private final double $const12;
    private int s;
    private double r1;
    private int cnt;
    private double r;
    private int n;
    private double aggr$ret;
    private double $aggrVar;
    private int $currentPredicate;
    private int $firstTableIdx;
    private int $userlogCount;
    private Remote_Rank$1_6_int_double_groupby0_id4[] remoteTable_head=new Remote_Rank$1_6_int_double_groupby0_id4[2];
;
    public  VisitorRank$1_6(int _$epochId, int _$ruleId, Double _$$const12, Rank$0_int_double_indexby0_range0to875713_groupby0_id3 _$$_rank$00, EdgeCnt_int_int_indexby0_range0to875713_groupby0_id1 _$$_edgeCnt1, Edge_int_int_nest1_indexby0_range0to875713_groupby0_id0 _$$_edge3, Rank$1_int_double_indexby0_range0to875713_groupby0_id4 $$headTable, SRuntime _$runtime, int _$firstTableIdx) {
        $const12=_$$const12;
        $_rank$00 = _$$_rank$00;
        $_edgeCnt1 = _$$_edgeCnt1;
        $_edge3 = _$$_edge3;
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
          $_rank$00.iterate_range_0($sliceMap.getRange(3,0,$firstTableIdx)[0],$sliceMap.getRange(3,0,$firstTableIdx)[1],this);
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

    public boolean visit(int _0, double _1) {
        do {
          switch ($currentPredicate) {
              case 0: 
              {s=(int)_0;
              r1=(double)_1;
              $currentPredicate=1;
              $_edgeCnt1.iterate_by_0(s, this);
              $currentPredicate=0;
              ;
              break;
              }

          };
        } while (false);;
        return true;
    }

    public boolean visit(int _0, int _1) {
        do {
          switch ($currentPredicate) {
              case 1: 
              {if (s!=_0) {
                  break;
              };
              cnt=(int)_1;
              {r=(double)(($const12*r1) /cnt) ;};
              $currentPredicate=3;
              $_edge3.iterate_by_0(s, this);
              $currentPredicate=1;
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
              case 3: 
              {if (s!=_0) {
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
              case 3: 
              {n=(int)_0;
              boolean $isUpdated=false;
              if ($sliceMap.isLocal(4,n)) {
                  {Rank$1_int_double_indexby0_range0to875713_groupby0_id4 _$$headTable=$headTable;
                  int $_slice_9=$sliceMap.getRangeIndex(4,0,n);
                  try {
                      $lockMap.lock(4, $_slice_9);
                      if (!_$$headTable.contains(n)) {
                          $aggrVar=r; ;
                          _$$headTable.insert(n, $aggrVar);
                          $isUpdated=true;
                      } else {
                          {double _$oldAns;
                          _$oldAns=_$$headTable.groupby(n);
                          $aggrVar=Builtin.sum(_$oldAns, r) ;
                          if($aggrVar==(_$oldAns)) continue;
                          $isUpdated=_$$headTable.update(n, $aggrVar);};
                      };
                  } finally {
                      $lockMap.unlock(4, $_slice_9);
                  };
                  if ($isUpdated) {
                      {;};
                  };};
              } else {
                  Remote_Rank$1_6_int_double_groupby0_id4 _$remoteT;
                  int _$machineIdx=$sliceMap.machineIndexFor(4,n);
                  _$remoteT=getRemoteTable_head(n);
                  {_$remoteT.insert(n, r);};
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

    public Remote_Rank$1_6_int_double_groupby0_id4 getRemoteTable_head(int _$rangeOrHash) {
        int _$machineIdx=$sliceMap.machineIndexFor(4, _$rangeOrHash);
        Remote_Rank$1_6_int_double_groupby0_id4 _$remoteT=remoteTable_head[_$machineIdx];
        if (_$remoteT==null) {
            _$remoteT=(Remote_Rank$1_6_int_double_groupby0_id4)TmpTablePool.get(worker.id(),Remote_Rank$1_6_int_double_groupby0_id4.class);
            remoteTable_head[_$machineIdx]=_$remoteT;
        };
        return _$remoteT;
    }

    public void nullifyRemoteTable_head(int _$machineIdx) {
        Remote_Rank$1_6_int_double_groupby0_id4 _$remoteT=remoteTable_head[_$machineIdx];
        assert _$remoteT!=null;
        remoteTable_head[_$machineIdx]=null;
    }

    public TableInst[] getDeltaTables() {
        return null;
    }

    public String toString() {
        String str="Rank$1(n,$Builtin.sum(r)) :- Rank$0(s,r1),EdgeCnt(s,cnt),r=((0.8*r1)/cnt),Edge(s,n). epoch:"+$epochId;
        return str;
    }

}