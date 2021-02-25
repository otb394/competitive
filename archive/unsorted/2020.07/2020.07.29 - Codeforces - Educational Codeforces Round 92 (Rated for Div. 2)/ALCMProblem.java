package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class ALCMProblem {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int l = in.nextInt();
        int r = in.nextInt();
        int oth = l << 1;
        if (oth > r) {
            out.println(-1 + " " + -1);
        } else {
            out.println(l + " " + oth);
        }
    }
}
