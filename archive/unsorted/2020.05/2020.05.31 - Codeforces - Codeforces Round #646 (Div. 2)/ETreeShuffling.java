package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.AdjacencyList;
import datastructure.Graph;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ETreeShuffling {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            b[i] = in.nextInt();
            c[i] = in.nextInt();
        }
        int fv = 0;
        int sv = 0;
        for (int i = 0; i < n; i++) {
            if (b[i] == 1) fv++;
            if (c[i] == 1) sv++;
        }
        if (fv != sv) {
            out.println(-1);
            return;
        }
        Graph<Integer, Graph.BaseEdge<Integer>> graph = new AdjacencyList<>();
        for (int i = 0; i < n; i++) {
            graph.addVertex(i);
        }
        for (int i = 0; i < (n-1); i++) {
            int u = in.nextInt();u--;
            int v = in.nextInt();v--;
            Graph.BaseEdge<Integer> edge = Graph.BaseEdge.of(u, v);
            graph.addEdge(edge);
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int[] par = new int[n];
        par[0] = 0;
        int[] rank = new int[n];
        rank[0] = 0;
        while(!q.isEmpty()) {
            int u = q.poll();
            List<Integer> seconds = graph.getEdges(u).stream().map(e -> e.second).collect(Collectors.toList());
            for (int v : seconds) {
                if (v != par[u]) {
                    par[v] = u;
                    a[v] = Math.min(a[v], a[u]);
                    q.add(v);
                    rank[v] = rank[u] + 1;
                }
            }
        }
        int[] bottomOrder = IntStream.range(0, n).boxed().sorted(Comparator.comparingInt(i -> -rank[i]))
                .mapToInt(i -> i).toArray();

        int[] r = new int[n];
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            if (b[i] != c[i]) {
                if (b[i] == 0) {
                    s[i]++;
                } else {
                    r[i]++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int u = bottomOrder[i];
            if (u != par[u]) {
                r[par[u]] += r[u];
                s[par[u]] += s[u];
            }
        }
        long ans = 0L;
        long[] tans = new long[n];
        for (int i = 0; i < n; i++) {
            tans[i] = ((long)Math.min(r[i], s[i])) * ((long)a[i]);
        }
        for (int i = 0; i < n; i++) {
            int u = bottomOrder[i];
            if (u != par[u]) {
                tans[par[u]] -= ((long)Math.min(r[u], s[u])) * ((long)a[par[u]]);
            }
        }
        for (int i = 0; i < n; i++) {
            ans+=tans[i];
        }
        ans<<=1L;
        out.println(ans);
    }
}
