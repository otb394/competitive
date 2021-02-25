package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Fraction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class PerCapitaIncome {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] b = in.nextIntArray(n);
        List<Integer> inds = new ArrayList<>();
        Fraction mx = new Fraction(0L, 1L);
        for (int i = 0; i < n; i++) {
            Fraction u = new Fraction(a[i], b[i]);
            if (u.compareTo(mx) > 0) {
                mx = u;
            }
        }
        for (int i = 0; i < n; i++) {
            Fraction u = new Fraction(a[i], b[i]);
            if (u.compareTo(mx) == 0) {
                inds.add(i);
            }
        }
        Set<Integer> indSet = new HashSet<>(inds);
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();u--;
            int v = in.nextInt();v--;
            if (indSet.contains(u) && indSet.contains(v)) {
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
        }
        boolean[] dis = new boolean[n];
        int mxc = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!dis[i]) {
                dis[i] = true;
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                int cnt = 0;
                List<Integer> cand = new ArrayList<>();
                while (!q.isEmpty()) {
                    int u = q.poll();
                    cand.add(u);
                    cnt++;
                    for (int v : graph.get(u)) {
                        if (!dis[v]) {
                            dis[v] = true;
                            q.add(v);
                        }
                    }
                }
                if (cnt > mxc) {
                    mxc = cnt;
                    ans = cand;
                }
            }
        }
        out.println(mxc);
        for (int val : ans) {
            out.print((val+1) + " ");
        }
        out.println();
    }
}
