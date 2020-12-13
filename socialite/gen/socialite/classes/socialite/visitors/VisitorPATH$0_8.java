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
import socialite.tables.Remote_PATH$0_6_int_int_groupby0_id1;
import socialite.tables.PATH$0_int_int_indexby0_range0to875713_groupby0_id1;


public final class VisitorPATH$0_8 extends VisitorImpl implements Runnable {
    private Remote_PATH$0_6_int_int_groupby0_id1 $_remote_PATH$0_60;
    private PATH$0_int_int_indexby0_range0to875713_groupby0_id1 $headTable;
    private SRuntime $runtime;
    private final int $epochId;
    private final int $ruleId;
    private TableInstRegistry $registry;
    private TableSliceMap $sliceMap;
    private LockMap $lockMap;
    private int $0$priv;
    private int $1$priv;
    private int aggr$ret;
    private int $aggrVar;
    private int $currentPredicate;
    private int $firstTableIdx;
    private int $userlogCount;
;
    public  VisitorPATH$0_8(int _$epochId, int _$ruleId, Remote_PATH$0_6_int_int_groupby0_id1 _$$_remote_PATH$0_60, PATH$0_int_int_indexby0_range0to875713_groupby0_id1 $$headTable, SRuntime _$runtime, int _$firstTableIdx) {
        $_remote_PATH$0_60 = _$$_remote_PATH$0_60;
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
          $_remote_PATH$0_60.iterate(this);
        } while (false);;
    }

    public boolean visit(int _0, int _1) {
        do {
          switch ($currentPredicate) {
              case 0: 
              {$0$priv=(int)_0;
              $1$priv=(int)_1;
              boolean $isUpdated=false;
              PATH$0_int_int_indexby0_range0to875713_groupby0_id1 _$$headTable=$headTable;
              if (_$$headTable.contains($0$priv)) {
                  int _$oldAns;
                  _$oldAns=_$$headTable.groupby($0$priv);
                  aggr$ret=Builtin.min(_$oldAns, $1$priv) ;
                  if (_$oldAns!=0 && _$oldAns==aggr$ret) continue;
              };
              int $_slice_10=$sliceMap.getRangeIndex(1,0,$0$priv);
              try {
                  $lockMap.lock(1, $_slice_10);
                  if (!_$$headTable.contains($0$priv)) {
                      $aggrVar=$1$priv; ;
                      _$$headTable.insert($0$priv, $aggrVar);
                      $isUpdated=true;
                  } else {
                      {int _$oldAns;
                      _$oldAns=_$$headTable.groupby($0$priv);
                      $aggrVar=Builtin.min(_$oldAns, $1$priv) ;
                      if($aggrVar==(_$oldAns)) continue;
                      $isUpdated=_$$headTable.update($0$priv, $aggrVar);};
                  };
              } finally {
                  $lockMap.unlock(1, $_slice_10);
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
        String str="PATH$0($0$priv,$Builtin.min($1$priv)) :- Remote_PATH$0_6($0$priv,$1$priv). epoch:"+$epochId;
        return str;
    }

}