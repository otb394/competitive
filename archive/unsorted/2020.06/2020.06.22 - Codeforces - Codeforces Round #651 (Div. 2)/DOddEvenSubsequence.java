package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import algorithms.BinarySearch;

import java.util.function.Function;

public class DOddEvenSubsequence {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = in.nextIntArray(n);
        int mxVal = Integer.MIN_VALUE;
        int mnVal = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (a[i] > mxVal) mxVal = a[i];
            if (a[i] < mnVal) mnVal = a[i];
        }
        Function<Integer, Boolean> func = val -> getVal(a, k, n, val);
        int ans = BinarySearch.searchFirstOne(mnVal-1, mxVal, func);
        out.println(ans);
    }

    private boolean getVal(int[] a, int k, int n, int val) {
        if ((k&1) == 0) {
            int ls = k/2;
            int fir = getValUtil(a, 0, n-2, val);
            if (fir >= ls) return true;
            int sec = getValUtil(a, 1, n-1, val);
            return sec >= ls;
        } else {
            int ls = k/2;
            int inner = getValUtil(a, 1, n-2, val);
            if (inner >= ls) return true;
            int outer = getValUtil(a, 0, n-1, val);
            return outer >= (ls+1);
        }
    }

    private int getValUtil(int[] a, int start, int end, int val) {
        if (start == end) {
            if (a[start] <= val) return 1;
            return 0;
        }
        if (start > end) return 0;
        int dpz = (a[start] <= val) ? 1 : 0;
        int dpo = (a[start+1] <= val) ? 1 : dpz;
        for (int i = start+2; i <= end; i++) {
            int td;
            if (a[i] <= val) {
                td = Math.max(dpz + 1, dpo);
            } else {
                td = dpo;
            }
            dpz = dpo;
            dpo = td;
        }
        return dpo;
    }
}
