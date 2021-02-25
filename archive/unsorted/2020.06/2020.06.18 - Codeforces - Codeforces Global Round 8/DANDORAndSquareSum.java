package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;

public class DANDORAndSquareSum {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        long[] a = in.nextLongArray(n);
        Arrays.sort(a);
        if (a[n - 1] == 0L) {
            out.println(0);
            return;
        }
        int mx = (64 - Long.numberOfLeadingZeros(a[n - 1])) - 1;
        for (int i = mx; i >= 0; i--) {
            int j = 0;
            int k = n-1;
            while (j < k) {
                while (k > j && (getVal(a[k], i) > 0L)) k--;
                while (j < k && (getVal(a[j], i) == 0L)) j++;
                if (j < k) {
                    long tj = a[j] & a[k];
                    long tk = a[j] | a[k];
                    a[j] = tj;
                    a[k] = tk;
                }
            }
        }
        long ans = 0L;
        for (int i = 0; i < n; i++) {
            ans += sq(a[i]);
        }
        out.println(ans);
    }

    private long sq(long x) {
        return x * x;
    }

    private long getVal(long num, int ind) {
        return (num & (1L<<ind));
    }
}
