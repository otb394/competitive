package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MathUtility;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumberOfFactors {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            contribute(a[i], mp);
        }
        long[] ansl = mp.values().stream().map(integer -> integer + 1).mapToLong(i -> i).toArray();
        long ans = 1L;
        for (int i = 0; i < ansl.length; i++) {
            ans *= ansl[i];
        }
        out.println(ans);
    }

    private void contribute(int n, Map<Integer, Integer> mp) {
        int s = (int) Math.floor(Math.sqrt(n));
        for (int i = 2; i <= s; i++) {
            if ((n%i) == 0) {
                int p = 0;
                while ((n%i) == 0) {
                    n/=i;
                    p++;
                }
                int val = mp.getOrDefault(i, 0);
                mp.put(i, val+p);
            }
        }
        if (n != 1) {
            int val = mp.getOrDefault(n, 0);
            mp.put(n, val + 1);
        }
    }
}
