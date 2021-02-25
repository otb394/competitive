package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class B01Game {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.nextString();
        int co = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                co++;
            }
        }
        int cz = n-co;
        int turns = Math.min(co, cz);
        if ((turns&1) == 0) {
            out.println("NET");
        } else {
            out.println("DA");
        }
    }
}
