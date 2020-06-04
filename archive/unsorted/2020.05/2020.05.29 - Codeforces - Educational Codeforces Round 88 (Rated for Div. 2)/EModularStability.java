package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MathUtility;

public class EModularStability {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int ans = 0;
        int MOD = 998244353;
        int MAXN = 500003;
        int[] fact = new int[MAXN];
        fact[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            fact[i] = MathUtility.mult(fact[i-1], i, MOD);
        }
        for (int i = 1; i <= n; i++) {
            int q = n/i;
            if (q >= k) {
                int op = q-1;
                int ch = k-1;
                ans = MathUtility.add(ans, C(op, ch, fact, MOD), MOD);
            }
        }
        out.println(ans);
    }

    private int C(int n, int r, int[] fact, int MOD) {
        if (n >= r) {
            int num = fact[n];
            int denom = MathUtility.mult(fact[r], fact[n - r], MOD);
            return MathUtility.dividePrime(num, denom, MOD);
        } else {
            return 0;
        }
    }
}
