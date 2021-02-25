package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class HoaxOrWhat {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (true) {
            int n = in.nextInt();
            if (n == 0) {
                break;
            }
            TreeMap<Integer, Integer> mp = new TreeMap<>();
            long ans = 0L;
            for (int i = 0; i < n; i++) {
                ut.printDebug(mp, "mp", i, "i");
                int k = in.nextInt();
                for (int j = 0; j < k; j++) {
                    int b = in.nextInt();
                    int val = mp.getOrDefault(b, 0);
                    mp.put(b, val+1);
                }
                Map.Entry<Integer, Integer> fe = mp.firstEntry();
                Map.Entry<Integer, Integer> le = mp.lastEntry();
                ans += le.getKey() - fe.getKey();
                sub(mp, fe.getKey());
                sub(mp, le.getKey());
                ut.printDebug(ans, "ans");
            }
            out.println(ans);
        }
    }

    private void sub(Map<Integer, Integer> mp, int k) {
        int val = mp.getOrDefault(k, 0);
        if (val == 0) {
            return;
        }
        if (val == 1) {
            mp.remove(k);
            return;
        }
        mp.put(k, val - 1);
    }
}
