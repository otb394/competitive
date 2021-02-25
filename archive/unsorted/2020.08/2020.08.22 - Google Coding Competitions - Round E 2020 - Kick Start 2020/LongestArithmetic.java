package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;
import util.Suppliers;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LongestArithmetic {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            if ((a[i-1] - a[i]) == (a[i-2] - a[i-1])) {
                dp[i] = dp[i-1] + 1;
            } else {
                dp[i] = 2;
            }
        }
        ut.printDebug(Suppliers.of(Arrays.stream(dp).boxed().collect(Collectors.toList())), "dp");
        int ans = 2;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }
        out.printf("Case #%d: %d\n", testNumber, ans);
    }
}
