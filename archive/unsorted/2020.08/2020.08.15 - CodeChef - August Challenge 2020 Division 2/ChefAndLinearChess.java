package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;

public class ChefAndLinearChess {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] p = in.nextIntArray(n);
        Arrays.sort(p);
        for (int i = n-1; i >= 0; i--) {
            if (k % p[i] == 0) {
                out.println(p[i]);
                return;
            }
        }
        out.println(-1);
    }
}
