package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class CoronavirusSpread2 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] v = in.nextIntArray(n);
        int bst = n;
        int wrst = 1;
        int INF = 6;
        for (int i = 0; i < n; i++) {
            int inf = 1;
            int maxSpeed = v[i];
            for (int j = i-1; j >= 0; j--) {
                if (v[j] > v[i]) {
                    inf++;
                    maxSpeed = Math.max(maxSpeed, v[j]);
                }
            }
            int slowest = INF;
            for (int j = i+1; j < n; j++) {
                if (v[j] < maxSpeed) {
                    inf++;
                    slowest = Math.min(slowest, v[j]);
                }
            }
            for (int j = 0; j < i; j++) {
                if (v[j] <= v[i] && v[j] > slowest) {
                    inf++;
                }
            }
            bst = Math.min(bst, inf);
            wrst = Math.max(wrst, inf);
        }
        out.println(bst + " " + wrst);
    }
}
