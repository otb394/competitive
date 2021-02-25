package code;

import algorithms.BinarySearch;

import java.util.function.Function;

public class AqaAsadiGroups {
    public long minimumDifference(int[] skills, int k) {
        int n = skills.length;
        long[] dp = new long[n];
        long[] pre = new long[n];
        pre[0] = skills[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i-1] + skills[i];
        }
        if (k > n) {
            long val = 0L;
            for (int skill : skills) {
                val += sq(skill);
            }
            return k * (k * val - sq(pre[n-1]));
        }
        for (int i = n-1; i >= 0; i--) {
            long s = getSum(i, n-1, pre);
            dp[i] = s * s;
        }
        for (int j = 2; j <= k; j++) {
            long[] ndp = new long[n];
            for (int i = n-j; i < n; i++) {
                ndp[n-j] += sq(skills[i]);
            }
            for (int i = n-j-1; i >= 0; i--) {
                long sum = getSum(i, n-1, pre);
                double tar = ((double)sum) / ((double)k);
                int endInc = n-j;
                int finalI = i;
                Function<Integer, Boolean> func = ind -> getSum(finalI, ind, pre) >= tar;
                int geq = BinarySearch.searchFirstOne(i-1, endInc+1, func);
                if (geq == (endInc + 1)) {
                    ndp[i] = sq(getSum(i, endInc, pre)) + dp[endInc+1];
                } else if (geq == i) {
                    ndp[i] = sq(skills[i]) + dp[i+1];
                } else {
                    ndp[i] = Math.min(sq(getSum(i, geq, pre)) + dp[geq+1], sq(getSum(i, geq-1, pre)) + dp[geq]);
                }
            }
            System.arraycopy(ndp, 0, dp, 0, n);
        }
        long val = dp[0];
        return k * (k * val - sq(pre[n-1]));
    }

    private long sq(long x) {
        return x*x;
    }

    private long getSum(int i, int j, long[] pre) {
        return (i == 0) ? pre[j] : pre[j] - pre[i-1];
    }
}
