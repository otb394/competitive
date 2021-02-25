package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class FRotatingSubstrings {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        String s = in.nextString();
        String t = in.nextString();
        int[] sc = new int[26];
        for (int i = 0; i < n; i++) {
            sc[s.charAt(i)-'a']++;
        }
        int[] tc = new int[26];
        for (int i = 0; i < n; i++) {
            tc[t.charAt(i)-'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (sc[i] != tc[i]) {
                out.println(-1);
                return;
            }
        }
        int INF = 11234;
        int[][] dp = new int[n][n];
        dp[n-1][n-1] = (s.charAt(n-1) == t.charAt(n-1)) ? (0) : INF;
        for (int i = n-2; i >= 0; i--) {
            dp[i][n-1] = (s.charAt(i) == t.charAt(n-1)) ? (n-i-1) : (dp[i+1][n-1] + 1);
        }
        for (int j = 0; j < (n-1); j++) {
            dp[n-1][j] = INF;
        }
        int[][] pre = new int[26][n];
        int[][] pret = new int[26][n];
        pre[s.charAt(0)-'a'][0]=1;
        pret[t.charAt(0)-'a'][0]=1;
        for (int i = 1; i < n; i++) {
            int ch = s.charAt(i)-'a';
            for (int j = 0; j < 26; j++) {
                pre[j][i] = pre[j][i-1];
            }
            pre[ch][i]++;
        }
        for (int i = 1; i < n; i++) {
            int ch = t.charAt(i)-'a';
            for (int j = 0; j < 26; j++) {
                pret[j][i] = pret[j][i-1];
            }
            pret[ch][i]++;
        }
        for (int i = n-2; i >= 0; i--) {
            for (int j = n-2; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i+1][j+1];
                } else {
                    if (getCount(pre, i+1, n-1, t.charAt(j)) > getCount(pret, j+1, n-1, t.charAt(j))) {
                        dp[i][j] = Math.min(dp[i+1][j]+1, dp[i][j+1]);
                    } else {
                        dp[i][j] = dp[i+1][j] + 1;
                    }
                }
            }
        }
        int ans = dp[0][0];
        if (ans >= INF) {
            ans = -1;
        }
        out.println(ans);
    }

    private int getCount(int[][] pre, int i, int j, char ch) {
        int c = ch-'a';
        if (i == 0) return pre[c][j];
        return pre[c][j] - pre[c][i-1];
    }
}
