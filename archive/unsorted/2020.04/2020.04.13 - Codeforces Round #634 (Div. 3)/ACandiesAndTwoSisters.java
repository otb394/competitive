package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class ACandiesAndTwoSisters {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();
        int ans = (n%2 == 0) ? (n/2-1) : n/2;
        out.println(ans);
    }
}
