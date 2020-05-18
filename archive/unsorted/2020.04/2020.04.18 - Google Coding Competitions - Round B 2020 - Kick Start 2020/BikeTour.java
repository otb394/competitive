package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class BikeTour {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();
        int[] a=in.nextIntArray(n);
        int ans = 0;
        for (int i = 1; i < (n-1); i++) {
            if (a[i] > a[i-1] && a[i] > a[i+1]) {
                ans++;
            }
        }
        out.println(String.format("Case #%d: %d", testNumber, ans));
    }
}
