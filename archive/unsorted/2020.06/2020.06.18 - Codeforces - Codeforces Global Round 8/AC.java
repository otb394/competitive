package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class AC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long a = in.nextLong();
        long b = in.nextLong();
        long n = in.nextLong();
        if (a < b) {
            long temp = a;
            a = b;
            b = temp;
        }
        int cnt = 0;
        while (a <= n) {
            long ta = a+b;
            long tb = a;
            a = ta;
            b = tb;
            cnt++;
        }
        out.println(cnt);
    }
}
