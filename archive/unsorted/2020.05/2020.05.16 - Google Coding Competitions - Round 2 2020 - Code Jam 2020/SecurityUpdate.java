package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.AdjacencyList;
import datastructure.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class SecurityUpdate {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int c=in.nextInt();
        int d=in.nextInt();
        int[] x=in.nextIntArray(c-1);
        Graph<Integer,MyEdge> g = new AdjacencyList<>();
        for (int i = 0; i < c; i++) {
            g.addVertex(i);
        }
        Map<Integer,MyEdge> edgeIndexToEdge = new HashMap<>();
        for (int i = 0; i < d; i++) {
            int u = in.nextInt();u--;
            int v=in.nextInt();v--;
            MyEdge edge = new MyEdge(u,v);
            g.addEdge(edge);
            edgeIndexToEdge.put(i, edge);
        }
        int[] minDis = new int[c];
        Arrays.fill(minDis, -1);
        minDis[0]=0;
        int[] r = new int[c];
        Arrays.fill(r, -1);
        r[0]=0;
        List<Integer> tinds = new ArrayList<>();
        List<Integer> rinds = new ArrayList<>();
        for (int i = 0; i < (c-1); i++) {
            if (x[i] > 0) {
                minDis[i + 1] = x[i];
                tinds.add(i+1);
            } else {
                r[i+1]=-x[i];
                rinds.add(i+1);
            }
        }
        tinds.sort(Comparator.comparingInt(i -> minDis[i]));
        rinds.sort(Comparator.comparingInt(i -> r[i]));
        List<Integer> combined = new ArrayList<>();
        combined.add(0);
        int tit=0;int rit=0;
        int tn=tinds.size();
        int rn=rinds.size();
        while(tit < tn && rit < rn) {
            int rind=rinds.get(rit);
            int tind=tinds.get(tit);
            int tln=combined.size();
            if (r[rind] <= tln) {
                combined.add(rind);
                int k=combined.get(tln-1);
                if (r[k] == r[rind]) {
                    minDis[rind]=minDis[k];
                } else {
                    minDis[rind] = minDis[k]+1;
                }
                rit++;
            } else {
                combined.add(tind);
                int k=combined.get(tln-1);
                if(minDis[k]==minDis[tind]) {
                    r[tind]=r[k];
                } else {
                    r[tind] = r[k]+1;
                }
                tit++;
            }
        }
        while(tit < tn) {
            int tind=tinds.get(tit);
            int tln=combined.size();
            combined.add(tind);
            int k=combined.get(tln-1);
            if(minDis[k]==minDis[tind]) {
                r[tind]=r[k];
            } else {
                r[tind] = r[k]+1;
            }
            tit++;
        }
        while(rit < rn) {
            int rind=rinds.get(rit);
            int tln=combined.size();
            combined.add(rind);
            int k=combined.get(tln-1);
            if (r[k] == r[rind]) {
                minDis[rind]=minDis[k];
            } else {
                minDis[rind] = minDis[k]+1;
            }
            rit++;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] dis = new boolean[c];
        dis[0]=true;
        while(!queue.isEmpty()) {
            int t=queue.poll();
            List<MyEdge> nEdges = g.getEdges(t);
            int ourMin = minDis[t];
            for (MyEdge ed: nEdges) {
                int otherMin = minDis[ed.second];
                if (ourMin < otherMin) {
                    ed.w = otherMin-ourMin;
                    g.getReverse(ed).w = otherMin-ourMin;
                }
                if (!dis[ed.second]) {
                    dis[ed.second] = true;
                    queue.add(ed.second);
                }
            }
        }

        out.print(String.format("Case #%d:", testNumber));
        for (int i = 0; i < d; i++) {
            MyEdge ed = edgeIndexToEdge.get(i);
            out.print(" " + ed.w);
        }
        out.println();
    }

    private static class MyEdge extends Graph.Edge<Integer, MyEdge> {
        public static final int INF = 1000000;
        public int w;
        @Override
        public MyEdge reverse() {
            return new MyEdge(second, first, w);
        }

        public MyEdge(Integer first, Integer second, Integer w) {
            super(first, second);
            this.w = w;
        }

        public MyEdge(Integer first, Integer second) {
            super(first, second);
            this.w = INF;
        }
    }
}
