package socialite.test;

import socialite.dist.master.MasterNode;
import socialite.engine.ClientEngine;
import socialite.engine.LocalEngine;
import socialite.parser.Parser;
import socialite.tables.QueryVisitor;
import socialite.tables.Tuple;

public class sIMrANK {
    public static void main(String[] args) throws InterruptedException {
        LocalEngine localEngine = new LocalEngine();

        localEngine.run("Node1(int n:0..875712).\n" +
                "Node2(int n:0..875712).\n" +
                "SimRank(int s:0..875712, (int t, double rank)).\n" +
                "Edge1(int n:0..875712, (int t)).\n" +
                "Edge2(int n:0..875712, (int t)).\n" +
                "Edgecnt1(int n:0..875712, int cnt).\n" +
                "Edgecnt2(int n:0..875712, int cnt).\n" +
                "Edge1(s, t) :- l=$read(\"/home/wangqg/Datasets/PageRank/Google/edge.txt\"), (s1,s2)=$split(l, \"\t\"),\n" +
                "             s=$toInt(s1), t=$toInt(s2).\n" +
                "Edge2(s,t) :- Edge1(s,t).\n" +
                "Node1(n) :- l=$read(\"/home/wangqg/Datasets/PageRank/Google/node.txt\"), n=$toInt(l)."
        );
//        localEngine.run("?- SimRank(x, y, z).", new QueryVisitor() {
//            @Override
//            public boolean visit(Tuple _0) {
//                System.out.println("ishere?");
//
//                return super.visit(_0);
//
//            }
//        });
//        localEngine.run("?- TMP_TABLE_SimRank(x, y, x1, y1, c1,c2).", new QueryVisitor() {
//            @Override
//            public boolean visit(Tuple _0) {
//                System.out.println("isherethat?");
//
//                return super.visit(_0);
//
//            }
//        });
        localEngine.shutdown();



//
//        localEngine.run("edge(int src:0..7, (int dst)).\n" +
//                "Weight(int src:0..7, int dst, double w).\n" +
//                "tmp(int s,int t,int c1,int c2,double r).\n"+
//                "H(int src:0..7, int dst, double h).\n" +
//                "Belief(int src:0..7, int dst, double b).\n" +
//                "EdgeCnt(int n:0..7, int cnt).\n"+
//                  "tmp(s,c1,t,c2,r) :-  Belief(s, c1, b), Weight(s, t, w), H(c1, c2 ,h), Belief(s, c1, b),r=w*h.\n");



//        localEngine.run("Rank(int src:0..7, int dst, float rank).");
//        localEngine.shutdown();
//        MasterNode.startMasterNode();
//        while (!MasterNode.getInstance().allOneLine())//waiting workers online
//            Thread.sleep(100);
//        ClientEngine en = new ClientEngine();
//        en.run("Rank(int src:0..7, int dst, double rank).");
        String p="Node(int n:0..4).\n" +
                "Rank(int n:0..4, int rank).\n" +
                "Edge(int n:0..4, (int t)).\n" +
                "Edgeweight(int n:0..4, (int t, int p)).\n" +
         //       "Edge(s, t) :- l=$read(\"/home/wangqg/socialite/examples/prog2_edge.txt\"), (s1,s2)=$split(l, \"\t\"),s=$toInt(s1), t=$toInt(s2).\n" +"Edgeweight(s, t, 2) :- Edge(s,t).\n" +
                "Node(n) :- l=$read(\"/home/wangqg/socialite/examples/prog2_node.txt\"), n=$toInt(l).\n" +
                "Rank(n, r) :- Node(n), r = 1.";

        String program = "edge(int src:0..7, (int dst)).\n" +
                "Weight(int src:0..7, int dst, double w).\n" +
                "tmp(int s,int t,int c1,int c2,double r).\n"+
                "H(int src:0..7, int dst, double h).\n" +
                "Belief(int src:0..7, int dst, double b).\n" +
                "EdgeCnt(int n:0..7, int cnt).\n"+
                "Weight(s, t, w) :- l=$read(\"/home/wangqg/Datasets/SSSP/Google/edge.txt\"), (s1,s2,s3)=$split(l, \"\t\"),"+
                "s=$toInt(s1), t=$toInt(s2), w=$toDouble(s3).\n" +
                "Belief(s, t, b) :- l=$read(\"/home/wangqg/Datasets/SSSP/Google/edge.txt\"), (s1,s2,s3)=$split(l, \"\t\"),"+
                "s=$toInt(s1), t=$toInt(s2), b=$toDouble(s3).\n" +
              //  "tmp(s,c1,t,c2,r) :-  Belief(s, c1, b), Weight(s, t, w), H(c1, c2 ,h), Belief(s, c1, b),r=w*h.\n"+
                "Belief(t, c2, $dsum(b1)) :- Belief(s, c1, b), Weight(s, t, w), H(c1, c2 ,h), Belief(s, c1, b),r=w*h.\n";





        String programSim = "E(int src:0..7, (int dst)).\n" +
                "S(int src:0..7, int dst, double rank).\n" +
                "EdgeCnt(int n:0..7, int cnt).\n"+
                "E(s, t) :- l=$read(\"/home/wangqg/Datasets/SSSP/Google/edge.txt\"), (s1,s2)=$split(l, \"\t\"),"+
                "s=$toInt(s1), t=$toInt(s2).\n" +
                "S(s, t, 1.0) :- E(s, t).\n" +
                "S(s, t, $dsum(r1)) :- S(s1, t1, r), E(s1, s), E(t1, t), EdgeCnt(s, d1), EdgeCnt(t,d2), r1= 0.8 / d1 / d2 * r.\n";
       // Parser parser = new Parser(p);
      //  parser.parse(p);

    }
}
