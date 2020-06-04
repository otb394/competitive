package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class AWayTooLongWords {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s=in.nextString();
        int n=s.length();
        if (n>10) {
            out.println(s.charAt(0) + Integer.toString(n - 2) + s.charAt(n - 1));
        } else {
            out.println(s);
        }
    }
}
