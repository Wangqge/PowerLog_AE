package socialite.tables;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Arrays;
import gnu.trove.iterator.TIntObjectIterator;
import gnu.trove.iterator.TDoubleObjectIterator;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.map.hash.TDoubleObjectHashMap;
import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;
import gnu.trove.list.array.TLongArrayList;
import gnu.trove.list.array.TFloatArrayList;
import gnu.trove.list.array.TDoubleArrayList;
import gnu.trove.TIntCollection;
import gnu.trove.TDoubleCollection;
import socialite.eval.Manager;
import socialite.visitors.IntVisitor;
import socialite.visitors.ObjectVisitor;
import socialite.visitors.VisitorImpl;
import socialite.util.Assert;
import socialite.util.SociaLiteException;
import socialite.collection.SIntArrayList;
import socialite.collection.SLongArrayList;
import socialite.collection.SFloatArrayList;
import socialite.collection.SDoubleArrayList;
import socialite.collection.SIntConstArrayList;
import socialite.collection.SDoubleConstArrayList;
import socialite.collection.SArrayList;
import socialite.collection.SCollection;
import socialite.collection.SCollectionTmpl;
import socialite.collection.TupleVisitor;
import socialite.eval.TmpTablePool;
import socialite.util.concurrent.ConcurrentSoftQueue;
import socialite.util.concurrent.ConcurrentWeakQueue;
import socialite.resource.TableSliceMap;
import socialite.resource.DistTableSliceMap;
import socialite.type.*;
import socialite.util.ByteBufferPool;

import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.IOException;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// ArrayTable
public class Rank$1_int_double_indexby0_range0to875713_groupby0_id4 extends AbstractTableInst  {
	private static final long serialVersionUID = 1;

	public static int tableid() { return 4; }

	public static Rank$1_int_double_indexby0_range0to875713_groupby0_id4 create() { return new Rank$1_int_double_indexby0_range0to875713_groupby0_id4(0, 875714); }
	public static Rank$1_int_double_indexby0_range0to875713_groupby0_id4 create(int base, int size) { return new Rank$1_int_double_indexby0_range0to875713_groupby0_id4(base, size); }
	public static Rank$1_int_double_indexby0_range0to875713_groupby0_id4 create(Integer base, Integer size) { return new Rank$1_int_double_indexby0_range0to875713_groupby0_id4(base.intValue(), size.intValue()); }
	int base;
	socialite.util.concurrent.NonAtomicByteArray filled;
	double col1[];


 boolean isEmpty = true;

	public Rank$1_int_double_indexby0_range0to875713_groupby0_id4() { }
	public Rank$1_int_double_indexby0_range0to875713_groupby0_id4(int _base, int size) {
		base = _base;
		filled = new socialite.util.concurrent.NonAtomicByteArray(size);
		col1=new double[size];
;
	}

private double col1(int i) { return col1[i]; }


	public int size() { throw new UnsupportedOperationException(); }
	public boolean isEmpty() { return isEmpty; }
	public int id() { return 4; }
	public String name() { return "Rank$1"; }

	public void init(double init1) {
		for (int i=0; i<filled.length(); i++) {
		    filled.set(i, (byte)1);
			col1[i]=init1;
;
		}
	}

	public void clearQuick() { clear(); }

	public void clear() {
	    filled.fill((byte)0);
		isEmpty = true;
	}

	public void clear(int from, int to) {
		from -= base;
		to -= base;
	    filled.fill(from-(from%4), to+4, (byte)0);
		isEmpty = true;
	}

	public int virtualSliceNum() {
		Assert.not_supported();
		return 0; // this should not be called!	
	}

	public int totalAllocSize() {
		int totalAlloc = 8+filled.length();
		totalAlloc += 12+4*filled.length();
		totalAlloc += 12+8*filled.length();

		return totalAlloc;
	}
	void rangeCheck(int i) {
		if (i<0 || i>=filled.length()) {
			throw new SociaLiteException("Value out of range for Rank$1 0'th column: "+(i+base));
		}
	}
	public boolean contains(int a0, double a1) {
		int i=(int)(a0-base); rangeCheck(i);
		if (filled.get(i)!=1) return false;

		if (true&&(col1(i)==(a1)))
			return true;
		return false;
	}
	public boolean contains(int a0, double a1, boolean[] dontcare) {
		if (dontcare[0]) {
			for (int i=0; i<filled.length(); i++) {
				if (filled.get(i)!=1) continue;
				if (true&&(dontcare[1] || col1(i)==(a1)))
					return true;
			}
			return false;
		}
		int i=(int)(a0-base); rangeCheck(i);
		if (filled.get(i)!=1) return false;

		if (true&&(dontcare[1] || col1(i)==(a1)))
			return true;
		return false;
	}

