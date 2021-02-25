package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class MaximumCoins {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        long[][] mat = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = in.nextLong();
            }
        }
        long ans = -1;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, getSum(mat, i, 0, n));
        }
        for (int j = 1; j < n; j++) {
            ans = Math.max(ans, getSum(mat, 0, j, n));
        }
        out.printf("Case #%d: %d\n", testNumber, ans);
    }

    private long getSum(long[][] mat, int i, int j, int n) {
        long ans = 0L;
        for (int r=i, c=j; ((r < n) && (c < n)); r++, c++) {
            ans += mat[r][c];
        }
        return ans;
    }
}
