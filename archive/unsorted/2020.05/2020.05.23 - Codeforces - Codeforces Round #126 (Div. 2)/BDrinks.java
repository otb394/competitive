package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class BDrinks {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] p = in.nextIntArray(n);
        int num = 0;
        for (int i = 0; i < n; i++) {
            num += p[i];
        }
        int denom = n * 100;
        double ans = ((double) num) / ((double) denom);
        out.print(ans * 100.0);
    }
}
