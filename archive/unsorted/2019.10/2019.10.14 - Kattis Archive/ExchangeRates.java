package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class ExchangeRates {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while(true) {
            int n = in.nextInt();
            if (n == 0) break;
            double[] rates = new double[n+1];
            for (int i = 1; i <= n; i++) {
                rates[i] = in.nextDouble();
            }
            double ans = solve(rates, n, out);
            out.printf("%.2f\n", ans);
        }
    }

    private double solve(double[] utc, int n, OutputWriter out) {
        double[] ctu = new double[n+1];
        for (int i = 1; i <= n; i++) {
            ctu[i] = 1.0 / utc[i];
        }
        double[] dpc = new double[n+1];
        double[] dpu = new double[n+1];
        dpc[0] = 1000.0;
        dpu[0] = 0.0;
        for (int i = 1; i <= n; i++) {
            dpc[i] = Math.max(dpc[i-1], roundDownTo2Dec(0.97*dpu[i-1]*utc[i]));
            dpu[i] = Math.max(dpu[i-1], roundDownTo2Dec(0.97*dpc[i-1]*ctu[i]));
        }
        return dpc[n];
    }

    private double roundDownTo2Dec(double x) {
        double x100 = x * 100.0;
        double ed = (x100 - (long)x100) / 100.0;
        return x - ed;
    }
}
