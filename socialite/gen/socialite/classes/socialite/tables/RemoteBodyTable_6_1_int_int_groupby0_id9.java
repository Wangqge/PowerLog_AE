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

public class RemoteBodyTable_6_1_int_int_groupby0_id9 extends TmpTableInst {
	private static final long serialVersionUID = 1;

	public static RemoteBodyTable_6_1_int_int_groupby0_id9 create() { return new RemoteBodyTable_6_1_int_int_groupby0_id9(false); }
	public static RemoteBodyTable_6_1_int_int_groupby0_id9 createSmall() { return new RemoteBodyTable_6_1_int_int_groupby0_id9(true); }

	static int id=9;

	int ordinaryCapacity;
    SIntArrayList col0;
    SIntArrayList col1;

	public RemoteBodyTable_6_1_int_int_groupby0_id9() { this(true); }	
	public RemoteBodyTable_6_1_int_int_groupby0_id9(boolean small) {
		int col0size=0, col1size=0, col2size=0, col3size=0;
		int overhead = 256+64*(2+0+0+0);
		int bufsize = ByteBufferPool.bufferSize()-overhead;
		if (small) { bufsize = ByteBufferPool.smallBufferSize()-overhead; }
		bufsize = (int)(bufsize/sizeEstimFactor());

		float row0size = 0+4+4;
		float row1size = 0;
		float row2size = 0;
		float row3size = 0;

		if(1==1) { // nesting level 1
			col0size = (int)(bufsize / row0size);
			ordinaryCapacity = col0size;
		} else if(1==2) { // nesting level 2
			row0size += 4;
			// row0size*size/8 + row1size*size = bufsize;
			// size(row0size/8 + row1size) = bufsize;
			col1size = (int)(bufsize / (row0size/8.0f+row1size));
			col0size = (int)(col1size/8.0f);
			ordinaryCapacity = col1size;
		} else if(1==3) { // nesting level 3
			row0size += 4; row1size += 4;
			col2size = (int)(bufsize / (row0size/8+row1size/4+row2size));
			col1size = (int)(col2size/4.0f);
			col0size = (int)(col2size/8.0f);
			ordinaryCapacity = col2size;
		} else { // nesting level 4
			row0size += 4; row1size += 4; row2size += 4;			
			col3size = (int)(bufsize / (row0size/8+row1size/6+row2size/4+row3size));
			col2size = (int)(col3size/4.0f);
			col1size = (int)(col3size/6.0f);
			col0size = (int)(col3size/8.0f);
			ordinaryCapacity = col3size;
		}

		col0 = new SIntArrayList(col0size);
		col1 = new SIntArrayList(col0size);

		
	}

	public int ordinaryCapacity() { return ordinaryCapacity; }
	public int capacity() {
		 return col0.capacity(); 
	}
	public int size() {
		 return col0.size(); 
	}
	public int vacancy() {
	    int vacancy = capacity() - size();
	    if (vacancy >= 8) { vacancy -= 8; }
	    else { vacancy = 0; }
	    return vacancy;
	}
	public boolean isSmall() { return size() < ordinaryCapacity()/10; }		

	public int totalAllocSize() {
		int total = 0;
		total += 32+col0.capacity()*4;
		total += 32+col1.capacity()*4;

		return total;		
	}
	public int totalDataSize() {
		int total = 256 /*overhead*/;
		total += 32+col0.size()*4;
		total += 32+col1.size()*4;

		return (int)(total*sizeEstimFactor());
	}
	static float estim = 1.0f;
	public void setSharedSizeEstimFactor(float v) {
		if (v > estim) { estim = v; }
	}
	public float sharedSizeEstimFactor() {
		return estim;
	}


	public int id() { return id; }
	public String name() { return "RemoteBodyTable_6_1"; }
	public String toString() {
		return "RemoteBodyTable_6_1_int_int_groupby0_id9(size="+size()+")";
	}	
	public void clearQuick() { clear(); }
	public void clear() {
		col0.resetQuick();
		col1.resetQuick();

	}

	public void addAll(TmpTableInst inst) {
		if (inst==null) return;
		RemoteBodyTable_6_1_int_int_groupby0_id9 other = (RemoteBodyTable_6_1_int_int_groupby0_id9)inst;
		col0.addAllFast(other.col0);
		col1.addAllFast(other.col1);


		int from=-1, offset=-1;


		
	}

	public boolean insert(int a0,int a1
	
) {				  
		col0.add(a0);
		col1.add(a1);

		return true;
	}

	public void iterate(VisitorImpl v) {
		int size = col0.size();
		for (int i=0; i < size; i++) {
			v.visit(col0.getQuick(i), col1.getQuick(i));
		}
	}

	@Override
	public int virtualSliceNum() { return 1;}

	public boolean isEmpty() {
		return col0.isEmpty();
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		ordinaryCapacity = in.readInt();

		col0.readExternal(in);
		col1.readExternal(in);




	}

	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(ordinaryCapacity);

		col0.writeExternal(out);
		col1.writeExternal(out);




	}
}