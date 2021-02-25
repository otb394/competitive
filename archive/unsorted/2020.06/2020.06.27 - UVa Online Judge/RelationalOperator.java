package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class RelationalOperator {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long a = in.nextLong();
        long b = in.nextLong();
        if (a < b) {
            out.println("<");
        } else if (a > b) {
            out.println(">");
        } else {
            out.println("=");
        }
    }
}
