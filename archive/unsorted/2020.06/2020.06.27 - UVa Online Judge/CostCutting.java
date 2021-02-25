package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;

public class CostCutting {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] a = in.nextIntArray(3);
        Arrays.sort(a);
        out.printf("Case %d: %d\n", testNumber, a[1]);
    }
}
