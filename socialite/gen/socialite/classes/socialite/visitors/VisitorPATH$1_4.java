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
import socialite.tables.Remote_PATH$1_2_int_int_groupby0_id2;
import socialite.tables.PATH$1_int_int_indexby0_range0to875713_groupby0_id2;


public final class VisitorPATH$1_4 extends VisitorImpl implements Runnable {
    private Remote_PATH$1_2_int_int_groupby0_id2 $_remote_PATH$1_20;
    private PATH$1_int_int_indexby0_range0to875713_groupby0_id2 $headTable;
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
    public  VisitorPATH$1_4(int _$epochId, int _$ruleId, Remote_PATH$1_2_int_int_groupby0_id2 _$$_remote_PATH$1_20, PATH$1_int_int_indexby0_range0to875713_groupby0_id2 $$headTable, SRuntime _$runtime, int _$firstTableIdx) {
        $_remote_PATH$1_20 = _$$_remote_PATH$1_20;
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
          $_remote_PATH$1_20.iterate(this);
        } while (false);;
    }

    public boolean visit(int _0, int _1) {
        do {
          switch ($currentPredicate) {
              case 0: 
              {$0$priv=(int)_0;
              $1$priv=(int)_1;
              boolean $isUpdated=false;
              PATH$1_int_int_indexby0_range0to875713_groupby0_id2 _$$headTable=$headTable;
              if (_$$headTable.contains($0$priv)) {
                  int _$oldAns;
                  _$oldAns=_$$headTable.groupby($0$priv);
                  aggr$ret=Builtin.min(_$oldAns, $1$priv) ;
                  if (_$oldAns!=0 && _$oldAns==aggr$ret) continue;
              };
              int $_slice_6=$sliceMap.getRangeIndex(2,0,$0$priv);
              try {
                  $lockMap.lock(2, $_slice_6);
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
                  $lockMap.unlock(2, $_slice_6);
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
        String str="PATH$1($0$priv,$Builtin.min($1$priv)) :- Remote_PATH$1_2($0$priv,$1$priv). epoch:"+$epochId;
        return str;
    }

}