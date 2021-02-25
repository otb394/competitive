package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class UniqueSnowflakes {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        Map<Integer, Integer> mp = new HashMap<>();
        int i = 0;
        int ans = 0;
        int j = 0;
        mp.put(a[i], i);
        ut.printDebug(() -> n, () -> "n", () -> Arrays.stream(a).boxed().collect(Collectors.toList()), () -> "a");
        while (i < n) {
            ut.printDebug(i, "i");
            int k = j+1;
            while (k < n && !mp.containsKey(a[k])) {
                mp.put(a[k], k);
                k++;
            }
            ans = Math.max(ans, k-i);
            ut.printDebug(k, "k", ans, "ans");
            if (k == n) {
                break;
            }
            int oi = mp.get(a[k]);
            MiscUtility.assertion(oi >= i, String.format("oi [%d] is less than i [%d]", oi, i));
            while (i <= oi) {
                mp.remove(a[i]);
                i++;
            }
            mp.put(a[k], k);
            j = k;
        }
        out.println(ans);
    }
}
