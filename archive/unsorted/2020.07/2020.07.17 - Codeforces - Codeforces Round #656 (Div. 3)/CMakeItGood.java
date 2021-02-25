package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import algorithms.BinarySearch;

import java.util.function.Function;

public class CMakeItGood {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        Function<Integer, Boolean> fun = k -> check(a, n, k);
        int ans = BinarySearch.searchFirstOne(-1, n-1, fun);
        out.println(ans);
    }

    private boolean check(int[] a, int n, int k) {
        int mx = 0;
        int mxi = -1;
        for (int i = k; i < n; i++) {
            if (a[i] > mx) {
                mx = a[i];
                mxi = i;
            }
        }
        for (int i = mxi+1; i < n; i++) {
            if (a[i] > a[i-1]) {
                return false;
            }
        }
        for (int i = mxi-1; i >= k; i--) {
            if (a[i+1] < a[i]) {
                return false;
            }
        }
        return true;
    }
}
