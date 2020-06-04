package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class CGameOnLeaves {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        int deg = 0;
        for (int i = 0; i < (n-1); i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            if (u == x || v == x) {
                deg++;
            }
        }
        boolean ans;
        if (deg <= 1) {
            ans = true;
        } else {
            ans = ((n - 3) % 2) != 0;
        }
        out.println(ans ? "Ayush" : "Ashish");
    }
}
