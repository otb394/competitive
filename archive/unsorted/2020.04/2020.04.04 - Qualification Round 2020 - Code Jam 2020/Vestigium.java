package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class Vestigium {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[][] mat = new int[n][n];
        for (int i = 0; i < n; i++) {
            mat[i] = in.nextIntArray(n);
        }
        Set<Integer> S = new HashSet<>();

        int tr = 0;
        int rc = 0;
        int cc = 0;
        for (int i = 0; i < n; i++) {
            tr += mat[i][i];
        }

        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < n; j++) {
                int val = mat[i][j];
                if (set.contains(val)) {
                    rc++;
                    break;
                }
                set.add(val);
            }
        }

        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < n; j++) {
                int val = mat[j][i];
                if (set.contains(val)) {
                    cc++;
                    break;
                }
                set.add(val);
            }
        }

        out.println(String.format("Case #%d: %d %d %d", testNumber, tr, rc, cc));
    }
}
