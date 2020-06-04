package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import algorithms.BinarySearch;

import java.util.function.Function;

public class DMultiset {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] k = in.nextIntArray(q);
        Function<Integer,Boolean> f = x -> (func(x, n,q,a,k)>0);
        int ans = BinarySearch.searchFirstOne(1, n+1, f);
        if (ans == (n+1)) {
            out.println(0);
        } else {
            out.println(ans);
        }
    }

    private int func(int x, int n, int q, int[] a, int[] k) {
        int l = 0;
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] <= x) {
                l++;
            } else {
                r++;
            }
        }
        for (int i = 0; i < q; i++) {
            if (k[i] < 0) {
                int order = -k[i];
                if (order <= l) {
                    l--;
                } else {
                    r--;
                }
            } else {
                if (k[i] <= x) {
                    l++;
                } else {
                    r++;
                }
            }
        }
        return l;
    }
}
