package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class TheTomAndJerryGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long ts = in.nextLong();
        if ((ts&1) != 0) {
            out.println(ts / 2L);
        } else {
            long ans = ts>>(Long.numberOfTrailingZeros(ts)+1);
            out.println(ans);
        }
    }
}
