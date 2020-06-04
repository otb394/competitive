package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class DYetAnotherYetAnotherTask {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[][] pre = new int[61][n];
        int[][] post = new int[61][n];
        for (int i = 0; i <= 60; i++) {
            int mxVal = i-30;
            if (a[0] <= mxVal) {
                pre[i][0] = a[0];
            }
            for (int j = 1; j < n; j++) {
                if (a[j] <= mxVal) {
                    int add = Math.max(0, pre[i][j-1]);
                    pre[i][j] = a[j] + add;
                }
            }
        }
        for (int i = 0; i <= 60; i++) {
            int mxVal = i-30;
            if (a[n-1] <= mxVal) {
                post[i][n-1] = a[n-1];
            }
            for (int j = n-2; j >= 0; j--) {
                if (a[j] <= mxVal) {
                    int add = Math.max(0, post[i][j+1]);
                    post[i][j] = a[j] + add;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int ind = a[i] + 30;
            int f = (i > 0 && pre[ind][i-1] > 0) ? pre[ind][i-1] : 0;
            int s = (i < (n-1) && post[ind][i+1] > 0) ? post[ind][i+1] : 0;
            ans = Math.max(ans, f+s);
        }
        out.println(ans);
    }
}
