package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;

public class BPowerSequence {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        long[] a = in.nextLongArray(n);
        Arrays.sort(a);
        if (n == 1) {
            out.println(a[0] - 1);
        } else if (n == 2) {
            out.println(a[0] - 1);
        } else if (n > 50) {
            out.println(Arrays.stream(a).sum() - n);
        } else {
            int MX = 100011;
            long cans = Arrays.stream(a).sum() - n;
            for (int i = 1; i < MX; i++) {
                long ans = 0L;
                long val = 1;
                boolean flag = true;
                for (int j = 0; j < n; j++) {
                    ans += Math.abs(val - a[j]);
                    if (ans > cans) {
                        flag = false;
                        break;
                    }
                    val *= i;
                }
                if (flag) {
                    cans = Math.min(cans, ans);
                }
            }
            out.println(cans);
        }
    }
}
