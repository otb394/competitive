package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class HorrorDash {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, a[i]);
        }
        out.printf("Case %d: %d\n", testNumber, ans);
    }
}
