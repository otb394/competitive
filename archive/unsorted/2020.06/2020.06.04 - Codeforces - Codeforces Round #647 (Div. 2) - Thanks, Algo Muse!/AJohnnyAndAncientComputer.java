package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class AJohnnyAndAncientComputer {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long a = in.nextLong();
        long b = in.nextLong();
        if (getRes(a) != getRes(b)) {
            out.println(-1);
            return;
        }
        if (a < b) {
            long temp = a;
            a = b;
            b = temp;
        }
        int diff = 0;
        while (a > b) {
            a>>=1L;
            diff++;
        }
        int ans = 0;
        ans += diff/3;
        diff%=3;
        ans += diff/2;
        diff%=2;
        ans += diff;
        out.println(ans);
    }

    private long getRes(long x) {
        while(x!= 0L && (x&1L) == 0L) {
            x>>=1L;
        }
        return x;
    }
}
