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
import socialite.tables.Remote_Rank$1_6_int_double_groupby0_id4;
import socialite.tables.Rank$1_int_double_indexby0_range0to875713_groupby0_id4;


public final class VisitorRank$1_7 extends VisitorImpl implements Runnable {
    private Remote_Rank$1_6_int_double_groupby0_id4 $_remote_Rank$1_60;
    private Rank$1_int_double_indexby0_range0to875713_groupby0_id4 $headTable;
    private SRuntime $runtime;
    private final int $epochId;
    private final int $ruleId;
    private TableInstRegistry $registry;
    private TableSliceMap $sliceMap;
    private LockMap $lockMap;
    private int $0$priv;
    private double $1$priv;
    private double aggr$ret;
    private double $aggrVar;
    private int $currentPredicate;
    private int $firstTableIdx;
    private int $userlogCount;
;
    public  VisitorRank$1_7(int _$epochId, int _$ruleId, Remote_Rank$1_6_int_double_groupby0_id4 _$$_remote_Rank$1_60, Rank$1_int_double_indexby0_range0to875713_groupby0_id4 $$headTable, SRuntime _$runtime, int _$firstTableIdx) {
        $_remote_Rank$1_60 = _$$_remote_Rank$1_60;
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
          $_remote_Rank$1_60.iterate(this);
        } while (false);;
    }

    public boolean visit(int _0, double _1) {
        do {
          switch ($currentPredicate) {
              case 0: 
              {$0$priv=(int)_0;
              $1$priv=(double)_1;
              boolean $isUpdated=false;
              Rank$1_int_double_indexby0_range0to875713_groupby0_id4 _$$headTable=$headTable;
              int $_slice_10=$sliceMap.getRangeIndex(4,0,$0$priv);
              try {
                  $lockMap.lock(4, $_slice_10);
                  if (!_$$headTable.contains($0$priv)) {
                      $aggrVar=$1$priv; ;
                      _$$headTable.insert($0$priv, $aggrVar);
                      $isUpdated=true;
                  } else {
                      {double _$oldAns;
                      _$oldAns=_$$headTable.groupby($0$priv);
                      $aggrVar=Builtin.sum(_$oldAns, $1$priv) ;
                      if($aggrVar==(_$oldAns)) continue;
                      $isUpdated=_$$headTable.update($0$priv, $aggrVar);};
                  };
              } finally {
                  $lockMap.unlock(4, $_slice_10);
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
        String str="Rank$1($0$priv,$Builtin.sum($1$priv)) :- Remote_Rank$1_6($0$priv,$1$priv). epoch:"+$epochId;
        return str;
    }

}