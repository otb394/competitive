package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class ChefAndPriceControl {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] p = in.nextIntArray(n);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (p[i] > k) ans+=p[i]-k;
        }
        out.println(ans);
    }
}
