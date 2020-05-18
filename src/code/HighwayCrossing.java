package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;

public class HighwayCrossing {
    private static final double MIN_DIS = 1E-6;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        long s = in.nextLong();
        long y = in.nextLong();
        long[] v = new long[n];
        int[] d = new int[n];
        long[] p = new long[n];
        long[] c = new long[n];
        for (int i = 0; i < n; i++) {
            v[i] = in.nextLong();
        }
        for (int i = 0; i < n; i++) {
            d[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            p[i] = in.nextLong();
        }
        for (int i = 0; i < n; i++) {
            c[i] = in.nextLong();
        }
//        out.printf("%.6f\n", solve(n, s, y, v, d, p, c));
        out.println(solve(n,s,y,v,d,p,c));
    }

    private double solve(int n, long s, long y, long[] v, int[] d, long[] p, long[] c) {
        double currT = 0.0;
        double T = ((double) y) / ((double) s);
        for (int i = 0; i < n; i++) {
            double vel = (d[i]==1) ? (v[i]) : (-v[i]);
            Pair<Double, Double> carTimes = getCarTimes(vel, d[i], p[i], c[i]);
            currT = getCrossTime(currT, carTimes.getLeft(), carTimes.getRight(), T);
        }
        return currT;
    }

    private double getCrossTime(double t, double T1, double T2, double T) {
        double bestCrossTime = t+T;
        if (Double.compare(bestCrossTime, T1) == -1 || Double.compare(t, T2) == 1) {
            return bestCrossTime;
        }
        return T2+T;
    }

    private Pair<Double, Double> getCarTimes(double v, int d, double p, double c) {
        double ep = MIN_DIS/v;
        double frontEarliestPosToHit, backLatestPosToHit;
        if (d == 1) {
            frontEarliestPosToHit = 0.0-ep;
            backLatestPosToHit = 0.0+ep;
        } else {
            frontEarliestPosToHit = 0.0-ep;
            backLatestPosToHit = 0.0-ep;
        }
        double b = getBackSidePos(p, c, d);
        double t1 = calcTimeFromPos(p, frontEarliestPosToHit, v);
        double t2 = calcTimeFromPos(b, backLatestPosToHit, v);
        return Pair.of(t1, t2);
    }

    private double calcTimeFromPos(double x1, double x2, double v) {
        return (x2-x1)/v;
    }

    private double getBackSidePos(double x, double len, int direction) {
        if (direction == 1) {
            return x-len;
        } else {
            return x+len;
        }
    }
}
