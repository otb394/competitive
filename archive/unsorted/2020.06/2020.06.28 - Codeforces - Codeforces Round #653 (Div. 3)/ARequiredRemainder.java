package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class ARequiredRemainder {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x = in.nextInt();
        int y = in.nextInt();
        int n = in.nextInt();
        int rem = n-y;
        int q = rem / x;
        out.println(x * q + y);
    }
}
