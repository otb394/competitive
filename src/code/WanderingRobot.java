package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class WanderingRobot {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int w = in.nextInt();
        int h = in.nextInt();
        int l = in.nextInt();
        int u = in.nextInt();
        int r = in.nextInt();
        int d = in.nextInt();
        double ans = solve(w,h,l,u,r,d);
        out.println(String.format("Case #%d: %.7f", testNumber, ans));
    }

    private double solve(int w, int h, int l, int u, int r, int d) {
        double p = 0.0;
//        if (u > 1) {
//            int y = u-1;
//
//        }
//        if (l > 1) {
//
//        }
        return p;
    }
}
