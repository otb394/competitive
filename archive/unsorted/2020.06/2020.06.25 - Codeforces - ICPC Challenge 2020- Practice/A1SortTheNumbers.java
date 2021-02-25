package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;

public class A1SortTheNumbers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        Arrays.sort(a);
        out.print(a);
    }
}
