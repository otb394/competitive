package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MathUtility;
import util.MiscUtility;

public class AlienPiano {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int k = in.nextInt();
        int[] a = in.nextIntArray(k);
        int[][] dp = new int[k][4];
        for (int j = 0; j < 4; j++) {
            dp[0][j] = 0;
        }
        int INF = k+1;
        for (int j = 1; j < k; j++) {
            for (int l = 0; l < 4; l++) {
                if (a[j] == a[j-1]) {
                    dp[j][l] = dp[j-1][l];
                    continue;
                }
                int tans = INF;
                for (int i = 0; i < 4; i++) {
                    tans = Math.min(tans, dp[j-1][i] + 1);
                }
                if (a[j] > a[j-1]) {
                    for (int i = 0; i < l; i++) {
                        tans = Math.min(tans, dp[j - 1][i]);
                    }
                } else {
                    for (int i = l+1; i < 4; i++) {
                        tans = Math.min(tans, dp[j - 1][i]);
                    }
                }
                dp[j][l] = tans;
            }
        }
        int ans = INF;
        for (int i = 0; i < 4; i++) {
            ans = Math.min(ans, dp[k -1][i]);
        }
        out.printf("Case #%d: %d\n", testNumber, ans);
    }
}
