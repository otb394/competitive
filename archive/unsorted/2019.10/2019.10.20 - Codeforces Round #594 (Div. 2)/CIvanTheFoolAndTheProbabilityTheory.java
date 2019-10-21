package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MathUtility;

import static util.MathUtility.add;
import static util.MathUtility.mult;
import static util.MathUtility.sub;

public class CIvanTheFoolAndTheProbabilityTheory {
    private static final int MOD = 1000000007;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();int m=in.nextInt();
        int l=Math.max(n,m);int fn=1;int fm=1;
        int[][] prev = new int[2][2];
        int[][] next = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                prev[i][j]=1;
            }
        }
        for (int c = 2; c <= l; c++) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if (j == 1) {
                        next[i][j] = add(prev[i][other(j)],  prev[other(i)][j], MOD);

                    } else {
                        next[i][j] = prev[other(i)][other(j)];
                    }
                }
            }
            if (c == n) fn=next[1][1];
            if (c==m) fm=next[1][1];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    prev[i][j]=next[i][j];
                }
            }
        }
        int te=sub(fm, 1, MOD);
        int ans = add(fn, te, MOD);
        ans=mult(ans,2,MOD);
        out.print(ans);
    }

    private int other(int i) {
        if (i==0) return 1;
        return 0;
    }
}
