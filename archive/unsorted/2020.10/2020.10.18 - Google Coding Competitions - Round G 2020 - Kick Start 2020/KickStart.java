package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class KickStart {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.nextString();
        int len = s.length();
        int st = 0;
        int kc = 0;
        long ans = 0L;
        for (int i = len - 4; i >= 0; i--) {
            if (i <= (len - 5) && s.charAt(i) == 'S' && s.charAt(i+1) == 'T' && s.charAt(i+2) == 'A'
                    && s.charAt(i+3) == 'R' && s.charAt(i+4) == 'T') {
                st++;
            }
            if (s.charAt(i) == 'K' && s.charAt(i+1) == 'I' && s.charAt(i+2) == 'C' && s.charAt(i+3) == 'K') {
                ans += st;
            }
        }
        out.printf("Case #%d: %d\n", testNumber, ans);
    }
}
