package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class CStringTransformation1 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        String a = in.nextString();
        String b = in.nextString();
        int M = 20;
        boolean[][] mp = new boolean[M][M];
        for (int i = 0; i < n; i++) {
            int f = get(a.charAt(i));
            int s = get(b.charAt(i));
            if (f > s) {
                out.println(-1);
                return;
            } else if (f < s) {
                mp[f][s] = true;
            }
        }
        int ans = 0;
        for (int i = M-2; i >= 0; i--) {
            for (int j = i+1; j < M; j++) {
                if (mp[i][j]) {
                    ans++;
                    for (int k = 0; k < i; k++) {
                        if (mp[k][j]) {
                            mp[k][i] = true;
                            mp[k][j] = false;
                        }
                    }
                }
            }
        }
        out.println(ans);
    }

    private int get(char c) {
        return c - 'a';
    }

    private char get(int i) {
        return ((char)(i + 'a'));
    }
}
