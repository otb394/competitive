package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class BEvenArray {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int e,o;
        if (n%2 == 0) {
            e = n/2; o = n/2;
        } else {
            o = n/2; e = n-o;
        }
        int ae = 0;
        int ao = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {
                ae++;
            } else {
                ao++;
            }
        }
        if (ae != e || ao != o) {
            out.println(-1);
            return;
        }
        int miss = 0;
        for (int i = 0; i < n; i+=2) {
            if (a[i] % 2 != 0) {
                miss++;
            }
        }
        out.println(miss);
    }
}
