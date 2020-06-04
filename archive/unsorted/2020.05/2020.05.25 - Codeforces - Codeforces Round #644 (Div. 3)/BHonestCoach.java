package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;

public class BHonestCoach {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] s = in.nextIntArray(n);
        int ans = Integer.MAX_VALUE;
        Arrays.sort(s);
        for (int i = 0; i < (n-1); i++) {
            int diff = Math.abs(s[i] - s[i+1]);
            ans = Math.min(ans, diff);
        }
        out.println(ans);
    }
}
