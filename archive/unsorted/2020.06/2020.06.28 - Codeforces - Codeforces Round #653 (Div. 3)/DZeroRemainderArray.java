package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class DZeroRemainderArray {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = in.nextIntArray(n);
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int req = (k - a[i]%k) % k;
            int val = mp.getOrDefault(req, 0);
            mp.put(req, val+1);
        }
        long ans = 0;
        for (Map.Entry<Integer, Integer> entry: mp.entrySet()) {
            int x = entry.getKey();
            if (x == 0) continue;
            int val = entry.getValue();
            ans = Math.max(ans, x + ((long)(val-1)) * ((long)k));
        }
        if (ans == 0) {
            out.println(0);
        } else {
            out.println(ans + 1);
        }
    }
}
