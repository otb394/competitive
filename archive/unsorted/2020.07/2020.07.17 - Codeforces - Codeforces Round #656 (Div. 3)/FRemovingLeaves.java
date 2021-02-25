package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FRemovingLeaves {
    int[] deg;
    List<TreeSet<Integer>> graph;
    int[] leaves;
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        List<Pair<Integer, Integer>> el = new ArrayList<>();
        for (int i = 0; i < (n-1); i++) {
            int u = in.nextInt();u--;
            int v = in.nextInt();v--;
            el.add(Pair.of(u, v));
        }
        if (n == 2) {
            out.println(1);
            return;
        }
        leaves = new int[n];
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new TreeSet<>());
        }
        deg = new int[n];
        for (int i = 0; i < (n-1); i++) {
            Pair<Integer, Integer> pr = el.get(i);
            int u = pr.getLeft();
            int v = pr.getRight();
            deg[u]++;deg[v]++;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        for (int i = 0; i < n; i++) {
            if (deg[i] == 1) {
                leaves[graph.get(i).first()]++;
            }
        }
        Queue<Integer> stt = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int leav = leaves[i];
            if (leav >= k) {
                stt.add(i);
            }
        }
        int ans = 0;
        boolean[] del = new boolean[n];
        ut.printDebug(n, "n", k, "k", stt, "stt", of(Arrays.stream(leaves).boxed().collect(Collectors.toList())), "leaves");
        ut.printDebug(graph, "graph");
        int vert = n;
        while (!stt.isEmpty()) {
            int u = stt.poll();
            if (del[u]) continue;
            ut.printDebug(u, "u");
            int leav = leaves[u];
            int rem = leav % k;
            int dll = 0;
            int dbl = leav - rem;
            Set<Integer> st = graph.get(u);
            List<Integer> ll = graph.get(u).stream().filter(v -> deg[v] == 1).collect(Collectors.toList());
            for (int v : ll) {
//            for (int v : st) {
                del[v] = true;
                st.remove(v);
                graph.get(v).remove(u);
                deg[u]--;
                vert--;
                deg[v] = 0;
                dll++;
                ut.printDebug(v, "v", vert, "vert", dll, "dll", dbl, "dbl", leav, "leav");
                if (dll == dbl) break;
            }
            ans+=leav / k;
            leaves[u] = rem;
            ut.printDebug(ans, "ans");
            if (vert == 2) {
                if (k == 1) {
                    ans++;
                }
                break;
            }
            if (st.size() == 1) {
                int par = graph.get(u).first();
                leaves[par]++;
                if (leaves[par] == k) {
                    stt.add(par);
                }
            }
        }
        out.println(ans);
    }

    private int getLeaves(int i) {
        Set<Integer> st = graph.get(i);
        int cnt = 0;
        for (int val : st) {
            if (deg[val] == 1) cnt++;
        }
        return cnt;
    }

    private static <T> Supplier<T> of(T ob) {
        return () -> ob;
    }
}
