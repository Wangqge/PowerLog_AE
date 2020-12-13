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

// dynamicTable
public final class CompIDs_int_groupby0_id3 extends AbstractTableInst {
	private static final long serialVersionUID = 1;

	public static int tableid() { return 3; }
	public static CompIDs_int_groupby0_id3 create() { return new CompIDs_int_groupby0_id3(); }	

	static boolean groupby = false;
	static void setGroupby() { 
		if (groupby == false) {groupby = true;} 
	}
	static int makePos(int nodePos, int offset) { return (nodePos << 8) | offset; }
	static int getPos(int val) { return (val >> 8); }
	static int getOffset(int val) { return (val & 0xff); }

    static final class Node$Init extends Node {
        static final int INIT_CHUNK_SIZE=4;
        Node$Init() { super(INIT_CHUNK_SIZE); }
        boolean isFull() {
            if (len < CHUNK_SIZE) {return false;}
            return super.isFull();
        }
        int insert(int a0) {
            if (len == col0().length) {
                expand();
            }
            return super.insert(a0);
        }
        void expand() {
            int size = len*3/2;
            if (size > CHUNK_SIZE) size=CHUNK_SIZE;
            int[] _col0 = new int[size];

            System.arraycopy(col0, 0, _col0, 0, len);

            col0 = _col0;

        }
    }
    static class Node {
	    static ConcurrentWeakQueue<Node> pool = new ConcurrentWeakQueue<Node>();
	    static Node alloc() {
	    	Node n = pool.poll();
	        if (n == null) {
	            n = new Node();
	        } else {
	            n.init();
	        }
	        return n;
	    }
	    static void free(Node n) { pool.add(n); }

        static final int CHUNK_SIZE=256;

	    Object col0;

 int len;

        Node() { this(CHUNK_SIZE); }
        Node(int capacity) {
            col0 = new int[capacity];

        }
        void init() {len = 0;}
        public void free() { Node.free(this); }

		boolean isEmpty() { return len() == 0; }
        boolean isFull() { return len() >= col0().length; }
        int len() { return len; }

        int[] col0() { return (int[])col0; }


		public String toString() {
			String s="{";
			for (int i=0; i<len; i++) {
				s+="(";
				s+= col0()[i]+" ";

				s+="),";
			}
			s+="}";
			return s;
		}
		void spill(int num, Node to) {
			assert num > 0 && num < len;
			System.arraycopy(to.col0(), 0, to.col0(), num, to.len);

			System.arraycopy(col0(), len-num, to.col0(), 0, num);

			len -= num;
			to.len += num;
		}
        boolean contains(int a0, boolean[] dontcare) {
        	if (dontcare==null) {return contains(a0);}
            for (int i=0; i<len(); i++) {
                if (true&& (dontcare[0]||col0()[i]==(a0))) {
                    return true;
                }
            }
            return false;
        }
        boolean contains(int a0) {
            for (int i=0; i<len(); i++) {
                if (true&& col0()[i]==(a0)) {
                    return true;
                }
            }
            return false;
        }
        boolean contains_at(int a0, int at, boolean[] dontcare) {
        	if (dontcare==null) {return contains_at(a0,at);}
            int i = at;
            return true&& (dontcare[0]||a0==(col0()[i]));
        }
        boolean contains_at(int a0, int at) {
            int i = at;
            return true&& a0==(col0()[i]);
        }
        int insert(int a0) {
            col0()[len] = a0;

            return len++;
        }
        boolean iterate(VisitorImpl v) {
            for (int i=0; i<len; i++) {
                boolean cont = v.visit(col0()[i]);
                if (!cont) return false;
            }
            return true;
        }
        boolean iterate_at(VisitorImpl v, int pos) {
            return v.visit(col0()[pos]);
        }
	}
	SArrayList<Node> nodeList;
	public CompIDs_int_groupby0_id3() {
		nodeList = new SArrayList<Node>(1);
		nodeList.add(new Node$Init());
	}

	public int id() { return 3; }
    public int size() { assert false; return -1; }
    public String name() { return "CompIDs"; }

    public boolean isEmpty() { return nodeList.getQuick(0).len() == 0; }
	public String toString() {
		String str="Table CompIDs(id=3):";
		return str + nodeList.getQuick(0).toString();
	}
	public void clearQuick() { clear(); }
	public void clear() {
		for (int i=0; i<nodeList.size(); i++) {
			Node n = nodeList.getQuick(i);
			nodeList.setQuick(i, null);
			n.free();
		}
		nodeList.resetQuick();
		nodeList.add(new Node$Init());
	}
	public boolean contains(final int a0) {
		for (int i=0; i<nodeList.size(); i++) {
			Node n = nodeList.getQuick(i);
		    if (n.contains(a0))
		        return true;
		}
		return false;
	}
	public boolean contains(final int a0, final boolean[] dontcare) {
		if (dontcare==null) {return contains(a0);} 
		for (int i=0; i<nodeList.size(); i++) {
			Node n = nodeList.getQuick(i); 
            if (n.contains(a0, dontcare))
        	    return true;
        }
		return false;
	}

	void addToIndex(int pos) {
	}
	void addToGroupbyMap(int pos) {
	}
	public boolean insert(int a0) {
		if (contains(a0)) return false;
		Node last = nodeList.getQuick(nodeList.size()-1);
		int nodePos = nodeList.size()-1;
	    if (last.isFull()) {
			last = Node.alloc();
			nodeList.add(last);
			nodePos = nodeList.size()-1;
	    }
        int offset = last.insert(a0);
		int pos = makePos(nodePos, offset);
        addToIndex(pos);
        addToGroupbyMap(pos);
		return true;
	}
	public boolean insertAtomic(int a0) {
        synchronized(this) {
            return insert(a0);
        }
    }

 // ] if(gbColumns)

	public void iterate(VisitorImpl v) {
		for (int i=0; i<nodeList.size(); i++) {
			Node n = nodeList.getQuick(i);
		    boolean cont = n.iterate(v);
		    if (!cont) return;
		}
	}
}