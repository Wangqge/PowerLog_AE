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
import socialite.tables.EdgeCnt_int_int_indexby0_range0to875713_groupby0_id1;


public final class VisitorEdgeCnt_1 extends VisitorImpl implements Runnable {
    private Edge_int_int_nest1_indexby0_range0to875713_groupby0_id0 $_edge0;
    private EdgeCnt_int_int_indexby0_range0to875713_groupby0_id1 $headTable;
    private SRuntime $runtime;
    private final int $epochId;
    private final int $ruleId;
    private TableInstRegistry $registry;
    private TableSliceMap $sliceMap;
    private LockMap $lockMap;
    private final int $const0;
    private int s;
    private int t;
    private int aggr$ret;
    private int $aggrVar;
    private int $currentPredicate;
    private int $firstTableIdx;
    private int $userlogCount;
;
    public  VisitorEdgeCnt_1(int _$epochId, int _$ruleId, Integer _$$const0, Edge_int_int_nest1_indexby0_range0to875713_groupby0_id0 _$$_edge0, EdgeCnt_int_int_indexby0_range0to875713_groupby0_id1 $$headTable, SRuntime _$runtime, int _$firstTableIdx) {
        $const0=_$$const0;
        $_edge0 = _$$_edge0;
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
          $_edge0.iterate_range_0($sliceMap.getRange(0,0,$firstTableIdx)[0],$sliceMap.getRange(0,0,$firstTableIdx)[1],this);
        } while (false);;
    }

    public boolean visit_0(int _0) {
        do {
          switch ($currentPredicate) {
              case 0: 
              {s=(int)_0;
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
              case 0: 
              {t=(int)_0;
              boolean $isUpdated=false;
              EdgeCnt_int_int_indexby0_range0to875713_groupby0_id1 _$$headTable=$headTable;
              int $_slice_5=$sliceMap.getRangeIndex(1,0,s);
              try {
                  $lockMap.lock(1, $_slice_5);
                  if (!_$$headTable.contains(s)) {
                      $aggrVar=$const0; ;
                      _$$headTable.insert(s, $aggrVar);
                      $isUpdated=true;
                  } else {
                      {int _$oldAns;
                      _$oldAns=_$$headTable.groupby(s);
                      $aggrVar=Builtin.inc(_$oldAns, $const0) ;
                      if($aggrVar==(_$oldAns)) continue;
                      $isUpdated=_$$headTable.update(s, $aggrVar);};
                  };
              } finally {
                  $lockMap.unlock(1, $_slice_5);
              };
              if ($isUpdated) {
                  {;};
              };
              ;
              break;
              }

          };
        } while (false);;
        return true;
    }

    public TableInst[] getDeltaTables() {
        return null;
    }

    public String toString() {
        String str="EdgeCnt(s,$Builtin.inc(1)) :- Edge(s,t). epoch:"+$epochId;
        return str;
    }

}