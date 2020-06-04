package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MathUtility;

public class DBuyingShovels {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] factors = MathUtility.factorize(n);
        int ans = 1;
        int len = factors.length;
        for (int i = 0; i < len; i++) {
            if (factors[i] <= k && factors[i] > ans) ans = factors[i];
        }
        int an = n/ans;
        out.println(an);
    }
}
