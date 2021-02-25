package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IshaanGS2 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int req = in.nextInt();
        int n = in.nextInt();
        int[] sizes = in.nextIntArray(n);
        int ans = getUmbrellas(req, Arrays.stream(sizes).boxed().collect(Collectors.toList()));
        out.println(ans);
    }

    public int getUmbrellas(int requirements, List<Integer> sizes) {
        int INF = 112345;
        int[] dp = new int[requirements + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 1; i <= requirements; i++) {
            for (int k : sizes) {
                if (k <= i) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - k]);
                }
            }
        }
        if (dp[requirements] >= INF) {
            return -1;
        } else {
            return dp[requirements];
        }
    }
}
