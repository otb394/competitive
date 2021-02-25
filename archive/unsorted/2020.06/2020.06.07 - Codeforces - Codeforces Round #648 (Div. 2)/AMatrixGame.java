package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class AMatrixGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();
            }
        }
        int r = 0;
        int c = 0;
        boolean[] rb = new boolean[n];
        boolean[] cb = new boolean[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 1) {
                    rb[i]=true;
                    cb[j] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (!rb[i]) r++;
        }
        for (int i = 0; i < m; i++) {
            if (!cb[i]) c++;
        }
        int t = Math.min(r,c);
        if ((t&1) == 0) {
            out.println("Vivek");
        } else {
            out.println("Ashish");
        }
    }
}
