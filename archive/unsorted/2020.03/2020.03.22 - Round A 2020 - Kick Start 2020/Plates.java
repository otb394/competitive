package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class Plates {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int p = in.nextInt();
        int[][] cumb = new int[n][k];
        for (int i = 0; i < n; i++) {
            cumb[i] = in.nextSumIntArray(k);
        }

        int maxN = n * k;

        int[][] dp = new int[n][maxN + 1];

        for (int i = 1; i <= k; i++) {
            dp[0][i] = cumb[0][i-1];
        }
        for (int i = 1; i < n; i++) {
            int maxPlates = (i+1) * k;
            for (int j = 0; j <= maxPlates; j++) {
                int mx = Math.min(j, k);
                for (int x = 0; x <= mx; x++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-x] + ((x == 0) ? (0) : (cumb[i][x-1])));
                }
            }
        }

        int ans = dp[n-1][p];

        out.println(String.format("Case #%d: %d", testNumber, ans));
    }
}
