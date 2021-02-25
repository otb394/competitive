package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;

public class WatchingCPL {
    boolean[] dp;
    boolean[] temp;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] h = in.nextIntArray(n);
        dp = new boolean[2*k+1];
        temp = new boolean[2*k + 1];
        out.println(getAns(n, k, h));
    }

    private int getAns(int n, int k, int[] h)  {
        if (n == 1) return -1;
        Arrays.sort(h);
        if (h[n-1] >= k)  {
            long t = 0L;
            for (int i = n-2; i >= 0; i--) {
                t+=h[i];
                if (t >= k) {
                    return n-i;
                }
            }
            return -1;
        }
        dp[h[n-1]] = true;
        dp[0] = true;
        long tot = h[n-1];
        int tok = 2*k;
        for (int i = n-2; i>=0; i--) {
            tot += h[i];
            int mx = (int)Math.min(tot - k, 2*k);
            Arrays.fill(temp, false);
            temp[0] = true;
            for (int tk = 1; tk <= tok; tk++) {
                temp[tk] = dp[tk] || (tk >= h[i] && dp[tk - h[i]]);
            }
            for (int l = k; l <= mx ; l++) {
                if (temp[l]) {
                    return n-i;
                }
            }
            System.arraycopy(temp, 0, dp, 0, tok+1);
        }
        return -1;
    }
}
