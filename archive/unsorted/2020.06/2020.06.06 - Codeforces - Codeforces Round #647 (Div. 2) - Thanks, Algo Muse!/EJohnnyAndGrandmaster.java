package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MathUtility;

import java.util.Arrays;
import java.util.Comparator;

public class EJohnnyAndGrandmaster {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int p = in.nextInt();
        int[] k = in.nextIntArray(n);

        int[] coeff = new int[n];
        int v = 0;
        boolean tooBig = false;
        int MOD = 1000000007;
        k = Arrays.stream(k).boxed().sorted(Comparator.reverseOrder()).mapToInt(i -> i).toArray();
        for (int i = 0; i < n; i++) {
            if (tooBig) {
                coeff[i] = -1;
            } else {
                if (v == 0) {
                    v = 1;
                    coeff[i] = 1;
                } else {
                    int diff = k[i - 1] - k[i];
                    tooBig = isTooBig(v, p, diff, n);
                    if (!tooBig) {
                        v *= MathUtility.pow(p, diff, MOD);
                        v--;
                        coeff[i] = -1;
                    } else {
                        coeff[i] = -1;
                    }
                }
            }
        }

        int ans = 0;
//        int[] cache = new int[1000001];
//        cache[0] = 1;
//        for (int i = 0; i <= 1000000; i++) {
//
//        }
        int prev = 0;
        for (int i = 0; i < n; i++) {
            int mult;
            if (i!=0 && k[i] == k[i-1]) {
                mult = prev;
            } else {
                mult = MathUtility.pow(p, k[i], MOD);
            }
            prev = mult;
            if (coeff[i] == 1) {
                ans = MathUtility.add(ans, mult, MOD);
            } else {
                ans = MathUtility.sub(ans, mult, MOD);
            }
        }
        out.println(ans);
    }

    private boolean isTooBig(long v, int p, int diff, int n) {
        int x = 1;
        long px = p;
        while (x <= diff) {
            long term = v * px;
            if (term > n) return true;
            x<<=1;
            px *= px;
        }
        return false;
    }
}
