package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DJohnnyAndContribution {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        List<Pair<Integer,Integer>> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            edges.add(Pair.of(u, v));
        }
        int[] t = new int[n+1];
        for (int i = 1; i <= n; i++) {
            t[i] = in.nextInt();
        }
        for (int i = 0; i < m; i++) {
            int u = edges.get(i).getLeft();
            int v = edges.get(i).getRight();
            if (t[u] == t[v]) {
                out.println(-1);
                return;
            }
        }
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(null);
        for (int i = 1; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = edges.get(i).getLeft();
            int v = edges.get(i).getRight();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        for (int i = 1; i <= n; i++) {
            graph.get(i).sort(Comparator.comparingInt(bid -> t[bid]));
        }
        Set<Integer> st = new HashSet<>();
        List<Integer> blogs = IntStream.range(1, n+1).boxed().sorted(Comparator.comparingInt(i -> t[i]))
                .collect(Collectors.toList());
        int ind = 0;
        while (ind < n && t[blogs.get(ind)] == 1) {
            st.add(blogs.get(ind));
            ind++;
        }
        if (st.size() == 0) {
            out.println(-1);
            return;
        }
        int val = 2;
        while (ind < n) {
            int bl = blogs.get(ind);
            int blt = t[bl];
            if (blt != val) {
                out.println(-1);
                return;
            }
            int j = ind+1;
            while(j < n && t[blogs.get(j)] == blt) {
                j++;
            }
            for (int i = ind; i < j; i++) {
                int bll = blogs.get(i);
                int myVal = 1;
                for (int ne : graph.get(bll)) {
                    if (myVal == t[ne]) myVal++;
                }
                if (myVal != val) {
                    out.println(-1);
                    return;
                }
            }
            ind = j;
            val++;
        }
        for (int blog : blogs) {
            out.print(blog + " ");
        }
        out.println();
    }
}
