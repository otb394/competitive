package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class AChoosingTeams {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] y = in.nextIntArray(n);
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (y[i] <= (5-k)) {
                cnt++;
            }
        }
        int ans = cnt/3;
        out.print(ans);
    }
}
