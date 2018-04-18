package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import static util.MathUtility.mult;
import static util.MathUtility.pow;

public class WeightOfNumbers {
    private static final int MOD = 1000000007;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();
        while(t-- > 0) {
            long n = in.nextLong();
            int w = in.nextInt();
            out.println(solve(n, w));
        }
    }

    private int solve(long n, int w) {
        return mult(countW(w), pow(10, n-2, MOD), MOD);
    }

    private int countW(int w) {
        int cnt = 0;
        for (int i = 9; i >= 0 ; i--) {
            int t = i-w;
            if (t>=1 && t<=9) cnt++;
        }
        return cnt;
    }
}