	public boolean insert(int a0, double a1) {
        int i=(int)(a0-base); rangeCheck(i);
		if (filled.get(i)==1) {
			if (contains(a0, a1)) return false;

			String msg = "Cannot insert the tuple {"+a0+","+a1 +"} to Rank$1\n";
	 		msg += "Rank$1 already contains {"+a0+","+col1(i) +"} with the same 1st (array index) column\n";
	 		msg += "To insert the tuple, declare Rank$1 with nested columns.\n";
	 		throw new SociaLiteException(msg);
		}
		if (isEmpty) isEmpty = false;

		col1[i] = a1;

		filled.unsafeSet(i, (byte)1);
		return true;
    }

/*
    public void insertAtomic(Iterator<Tuple> iterator) {
        Tuple_int_double tuple;
        while (iterator.hasNext()) {
            tuple = (Tuple_int_double)iterator.next();
            insertAtomic((int)tuple._0,(double)tuple._1);
        }
    }
	public boolean insertAtomic(int a0, double a1) {
		int i=(int)(a0-base); rangeCheck(i);
		if (!filled.compareAndSet(i, (byte)0, (byte)-1)) {
		    while(filled.get(i)!=1) {}
			if (filled.get(i)==1 && contains(a0, a1)) return false;

			String msg = "Cannot insert the tuple {"+a0+","+a1 +"} to Rank$1\n";
	 		msg += "Rank$1 already contains {"+a0+","+col1(i) +"} with the same 1st (array index) column\n";
	 		msg += "To insert the tuple, declare Rank$1 with nested columns.\n";
	 		throw new SociaLiteException(msg);
		}

		if (isEmpty) isEmpty = false;

		col1[i] = a1;

		filled.compareAndSet(i, (byte)-1, (byte)1);
		return true;
	}*/

	public void iterate(VisitorImpl v) {
		if (isEmpty) return;
		for (int i=0; i<filled.length(); i++) {
			if (filled.get(i)==1) {
				boolean $cont=v.visit(i+base, col1(i));
				if (!$cont) return;
			}
		}
	}
	public void iterate_range_0(int _from, int _to, VisitorImpl v) {
		int from=(int)(_from-base); rangeCheck(from);
		int to=(int)(_to-base); rangeCheck(to);
		for (int i=from; i<=to; i++) {
			if (filled.get(i)==1) {
				boolean $cont=v.visit(i+base, col1(i));
				if (!$cont) return;
			}
		}
	}
	public void iterate_by_0(int n, VisitorImpl v) {
		int i=(int)(n-base); rangeCheck(i);
		if (i<0||i>=filled.length()) return;
		if (filled.get(i)==1) {
			boolean $cont=v.visit(n ,col1(i));
			if (!$cont) return;
		}
	}
	public void iterate_part_from_0(int from, boolean inclusive, VisitorImpl v) {
		int start =(int)(from-base); rangeCheck(start);
		for (int i=start; i<=filled.length(); i++) {
			if (filled.get(i)==1) {
				boolean $cont=v.visit(i+base, col1(i));
				if (!$cont) return;
			}
		}	
	}
	public void iterate_part_to_0(int to, boolean inclusive, VisitorImpl v) {
		int end =(int)(to-base); rangeCheck(end);
		if (end > filled.length()-1) end = filled.length()-1;
		for (int i=0; i<=end; i++) {
			if (filled.get(i)==1) {
				boolean $cont=v.visit(i+base, col1(i));
				if (!$cont) return;
			}
		}	
	}

	
	public double groupby(int a0) {
		int i=(int)(a0-base); rangeCheck(i);
		assert filled.get(i)==1;

		if (true) {
			return col1[i];
		} else {
			throw new SociaLiteException("Unexpected groupby operation");
		}		
	}
	public boolean contains(int a0) {
		int i=(int)(a0-base); rangeCheck(i);
		if (filled.get(i)!=1) return false;

		if (true)
			return true;
		throw new SociaLiteException("Unexpected contains(for groupby) operation");
	}
	public boolean update(int a0, double a1) {
		int i=(int)(a0-base); rangeCheck(i);
		assert filled.get(i)==1;
		col1[i] = a1;
;		
		return true;
	}

}