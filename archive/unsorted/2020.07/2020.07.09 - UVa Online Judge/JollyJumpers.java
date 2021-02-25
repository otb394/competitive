package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JollyJumpers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (!in.isExhausted()) {
            int n = in.nextInt();
            long[] a = in.nextLongArray(n);
            boolean yes;
            if (n == 1) {
                yes = true;
            } else {
                long[] diffs = new long[n-1];
                int z = 0;
                for (int i = 0; i < (n-1); i++) {
                    diffs[z] = Math.abs(a[i] - a[i+1]);
                    z++;
                }
                Arrays.sort(diffs);
                yes = true;
                for (int i = 0; i < (n-1); i++) {
                    if (diffs[i] != (i+1)) {
                        yes = false;
                        break;
                    }
                }
            }
            out.println(yes ? "Jolly" : "Not jolly");
        }
    }
}
