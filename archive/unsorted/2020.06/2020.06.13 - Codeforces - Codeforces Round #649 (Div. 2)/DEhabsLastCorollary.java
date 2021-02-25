package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import util.MathUtility;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DEhabsLastCorollary {
    MiscUtility ut;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        ut = new MiscUtility(false);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();u--;
            int v = in.nextInt();v--;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        int[] rank = new int[n];
//        List<Integer> ans = dfs(n, graph, rank);
        int hk = MathUtility.ceil(k, 2);
//        ut.printDebug(hk, "hk");
        if (m == (n-1)) {
            ndfs(n, graph, rank, k, out);
            int ecnt = 0;
            for (int i = 0; i < n; i++) {
                if ((rank[i]&1) == 0) {
                    ecnt++;
                }
            }
            Predicate<Integer> pred = (ecnt >= hk) ? (ind -> ((rank[ind]&1) == 0)) : (ind -> ((rank[ind]&1) != 0));
            int r = hk;
            out.println(1);
            for (int i = 0; i < n && r != 0; i++) {
                if (pred.test(i)) {
                    out.print((i+1) + " ");
                    r--;
                }
            }
            out.println();
        } else {
            boolean done = ndfs(n, graph, rank, k, out);
            if (!done) {
                printAns(n, graph, rank, k, out);
            }
        }
//        if (ans.isEmpty()) {
//            //Tree case
//            int ecnt = 0;
//            for (int i = 0; i < n; i++) {
//                if ((rank[i]&1) == 0) {
//                    ecnt++;
//                }
//            }
//            Predicate<Integer> pred = (ecnt >= hk) ? (ind -> ((rank[ind]&1) == 0)) : (ind -> ((rank[ind]&1) != 0));
//            int r = hk;
//            out.println(1);
//            for (int i = 0; i < n && r != 0; i++) {
//                if (pred.test(i)) {
//                    out.print((i+1) + " ");
//                    r--;
//                }
//            }
//            out.println();
//        } else {
//            int minCycle = ans.size();
////            ut.printDebug(minCycle, "minCycle");
//            if (minCycle <= k) {
//                out.println(2);
//                out.println(minCycle);
//                for (int val : ans) {
//                    out.print((val+1)+ " ");
//                }
//                out.println();
//            } else {
//                out.println(1);
//                for (int i = 0, r=0; r < hk; i+=2, r++) {
//                    out.print((ans.get(i) + 1) + " ");
//                }
//                out.println();
//            }
//        }
    }

    private boolean printAns(int n, List<List<Integer>> graph, int[] rank, int k, OutputWriter out) {
        boolean[] dis = new boolean[n];
        dis[0] = true;
        rank[0] = 0;
        int[] p = new int[n];
        p[0] = -1;
        return printAnsUtil(0, dis, graph, rank, p, k, out);
    }

    private boolean printAnsUtil(int s, boolean[] dis, List<List<Integer>> graph, int[] rank, int[] p, int k,
                              OutputWriter out) {
        for (int v : graph.get(s)) {
            if (!dis[v]) {
                dis[v] = true;
                rank[v] = rank[s] + 1;
                p[v] = s;
                boolean ans = printAnsUtil(v, dis, graph, rank, p, k, out);
                if (ans) return true;
            } else if (v != p[s]) {
                int cand = Math.abs(rank[s] - rank[v]) + 1;
                MiscUtility.assertion(cand > k, String.format("cand [%d] is <= k [%d]", cand, k));
                out.println(1);
                int curr = s;
                int hk = MathUtility.ceil(k, 2);
                for (int i = 0; i < hk; i++) {
                    out.print((curr + 1) + " ");
                    curr = p[curr];
                    curr = p[curr];
                }
                return true;
            }
        }
        return false;
    }

    private boolean ndfs(int n, List<List<Integer>> graph, int[] rank, int k, OutputWriter out) {
        boolean[] dis = new boolean[n];
        dis[0] = true;
        rank[0] = 0;
        int[] p = new int[n];
        p[0] = -1;
        return ndfsUtil(0, dis, graph, rank, p, k, out);
    }

    private boolean ndfsUtil(int s, boolean[] dis, List<List<Integer>> graph, int[] rank, int[] p, int k,
                             OutputWriter out) {
        for (int v : graph.get(s)) {
            if (!dis[v]) {
                dis[v] = true;
                rank[v] = rank[s] + 1;
                p[v] = s;
                boolean ans = ndfsUtil(v, dis, graph, rank, p, k, out);
                if (ans) return true;
            } else if (v != p[s]) {
                int cand = Math.abs(rank[s] - rank[v]) + 1;
                if (cand <= k) {
                    out.println(2);
                    out.println(cand);

                    int curr = s;
                    out.print((curr + 1) + " ");
                    do {
                        curr = p[curr];
                        out.print((curr + 1) + " ");
                    } while (curr != v);
                    out.println();
                    return true;
                }
            }
        }
        return false;
    }

    private List<Integer> dfs(int n, List<List<Integer>> graph, int[] rank) {
        boolean[] dis = new boolean[n];
        dis[0] = true;
        rank[0] = 0;
        int[] p = new int[n];
        p[0] = -1;
        return dfsUtil(0, dis, graph, rank, p);
    }

    private List<Integer> dfsUtil(int s, boolean[] dis, List<List<Integer>> graph, int[] rank, int[] p) {
//        ut.printDebug(() -> s, () -> "s", () -> Arrays.stream(p).boxed().collect(Collectors.toList()), () -> "p");
        List<Integer> ret = new ArrayList<>();
        for (int v: graph.get(s)) {
            if (!dis[v]) {
                dis[v] = true;
                rank[v] = rank[s] + 1;
                p[v] = s;
                List<Integer> ans = dfsUtil(v, dis, graph, rank, p);
                if (!ans.isEmpty()) {
                    if (ret.isEmpty() || ret.size() > ans.size()) {
                        ret = ans;
                    }
                }
            } else if (v != p[s]) {
//                ut.printDebug(v, "v");
                int cand = Math.abs(rank[s] - rank[v]) + 1;
                if (ret.isEmpty() || ret.size() > cand) {
                    List<Integer> cycle = new ArrayList<>();
                    int curr = s;
                    while (true) {
                        cycle.add(curr);
                        if (curr == v) break;
                        curr = p[curr];
                    }
                    ret = cycle;
                }
            }
        }
        return ret;
    }
}
