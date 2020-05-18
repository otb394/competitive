package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MathUtility;

import java.util.HashMap;
import java.util.Map;

public class PerfectSubarray {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int maxA = -100;
        for (int i = 0; i < n; i++) {
            maxA = Math.max(maxA, a[i]);
        }
        long ans = solve(a,n, ((int)Math.sqrt(maxA*n))+2);
        out.println(String.format("Case #%d: %d", testNumber, ans));
    }

    private long solve(int[] a, int n, int maxVal) {
        long ret = 0;
        Map<Integer, Long> mp = new HashMap<>();
        int[] pre=new int[n];
        pre[0]=a[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i-1]+a[i];
        }

        for (int i = 0; i < n; i++) {
            int pr = pre[i];
            if (MathUtility.isSquare(pr)) ret++;
            for (int j = 0; j <= maxVal; j++) {
                int num = j*j;
                ret+= mp.getOrDefault(pr-num,0L);
            }
            long val = mp.getOrDefault(pr, 0L);
            mp.put(pr, val+1);
        }
        return ret;
    }
}
