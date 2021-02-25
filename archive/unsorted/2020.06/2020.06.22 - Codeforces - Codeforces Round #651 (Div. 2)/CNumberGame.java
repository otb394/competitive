package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MathUtility;

public class CNumberGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        boolean first;
        if (n == 1) {
            first = false;
        } else if (n == 2) {
            first = true;
        } else if (n % 2 != 0) {
            first = true;
        } else if (MathUtility.isPowerOfTwo(n)) {
            first = false;
        } else if ((n/2)%2 != 0) {
            int val = n/2;
            first = !isPrime(val);
        } else {
            first = true;
        }
        out.println(first ? "Ashishgup" : "FastestFinger");
    }

    private boolean isPrime(int n) {
        if (n == 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        int[] factors = MathUtility.factorize(n);
        return factors.length == 2;
    }
}
