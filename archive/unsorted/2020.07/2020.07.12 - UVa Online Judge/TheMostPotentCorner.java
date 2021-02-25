package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class TheMostPotentCorner {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (true) {
            try {
                int n = in.nextInt();
                int N = (1<<n);
                long[] w = in.nextLongArray(N);
                long[] p = new long[N];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < n; j++) {
                        int nb = i ^ (1<<j);
                        p[i] += w[nb];
                    }
                }
                long ans = -1;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < n; j++) {
                        int nb = i ^ (1<<j);
                        ans = Math.max(ans, p[i] + p[nb]);
                    }
                }
                out.println(ans);
            } catch (Exception e) {
                break;
            }
        }
    }
}
