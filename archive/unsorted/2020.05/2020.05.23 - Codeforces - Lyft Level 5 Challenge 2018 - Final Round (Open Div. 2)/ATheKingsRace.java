package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class ATheKingsRace {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextLong();
        long x = in.nextLong(); long y = in.nextLong();
        long w = steps(x-1L, y-1L); long b = steps(n-x, n-y);
        if (w <= b) {
            out.print("White");
        } else {
            out.print("Black");
        }
    }

    public long steps(long x, long y) {
        return Math.max(x,y);
    }
}
