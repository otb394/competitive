package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class Alphabet {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.nextString();
        int n = s.length();
        int[][] next = new int[26][n];
        for (int i = 0; i < 26; i++) {
            if ((s.charAt(n-1) - 'a') == i) {
                next[i][n-1] = n-1;
            } else {
                next[i][n-1] = n;
            }
            for (int j = n-2; j >= 0; j--) {
                if ((s.charAt(j) - 'a') == i) {
                    next[i][j] = j;
                } else {
                    next[i][j] = next[i][j+1];
                }
            }
        }
        int[][] dp = new int[27][n+1];
        for (int i = 0; i < n; i++) {
            dp[26][i] = 0;
        }
        for (int j = 0; j <= 26; j++) {
            dp[j][n] = 26-j;
        }
        for (int j = 25; j >= 0; j--) {
            for (int i = n-1; i >= 0; i--) {
                int k = next[j][i];
                if (k == n) {
                    dp[j][i] = dp[j+1][i] + 1;
                } else {
                    dp[j][i] = Math.min(dp[j+1][i]+1, dp[j+1][k+1]);
                }
            }
        }
        out.print(dp[0][0]);
    }
}
