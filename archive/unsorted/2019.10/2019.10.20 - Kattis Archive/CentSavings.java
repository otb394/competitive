package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;

public class CentSavings {
    private int[][] dp;
    private int[][] S;
    private int[] cum;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();
        int d=in.nextInt();
        int[] p=in.nextIntArray(n);
        dp = new int[n][d+1];
        for(int[]ar: dp) {
            Arrays.fill(ar, -1);
        }
        cum=new int[n];
        cum[0] = p[0];
        for (int i = 1; i < n; i++) {
            cum[i] = cum[i-1] + p[i];
        }
        S = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                S[i][j] = (i == 0) ? (cum[j]) : (cum[j]-cum[i-1]);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                S[i][j]=roundCent(S[i][j]);
            }
        }
        int ans = dp(0,n-1,d);
        out.print(ans);
    }

    private int dp(int i,int j,int d) {
        if (dp[i][d] != -1) return dp[i][d];
        if (i == j || d == 0) {
            dp[i][d] = S[i][j];
            return dp[i][d];
        }
        int ans = dp(i, j, d-1);
        for (int k = i+1; k <= j; k++) {
            if (d > 1) {
                ans = Math.min(ans, S[i][k-1] + dp(k,j,d-1));
            } else {
                ans = Math.min(ans, S[i][k-1] + S[k][j]);
            }
        }
        dp[i][d] = ans;
        return dp[i][d];
    }

    private int roundCent(int n) {
        int lastDigit = n%10;
        if (lastDigit < 5) {
            return n - lastDigit;
        } else {
            return n + (10 - lastDigit);
        }
    }
}
