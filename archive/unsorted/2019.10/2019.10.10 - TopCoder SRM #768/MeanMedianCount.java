package code;

import util.MathUtility;

import java.util.Arrays;

public class MeanMedianCount {
    private static final int MOD = 1000000007;
    private int[][][] dp;

    public int getCount(int N, int needMean, int needMedian) {
        int sum = N * needMean;
        dp = new int[N+1][N/2+1][sum+1];
        for (int[][] arr: dp) {
            for (int[] arrr: arr) {
                Arrays.fill(arrr, -1);
            }
        }
        return getAns(N, N/2, sum, needMedian-1);
    }

    private int getAns(int n, int count, int sum, int x) {
        sum = Math.max(sum, 0);
        if (dp[n][count][sum] != -1) {
            return dp[n][count][sum];
        }
        if (n == 1) {
            int i,j;
            if (count == 0) {
                i = x+1;j = 10;
            } else {
                i = 0;j=10;
            }
            int st = Math.max(i,sum);
            if (st <= j) {
                dp[1][count][sum] = j-st+1;
            } else {
                dp[1][count][sum] = 0;
            }
            return dp[1][count][sum];
        } else {
            int ret = 0;
            if (count > 0) {
                for (int k = 0; k <= x; k++) {
                    ret = MathUtility.add(ret, getAns(n - 1, count - 1, sum - k, x), MOD);
                }
            }
            for (int k = x+1; k <= 10; k++) {
                ret = MathUtility.add(ret, getAns(n-1, count, sum-k, x), MOD);
            }
            dp[n][count][sum] = ret;
            return ret;
        }
    }
}
