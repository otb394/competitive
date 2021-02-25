package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;

import java.util.HashMap;
import java.util.Map;

public class CovidSamplingChallenge {
    private Map<Pair<Pair<Integer,Integer>, Pair<Integer,Integer>>, Integer> cache;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        cache = new HashMap<>();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            try {
                slve(i, in, out);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void slve(int testNumber, InputReader in, OutputWriter out) {
        cache.clear();
        int n = in.nextInt();
        int p = in.nextInt();
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                ans[i][j] = query(i, j, i, j, in, out);
                ans[i][j] = queryCell(i, j, n, in, out);
            }
        }
        answer(ans, n, in, out);
    }

    private int queryCell(int i, int j, int n, InputReader in, OutputWriter out) {
        int mid = n/2;
        if (j <= mid) {
            return query(i, j, i, n-1, in, out) - query(i, j+1, i, n-1, in, out);
        } else {
            return query(i, 0, i, j, in, out) - query(i, 0, i, j-1, in, out);
        }
    }

    private int query(int i, int j, int x, int y, InputReader in, OutputWriter out) {
        Pair<Pair<Integer,Integer>, Pair<Integer,Integer>> key = Pair.of(Pair.of(i,j), Pair.of(x,y));
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        out.println(String.format("1 %d %d %d %d", i+1, j+1, x+1, y+1));
        out.flush();
        out.flush();

        int response = in.nextInt();
        if (response == -1) {
            throw new RuntimeException("-1 received during query");
        }
        cache.put(key, response);
        return response;
    }

    private void answer(int[][] ans, int n, InputReader in, OutputWriter out) {
        out.println(2);
        out.flush();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out.print(ans[i][j] + " ");
            }
            out.println();
        }
        out.flush();
        int x = in.nextInt();
        if (x == -1) {
            throw new RuntimeException("-1 received during query");
        }
    }
}
