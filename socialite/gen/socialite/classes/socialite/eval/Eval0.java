package socialite.eval;

import java.lang.reflect.Constructor;
import java.util.List;

import socialite.engine.Config;
import socialite.codegen.RuleComp;
import socialite.codegen.Epoch;

import socialite.parser.Rule;
import socialite.tables.*;
import socialite.resource.*;
import socialite.util.Assert;
import socialite.util.SociaLiteException;

import socialite.eval.Manager;

public class Eval0 extends EvalDist {
	Edge_int_int_nest1_indexby0_range0to875713_groupby0_id0[] _edge= new Edge_int_int_nest1_indexby0_range0to875713_groupby0_id0[sliceMap.sliceNum(0)];
	Nodes_int_indexby0_range0to875713_groupby0_id1[] _nodes= new Nodes_int_indexby0_range0to875713_groupby0_id1[sliceMap.sliceNum(1)];
	Comp_int_int_indexby0_range0to875713_groupby0_id2[] _comp= new Comp_int_int_indexby0_range0to875713_groupby0_id2[sliceMap.sliceNum(2)];
	CompIDs_int_groupby0_id3[] _compIDs= new CompIDs_int_groupby0_id3[sliceMap.sliceNum(3)];

	public Eval0(SRuntime _runtime, Epoch _epoch, Config _conf) {
		super(_runtime, _epoch, _conf);
		try {
			for(int $i=0; $i<sliceMap.sliceNum(0); $i++) {
			    _edge[$i]=Edge_int_int_nest1_indexby0_range0to875713_groupby0_id0.create(sliceMap.localBeginIndex(0), sliceMap.localSize(0));
			};
			for(int $i=0; $i<sliceMap.sliceNum(1); $i++) {
			    _nodes[$i]=Nodes_int_indexby0_range0to875713_groupby0_id1.create(sliceMap.localBeginIndex(1), sliceMap.localSize(1));
			};
			for(int $i=0; $i<sliceMap.sliceNum(2); $i++) {
			    _comp[$i]=Comp_int_int_indexby0_range0to875713_groupby0_id2.create(sliceMap.localBeginIndex(2), sliceMap.localSize(2));
			};
			for(int $i=0; $i<sliceMap.sliceNum(3); $i++) {
			    _compIDs[$i]=CompIDs_int_groupby0_id3.create();
			};
			tableRegistry.setTableInstArray(0, _edge);
			tableRegistry.setTableInstArray(1, _nodes);
			tableRegistry.setTableInstArray(2, _comp);
			tableRegistry.setTableInstArray(3, _compIDs);
;
		} catch (Throwable t) {
			EvalParallel.L.fatal("Exception while table instantiation:"+t.getMessage());
			throw new SociaLiteException(t.getMessage());
		}
	}

	public void init() {
		super.init();

;		
	}

	//public void run() { super.run(); }	
	public void finish() { 
		super.finish();
		_edge = null;
		_nodes = null;
		_comp = null;
		_compIDs = null;

	}
}