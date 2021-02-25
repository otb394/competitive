package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ChefinaAndSwaps {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] b = in.nextIntArray(n);
        Map<Integer, Integer> mpa = new HashMap<>();
        Map<Integer, Integer> mpb = new HashMap<>();
        for (int i = 0; i < n; i++) {
            add(mpa, a[i]);
        }
        for (int i = 0; i < n; i++) {
            add(mpb, b[i]);
        }
        Set<Integer> set = new HashSet<>();
        set.addAll(mpa.keySet());
        set.addAll(mpb.keySet());
        List<Integer> vals = new ArrayList<>(set);
        ut.printDebug(vals, "vals");
        List<Integer> ae = new ArrayList<>();
        List<Integer> be = new ArrayList<>();
        Optional<Integer> minVal = Optional.empty();
        int minnVal = Integer.MAX_VALUE;
        for (int val : vals) {
            int ca = mpa.getOrDefault(val, 0);
            int cb = mpb.getOrDefault(val, 0);
            minnVal = Math.min(minnVal, val);
            if (ca > 0 && cb > 0) {
                minVal = Optional.of(minVal.map(v -> Math.min(v, val)).orElse(val));
//                minVal = Math.min(minVal, val);
            }
            int tot = ca + cb;
            ut.printDebug(val, "val", ca, "ca", cb, "cb", tot, "tot");
            if (tot % 2 != 0) {
                out.println(-1);
                return;
            }
            if (ca > cb) {
                int d = ca - cb;
                int h = d/2;
                for (int i = 0; i < h; i++) {
                    ae.add(val);
                }
            } else if (ca < cb) {
                int d = cb - ca;
                int h = d/2;
                for (int i = 0; i < h; i++) {
                    be.add(val);
                }
            }
        }
        ut.printDebug(ae, "ae", be, "be");
        MiscUtility.assertion(ae.size() == be.size(), String.format("ae.size [%d] not same as be.size [%d]",
                ae.size(), be.size()));
        int an = ae.size();
        ae.sort(Integer::compareTo);
        be.sort(Comparator.reverseOrder());
        long ans = 0L;
        int valMin = minnVal * 2;
        for (int i = 0; i < an; i++) {
            ans += Math.min(valMin, Math.min(ae.get(i), be.get(i)));
            int finalI = i;
//            ans += minVal.map(v -> Math.min(2*v, Math.min(ae.get(finalI), be.get(finalI))))
//                    .orElseGet(() -> Math.min(ae.get(finalI), be.get(finalI)));
//            ans += Math.min(Math.min(ae.get(i), be.get(i)));
        }
        out.println(ans);
    }

    private void add(Map<Integer, Integer> mp , int key) {
        int val = mp.getOrDefault(key, 0);
        mp.put(key, val + 1);
    }
}
