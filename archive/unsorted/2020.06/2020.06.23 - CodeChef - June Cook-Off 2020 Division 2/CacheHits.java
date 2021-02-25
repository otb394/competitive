package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class CacheHits {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int b = in.nextInt();
        int m = in.nextInt();
        int[] x = in.nextIntArray(m);
        int cnt = 0;
        int curr = -1;
        for (int i = 0; i < m; i++) {
            int cb = x[i] / b;
            if (curr == -1 || curr != cb) {
                curr = cb;
                cnt++;
            }
        }
        out.println(cnt);
    }
}
