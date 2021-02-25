package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class ChefAndStrings {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] s = in.nextIntArray(n);
        long ans = 0L;
        for (int i = 1; i < n; i++) {
            ans += Math.abs(s[i] - s[i-1]) - 1;
        }
        out.println(ans);
    }
}
