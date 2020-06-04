package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class BSubsequenceHate {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.nextString();
        int n = s.length();
        int[][] dp = new int[2][n];
        dp[0][0] = (s.charAt(0) == '0') ? 0 : 1;
        dp[1][0] = (s.charAt(0) == '1') ? 0 : 1;
        int[][] pre = new int[2][n];
        pre[0][0] = (s.charAt(0) == '0') ? 1 : 0;
        pre[1][0] = (s.charAt(0) == '1') ? 1 : 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '0') {
                pre[0][i] = pre[0][i-1] + 1;
                pre[1][i] = pre[1][i-1];
            } else {
                pre[0][i] = pre[0][i-1];
                pre[1][i] = pre[1][i-1] + 1;
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = (s.charAt(j) == ((char)(i+'0'))) ? Math.min(dp[i][j-1], pre[i][j-1])
                        : Math.min(dp[i][j-1] + 1, pre[i][j-1] + 1);
            }
        }
        int ans = Math.min(dp[0][n-1], dp[1][n-1]);
        out.println(ans);
    }
}
