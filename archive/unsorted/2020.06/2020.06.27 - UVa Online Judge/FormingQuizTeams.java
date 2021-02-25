package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormingQuizTeams {
    final double INF = 1e8;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = 0;
        while (true) {
            t++;
            int n = in.nextInt();
            if (n == 0) {
                break;
            }
            int N = n<<1;
            int[] x = new int[N];
            int[] y = new int[N];
            for (int i = 0; i < N; i++) {
                String s = in.nextString();
                x[i] = in.nextInt();
                y[i] = in.nextInt();
            }
            double[] dp = new double[1<<N];
            Arrays.fill(dp, -1.0);
            dp[0] = 0.0;
            double ans = getAns(dp, (1<<N) - 1, N, x, y);
            out.printf("Case %d: %.2f\n", t, ans);
        }
    }

    private double getAns(double[] dp, int mask, int N, int[] x, int[] y) {
        if (dp[mask] != -1.0) {
            return dp[mask];
        }
        List<Integer> available = new ArrayList<>();
        int t = 1;
        int r = 0;
        while (t<=mask) {
            if ((t&mask) != 0) {
                available.add(r);
            }
            t<<=1;
            r++;
        }
        int an = available.size();
        MiscUtility.assertion((an&1) == 0, String.format("mask [%d] is malformed", mask));
        double ret = INF;
        for (int i = 0; i < (an-1); i++) {
            int f = available.get(i);
            for (int j = i+1; j < an; j++) {
                int s = available.get(j);
                int nmask = mask ^ (1<<f);
                nmask ^= (1<<s);
                double cand = getDis(x[f], y[f], x[s], y[s]) + getAns(dp, nmask, N, x, y);
                ret = Math.min(ret, cand);
            }
        }
        dp[mask] = ret;
        return ret;
    }

    private double getDis(int xa, int ya, int xb, int yb) {
        return Math.sqrt(sq(xa-xb) + sq(ya-yb));
    }

    private int sq(int x) {
        return x*x;
    }
}
