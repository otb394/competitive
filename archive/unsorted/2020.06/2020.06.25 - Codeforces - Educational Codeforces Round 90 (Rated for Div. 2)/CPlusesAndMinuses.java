package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class CPlusesAndMinuses {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.nextString();
        int n = s.length();
        int N = n+1;
        long[] val = new long[N];
        int curr = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '+') {
                curr++;
            } else {
                curr--;
            }
            if (curr < 0 && val[-curr] == 0) {
                val[-curr] = i+1;
            }
        }
        long ans = 0L;
        for (int i = 0; i < N; i++) {
            ans += val[i];
        }
        out.println(ans + ((long)n));
    }
}
