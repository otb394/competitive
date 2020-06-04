package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MathUtility;

public class AAlarmClock {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long a = in.nextLong();
        long b = in.nextLong();
        long c = in.nextLong();
        long d = in.nextLong();
        long ans;
        if (b >= a) {
            ans = b;
        } else {
            long rem = a-b;
            if (c <= d) {
                ans = -1L;
            } else {
                long gain = c-d;
                long cycles = MathUtility.ceil(rem, gain);
                ans = b + cycles * c;
            }
        }
        out.println(ans);
    }
}
