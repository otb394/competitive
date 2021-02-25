package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DMaximumSumOnEvenPositions {
    MiscUtility ut;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        ut = new MiscUtility(false);
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        if (n == 1) {
            out.println(a[0]);
            return;
        }
        long[] dp = new long[n];
        dp[1] = Math.max(0, a[1] - a[0]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(0, dp[i-2] + (a[i] - a[i-1]));
        }
        ut.printDebug(Arrays.stream(dp).boxed().collect(Collectors.toList()), "dp");
        long sum = Long.MIN_VALUE;
        for (int i = 1; i < n; i+=2) {
            sum = Math.max(sum, dp[i]);
        }

        long[] dpp = new long[n];
        dpp[1] = Math.max(0, a[0] - a[1]);
        for (int i = 2; i < n; i++) {
            dpp[i] = Math.max(0, dpp[i-2] + (a[i-1] - a[i]));
        }
        long summ = Long.MIN_VALUE;
        for (int i = 0; i < n; i+=2) {
            summ = Math.max(summ, dpp[i]);
        }

        long ans = 0L;
        for (int i = 0; i < n; i+=2) {
            ans += a[i];
        }
        out.println(ans + Math.max(sum, summ));
    }
}
