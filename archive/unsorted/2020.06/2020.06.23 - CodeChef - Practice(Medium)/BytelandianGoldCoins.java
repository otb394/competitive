package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class BytelandianGoldCoins {
    Map<Integer, Long> cache;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        cache = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            cache.put(i, (long)i);
        }
        try {
            while (true) {
                slve(1, in, out);
            }
        } catch (Exception e) {
            return;
        }
    }

    private void slve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        if (cache.containsKey(n)) {
            out.println(cache.get(n));
            return;
        }
        long ans = getAns(n);
        cache.put(n, ans);
        out.println(ans);
    }

    private long getAns(int n) {
        if (cache.containsKey(n)) return cache.get(n);
        long ans = Math.max(n, getAns(n/2) + getAns(n/3) + getAns(n/4));
        cache.put(n, ans);
        return ans;
    }
}
