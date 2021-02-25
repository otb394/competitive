package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MathUtility;

public class BOmkarAndLastClassOfMath {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        if ((n&1) == 0) {
            int m = n>>1;
            out.println(m + " " + m);
        } else {
            int[] factors = MathUtility.factorize(n);
            long ans = n-1L;
            int af, as;
            af = 1;
            as = n-1;
            for (int fact : factors) {
                int s = n-fact;
                if (s > 0) {
                    long cand = MathUtility.lcm(fact, s);
                    if (cand < ans) {
                        ans = cand;
                        af = fact;
                        as = s;
                    }
                }
//                for (int i = fact; i < n; i+=fact) {
//                    int s = n-i;
//                    if (s <= 0) {
//                        break;
//                    }
//                    long cand = MathUtility.lcm(i, s);
//                    if (cand < ans) {
//                        ans = cand;
//                        af = i;
//                        as = s;
//                    }
//                }
            }
            out.println(af + " " + as);
        }
    }
}
