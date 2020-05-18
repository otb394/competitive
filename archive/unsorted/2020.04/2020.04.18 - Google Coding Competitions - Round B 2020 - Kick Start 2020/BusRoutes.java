package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;

public class BusRoutes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        long d = in.nextLong();
        long[] x = in.nextLongArray(n);
        long ans = d;
        for (int i = n-1; i >= 0; i--) {
            ans = getLastMult(ans, x[i]);
        }
//        long minLastMult = Arrays.stream(x).map(val -> getLastMult(d, val)).min()
//                .orElseThrow(() -> new RuntimeException("Something"));
//        long ans = getLastMult(minLastMult, x[0]);
        out.println(String.format("Case #%d: %d", testNumber, ans));
    }

    private long getLastMult(long num, long d) {
        return (num / d) * d;
    }
}
