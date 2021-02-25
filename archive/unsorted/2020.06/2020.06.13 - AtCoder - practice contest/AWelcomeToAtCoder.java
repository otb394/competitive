package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class AWelcomeToAtCoder {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        String s = in.nextString();
        out.print((a + b + c) + " " + s);
    }
}
