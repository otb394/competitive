package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class BShuffle {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        int m = in.nextInt();
        int al = x;
        int ar = x;
        for (int i = 0; i < m; i++) {
            int l = in.nextInt();
            int r = in.nextInt();
            if (ar < l || al > r) continue;
            al = Math.min(al, l);
            ar = Math.max(ar, r);
        }
        out.println(ar - al + 1);
    }
}
