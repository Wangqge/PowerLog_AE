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
import socialite.tables.Remote_Nodes_1_int_groupby0_id1;
import socialite.tables.Nodes_int_indexby0_range0to875713_groupby0_id1;


public final class VisitorNodes_4 extends VisitorImpl implements Runnable {
    private Remote_Nodes_1_int_groupby0_id1 $_remote_Nodes_10;
    private Nodes_int_indexby0_range0to875713_groupby0_id1 $headTable;
    private SRuntime $runtime;
    private final int $epochId;
    private final int $ruleId;
    private TableInstRegistry $registry;
    private TableSliceMap $sliceMap;
    private LockMap $lockMap;
    private int $0$priv;
    private int $currentPredicate;
    private int $firstTableIdx;
    private int $userlogCount;
;
    public  VisitorNodes_4(int _$epochId, int _$ruleId, Remote_Nodes_1_int_groupby0_id1 _$$_remote_Nodes_10, Nodes_int_indexby0_range0to875713_groupby0_id1 $$headTable, SRuntime _$runtime, int _$firstTableIdx) {
        $_remote_Nodes_10 = _$$_remote_Nodes_10;
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
          $_remote_Nodes_10.iterate(this);
        } while (false);;
    }

    public boolean visit(int _0) {
        do {
          switch ($currentPredicate) {
              case 0: 
              {$0$priv=(int)_0;
              boolean $isUpdated=false;
              Nodes_int_indexby0_range0to875713_groupby0_id1 _$$headTable=$headTable;
              int $_slice_8=$sliceMap.getRangeIndex(1,0,$0$priv);
              try {
                  $lockMap.lock(1, $_slice_8);
                  $isUpdated=_$$headTable.insert($0$priv);
                  if ($isUpdated) {
                      {;};
                  };
              } finally {
                  $lockMap.unlock(1, $_slice_8);
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
        String str="Nodes($0$priv) :- Remote_Nodes_1($0$priv). epoch:"+$epochId;
        return str;
    }

}