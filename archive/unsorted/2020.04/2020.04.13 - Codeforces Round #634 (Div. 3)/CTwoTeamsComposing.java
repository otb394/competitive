package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class CTwoTeamsComposing {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();int[] a=in.nextIntArray(n);
        Map<Integer,Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = a[i];
            int val = mp.getOrDefault(x, 0);
            mp.put(x, val+1);
        }
        int u = mp.size();
        int m = mp.values().stream().max(Integer::compareTo).orElseThrow(() -> new RuntimeException("Unexpected input"));
        int ans;
        if (m > u) {
            ans = u;
        } else if (m == u) {
            ans = u-1;
        } else {
            ans = m;
        }
        out.println(ans);
    }
}
