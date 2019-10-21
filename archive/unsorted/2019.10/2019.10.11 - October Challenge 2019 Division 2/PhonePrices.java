package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class PhonePrices {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] p = in.nextIntArray(n);
        int ans = 1;
        for (int i = 1; i < n; i++) {
            int l = Math.max(i-5, 0);
            boolean smallest = true;
            for (int j = l; j < i; j++) {
                if (p[j] <= p[i]) {
                    smallest = false;
                    break;
                }
            }
            if (smallest) ans++;
        }
        out.println(ans);
    }
}
