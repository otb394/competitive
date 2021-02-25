package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;

public class AThreePairwiseMaximums {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] a = in.nextIntArray(3);
        Arrays.sort(a);
        if (a[1] != a[2]) {
            out.println("NO");
        } else {
            out.println("YES");
            out.println(a[0] + " " + a[0] + " " + a[1]);
        }
    }
}
