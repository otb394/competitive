package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResearchProductivityIndex {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] p = in.nextIntArray(n);
        List<Double> lp = IntStream.of(p).boxed().sorted(Collections.reverseOrder())
                .map(i -> (double)i / 100.0)
                .collect(Collectors.toList());
        double[][] dp = new double[n+1][n+1];
        dp[0][0] = 1.0;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i-1][0] * (1.0 - lp.get(i-1));
        }
        dp[1][1] = lp.get(0);
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = lp.get(i-1) * dp[i-1][j-1] + (1.0-lp.get(i-1)) * dp[i-1][j];
            }
        }
        double[] an = new double[n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                an[i]+=(mpow(i,j)) * dp[i][j];
            }
        }
        double ans = Double.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, an[i]);
        }
        out.printf("%.9f", ans);
//        out.print(ans);
    }

    private static double mpow(int i, int j) {
        if (j == 0) return 0.0;
        return Math.pow(j, ((double)j)/((double)i));
    }
}
