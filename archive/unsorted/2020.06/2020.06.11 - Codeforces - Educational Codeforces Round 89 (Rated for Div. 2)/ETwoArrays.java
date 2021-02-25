package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MathUtility;

public class ETwoArrays {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] b = in.nextIntArray(m);
        int MOD = 998244353;
        if (n < m) {
            out.println(0);
            return;
        }
        int[] post = new int[n];
        post[n-1] = a[n-1];
        for (int i = n-2; i >= 0; i--) {
            post[i] = Math.min(post[i+1], a[i]);
        }
        if (post[0] != b[0]) {
            out.println(0);
            return;
        }
        if (m == 1) {
            out.println(1);
            return;
        }
        int ans = 1;
        int i = 0;
        int j = 1;
        while (i < n && post[i] < b[1]) i++;
        while (i < n && j < m) {
            if (post[i] != b[j]) {
                out.println(0);
                return;
            }
            int k = i+1;
            while (k < n && post[k] == b[j]) k++;
            ans = MathUtility.mult(ans, k-i, MOD);
            j++;
            i = k;
            if (j < m) {
                while (i < n && post[i] < b[j]) i++;
            }
        }
        if (j == m) {
            out.println(ans);
        } else {
            out.println(0);
        }
    }
}
