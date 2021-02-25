package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class AnotherCardGameProblem {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int sa = getAns(a);
        int sb = getAns(b);
        if (sa < sb) {
            out.println("0 " + sa);
        } else {
            out.println("1 " + sb);
        }
    }

    private int getAns(int num) {
        if (num == 0) {
            return 1;
        } else {
            int q = num / 9;
            int r = num % 9;
            int ret = 0;
            ret += q;
            if (r != 0) {
                ret++;
            }
            return ret;
        }
    }
}
