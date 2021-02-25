package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Fraction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeautyOfTree {
    int[] rank;
    int[] path;
    int z;
    List<List<Integer>> graph;
    int[] aanc;
    int[] banc;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int[] arr = in.nextIntArray(n-1);
        int[] p = new int[n];
        p[0] = -1;
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            p[i] = arr[i-1] - 1;
            graph.get(p[i]).add(i);
        }
        aanc = new int[n];
        banc = new int[n];
        int[] ad = new int[n];
        int[] bd = new int[n];
        rank = new int[n];
        rank[0] = 0;
        path = new int[n];
        z = 0;
        dfs(0, a, b);
        Arrays.fill(ad, 1);
        Arrays.fill(bd, 1);
        for (int i = n-1; i >= 0; i--) {
            int ap = aanc[i];
            if (ap != -1) {
                ad[ap] += ad[i];
            }
            if (banc[i] != -1) {
                bd[banc[i]] += bd[i];
            }
        }
        Fraction ans = new Fraction(0,  1);
        for (int i = 0; i < n; i++) {
            long pnum = ((long)(n-ad[i])) * ((long)(n-bd[i]));
            long denom = sq(n);
            long num = denom - pnum;
            ans = ans.add(new Fraction(num, denom));
        }
        ans = ans.reduce();
        out.printf("Case #%d: %.8f\n", testNumber, ans.doubleValue());
    }

    private long sq(long x) {
        return x*x;
    }

    //rank is set before
    //path includes only parent
    private void dfs(int s, int a, int b) {
        int pa = z-a;
        if (pa >= 0) {
            aanc[s] = path[pa];
        } else {
            aanc[s] = -1;
        }
        int pb = z-b;
        if (pb >= 0) {
            banc[s] = path[pb];
        } else {
            banc[s] = -1;
        }
        path[z] = s;
        z++;
        List<Integer> c = graph.get(s);
        for (int v : c) {
            rank[v] = rank[s] + 1;
            dfs(v, a, b);
        }
        z--;
    }
}
