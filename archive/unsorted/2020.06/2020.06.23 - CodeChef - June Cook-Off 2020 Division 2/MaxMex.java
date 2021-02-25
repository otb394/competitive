package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class MaxMex {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = in.nextIntArray(n);
        boolean[] dp = new boolean[m];
        int c = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] < m) {
                dp[a[i]] = true;
            } else if (a[i] == m) {
                c++;
            }
        }
        for (int i = 1; i < m; i++) {
            if (!dp[i]) {
                out.println(-1);
                return;
            }
        }
        out.println(n - c);
    }
}
