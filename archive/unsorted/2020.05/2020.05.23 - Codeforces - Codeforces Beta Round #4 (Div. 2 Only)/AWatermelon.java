package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class AWatermelon {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int w = in.nextInt();
        String ans = (((w%2) == 0) && w>=4) ? ("YES") : "NO";
        out.print(ans);
    }
}
