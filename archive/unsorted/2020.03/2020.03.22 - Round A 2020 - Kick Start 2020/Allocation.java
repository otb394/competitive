package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;

public class Allocation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int b = in.nextInt();
        int[] a = in.nextIntArray(n);

        Arrays.sort(a);

        int cnt = 0;
        int i = 0;
        while (i < n && b > 0) {
            if (a[i] <= b) {
                cnt++;
                b-=a[i];
            }
            i++;
        }

        out.println(String.format("Case #%d: %d", testNumber, cnt));
        /*
        int[] dp = new int[b+1];

        for (int i = 0; (i < a[0]) && (i <= b); i++) {
            dp[i] = 0;
        }
        for (int i = a[0]; i <= b; i++) {
            dp[i] = 1;
        }

        int[] dp2 = new int[b+1];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= b; j++) {
                dp2
            }
        }
         */
    }
}
