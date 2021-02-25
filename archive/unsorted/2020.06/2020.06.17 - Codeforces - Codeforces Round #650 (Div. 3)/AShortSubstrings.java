package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class AShortSubstrings {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.nextString();
        int n = s.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i+=2) {
            builder.append(s.charAt(i));
        }
        builder.append(s.charAt(n-1));
        out.println(builder.toString());
    }
}
