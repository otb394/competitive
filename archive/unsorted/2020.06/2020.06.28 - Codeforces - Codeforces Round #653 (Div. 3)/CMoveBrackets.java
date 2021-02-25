package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class CMoveBrackets {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        String s = in.nextString();
        int ans = 0;
        int val = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (s.charAt(i) == '(') {
                val++;
            } else {
                val--;
            }
            if (val < 0) {
                ans = Math.min(ans, val);
            }
        }
        out.println(-ans);
    }
}
