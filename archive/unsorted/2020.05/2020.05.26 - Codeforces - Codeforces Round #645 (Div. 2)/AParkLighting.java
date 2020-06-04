package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MathUtility;

public class AParkLighting {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int some = n/2 * m;
        if ((n&1) != 0) {
            some+= MathUtility.ceil(m, 2);
        }
        out.println(some);
    }
}
