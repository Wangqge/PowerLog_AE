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
import socialite.tables.Node_int_indexby0_range0to875713_groupby0_id2;
import socialite.tables.Rank$1_int_double_indexby0_range0to875713_groupby0_id4;


public final class VisitorRank$1_5 extends VisitorImpl implements Runnable {
    private Node_int_indexby0_range0to875713_groupby0_id2 $_node0;
    private Rank$1_int_double_indexby0_range0to875713_groupby0_id4 $headTable;
    private SRuntime $runtime;
    private final int $epochId;
    private final int $ruleId;
    private TableInstRegistry $registry;
    private TableSliceMap $sliceMap;
    private LockMap $lockMap;
    private final double $const3;
    private final int $const4;
    private int n;
    private double r;
    private double aggr$ret;
    private double $aggrVar;
    private int $currentPredicate;
    private int $firstTableIdx;
    private int $userlogCount;
;
    public  VisitorRank$1_5(int _$epochId, int _$ruleId, Double _$$const3, Integer _$$const4, Node_int_indexby0_range0to875713_groupby0_id2 _$$_node0, Rank$1_int_double_indexby0_range0to875713_groupby0_id4 $$headTable, SRuntime _$runtime, int _$firstTableIdx) {
        $const3=_$$const3;
        $const4=_$$const4;
        $_node0 = _$$_node0;
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
          $_node0.iterate_range_0($sliceMap.getRange(2,0,$firstTableIdx)[0],$sliceMap.getRange(2,0,$firstTableIdx)[1],this);
        } while (false);;
    }

    public boolean visit(int _0) {
        do {
          switch ($currentPredicate) {
              case 0: 
              {n=(int)_0;
              {r=(double)($const3/$const4) ;};
              boolean $isUpdated=false;
              Rank$1_int_double_indexby0_range0to875713_groupby0_id4 _$$headTable=$headTable;
              int $_slice_8=$sliceMap.getRangeIndex(4,0,n);
              try {
                  $lockMap.lock(4, $_slice_8);
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
                  $lockMap.unlock(4, $_slice_8);
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
        String str="Rank$1(n,$Builtin.sum(r)) :- Node(n),r=(0.2/875713). epoch:"+$epochId;
        return str;
    }

}