package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class AMinimalSquare {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        if (a < b ) {
            int te = a;a = b;b = te;
        }
        int f = (a+b)*(a+b);
        int s;
        if (b <= a/2) s = a*a;
        else s = 4*b*b;
        int ans = Math.min(f,s);
        out.println(ans);
    }
}
