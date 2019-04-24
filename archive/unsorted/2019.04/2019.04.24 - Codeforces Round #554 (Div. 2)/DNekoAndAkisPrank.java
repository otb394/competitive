package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import static util.MathUtility.add;

public class DNekoAndAkisPrank {
    private static final int MOD = 1000000007;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        out.print(solve(n));
    }

    private int solve(int n) {
        int N = 2*n;
        int[][][] dp = new int[N+1][N+1][2];
        dp[0][0][0]=0;
        dp[0][0][1]=0;
        for (int i = 1; i<=N; i++) {
            for (int j = 0; j<=i; j++) {
                for (int k = 0; k<=1 ;k++) {
                    dp[i][j][k] = 0;
                    if (k == 1) {
                        if (i - j >= 2) {
                            dp[i][j][k] = add(dp[i][j][k], dp[i - 1][j + 1][0], MOD);
                        }
                        if (j > 0) {
                            dp[i][j][k] = add(dp[i][j][k], dp[i - 1][j - 1][0], MOD);
                        }
                    } else {
                        dp[i][j][k] = Math.max(getVal(dp, i, j, false, false),
                                Math.max(getVal(dp,i,j,false,true),
                                        getVal(dp,i,j,true,false)));
                    }
                }
            }
        }
        return dp[N][0][0];
    }

    private int getVal(int[][][] dp, int i, int j, boolean selectStart, boolean selectEnd) {
        int ret = 0;
        if (i-j >= 2) {
            if (selectStart) {
                ret = add(ret, dp[i-1][j+1][1], MOD);
                ret = add(ret, 1, MOD);
            } else {
                ret = add(ret, dp[i-1][j+1][0], MOD);
            }
        }
        if (j > 0) {
            if (selectEnd) {
                ret = add(ret, dp[i-1][j-1][1], MOD);
                ret = add(ret, 1, MOD);
            } else {
                ret = add(ret, dp[i-1][j-1][0], MOD);
            }
        }
        return ret;
    }
}
