package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class EPolygon {
    boolean[][] dp;
    boolean[][] done;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        String[] mat = new String[n];
        for (int i = 0; i < n; i++) {
            mat[i] = in.nextString();
        }
        dp = new boolean[n][n];
        done = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i].charAt(j) == '1') {
                    if (!isValid(i,j,n,mat)) {
                        out.println("NO");
                        return;
                    }
                }
            }
        }
        out.println("YES");
    }

    private boolean isValid(int i, int j,int n, String[] mat) {
        if (done[i][j]) {
            return dp[i][j];
        }
        char ch = mat[i].charAt(j);
        if (ch == '0') {
            done[i][j] = true;
            return false;
        }
        if (i == n-1 || j == n-1) {
            done[i][j]=true;
            dp[i][j] = true;
            return true;
        }
        dp[i][j] = isValid(i+1,j,n,mat) || isValid(i, j+1,n,mat);
        done[i][j] = true;
        return dp[i][j];
    }
}
