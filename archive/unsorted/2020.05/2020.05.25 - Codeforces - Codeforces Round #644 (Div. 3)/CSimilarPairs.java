package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;

public class CSimilarPairs {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        boolean ok;
        int e = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {
                e++;
            }
        }
        if ((e%2) == 0) {
            ok = true;
        } else {
            boolean pr = false;
            Arrays.sort(a);
            for (int i = 0; i < (n-1); i++) {
                int diff = a[i+1]-a[i];
                if (diff == 1) {
                    pr = true;
                    break;
                }
            }
            ok = pr;
        }
        String ans = ok ? "YES" : "NO";
        out.println(ans);
    }
}
