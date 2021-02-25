package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class EMaximumSubsequenceValue {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        long[] a = in.nextLongArray(n);
        long ans = 0L;
        if (n == 1) {
            ans = a[0];
        } else if (n == 2) {
            ans = a[0] | a[1];
        } else {
            for (int i = 0; i < (n-2); i++) {
                for (int j = i+1; j < (n-1); j++) {
                    long val = a[i] | a[j];
                    for (int k = j+1; k < n; k++) {
                        long vall = val | a[k];
                        ans = Math.max(ans, vall);
                    }
                }
            }
        }
        out.println(ans);
    }
}
