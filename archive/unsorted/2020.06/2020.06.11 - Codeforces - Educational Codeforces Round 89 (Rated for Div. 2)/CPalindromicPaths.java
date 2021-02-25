package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CPalindromicPaths {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();
            }
        }
        List<List<Integer>> diags = new ArrayList<>();
        for (int i = 0; i < (n+m-1); i++) {
            diags.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                diags.get(i + j).add(a[i][j]);
            }
        }
        MiscUtility.assertion(diags.size() == (n+m-1), String.format("diags.size() [%d] is not (n+m-1)[%d]",
                diags.size(), n+m-1));
        int ans = 0;
        int N = n+m-1;
        int mi = N/2 - 1;
        for (int i = 0; i <= mi; i++) {
            int other = N-1-i;
            int za = 0;
            for (int val : diags.get(i)) {
                if (val != 0) za++;
            }
            for (int val : diags.get(other)) {
                if (val != 0) za++;
            }
            int zo = 0;
            for (int val : diags.get(i)) {
                if (val != 1) zo++;
            }
            for (int val : diags.get(other)) {
                if (val != 1) zo++;
            }
            ans += Math.min(zo, za);
        }
        out.println(ans);
    }
}
