package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.util.HashSet;
import java.util.Set;

public class ExactSum {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (true) {
            if (in.isExhausted()) {
                return;
            }
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);
            int m = in.nextInt();
            Set<Integer> st = new HashSet<>();
            for (int i = 0; i < n; i++) {
                st.add(a[i]);
            }
            int ans = -1;
            int ad = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                int oth = m - a[i];
                if (st.contains(oth)) {
                    int diff = Math.abs(oth - a[i]);
                    if (diff < ad) {
                        ad = diff;
                        ans = a[i];
                    }
                }
            }
            int ls = Math.min(ans, m - ans);
            ut.printDebug(ls, "ls");
            out.printf("Peter should buy books whose prices are %d and %d.\n\n", ls, m - ls);
        }
    }
}
