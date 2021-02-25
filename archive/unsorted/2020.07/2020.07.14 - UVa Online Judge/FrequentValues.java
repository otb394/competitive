package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.SegmentTree;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class FrequentValues {
    MiscUtility ut = new MiscUtility(false);
    int[] tr;
    int[] ab;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (true ) {
            int n = in.nextInt();
            if (n == 0) {
                break;
            }
            int N = 4*n + 1;
            tr = new int[N];
            int q = in.nextInt();
            int[] a = in.nextIntArray(n);
            List<Integer> b = new ArrayList<>();
            TreeMap<Integer, Integer> mp = new TreeMap<>();
            int i = 0;
            int z = 0;
            while (i < n) {
                int j = i+1;
                while (j < n && a[j] == a[i]) j++;
                b.add(j - i);
                mp.put(i, z);
                i = j;
                z++;
            }
            int len = b.size();
//            int[] cum = new int[len];
//            cum[0] = b.get(0);
//            for (int j = 1; j < len; j++) {
//                cum[j] = cum[j-1] + b.get(j);
//            }
            ab = b.stream().mapToInt(y -> y).toArray();
            build(1, 0, len-1);
//            SegmentTree st = new SegmentTree(ab, len);
            ut.printDebug(() -> Arrays.stream(a).boxed().collect(Collectors.toList()), () -> "a", () -> b, () -> "b",
                    () -> len, () -> "len", () -> mp, () -> "mp", () -> Arrays.stream(tr).boxed().collect(Collectors.toList()), () -> "tr");//, () -> st, () -> "st");
            for (int j = 0; j < q; j++) {
                int f = in.nextInt();f--;
                int s = in.nextInt();s--;
                int rs = mp.floorKey(f);
                int re = mp.floorKey(s);
                if (rs == re) {
                    out.println(s - f + 1);
                    continue;
                }
                int tot = ab[mp.get(rs)];
                int ex = tot - f + rs;
                int oex = s - re + 1;
                int qa = mp.get(rs) + 1;
                int qb = mp.get(re) - 1;
                ut.printDebug(f, "f", s, "s", rs, "rs", ex, "ex", re, "re", oex, "oex", qa, "qa", qb, "qb");
                int ans = Math.max(ex, oex);
                if (qa <= qb) {
                    ans = Math.max(ans, query(1, 0, len-1, qa, qb));
//                    ans = Math.max(ans, st.query(qa, qb));
                }
                out.println(ans);
            }
        }
    }

    private void build(int p, int start, int end) {
        if (start == end) tr[p] = ab[start];
        else {
            int mid = start + (end - start)/2;
            int lf = p<<1;
            int ri = lf + 1;
            build(lf, start, mid);
            build(ri, mid + 1, end);
            tr[p] = Math.max(tr[lf], tr[ri]);
        }
    }

    private int query(int p, int start, int end, int i, int j) {
        int ret;
        if (start >= i && end <= j) {
            ret= tr[p];
        } else if (start > j || end < i) {
            ret = Integer.MIN_VALUE;
        } else {
            int mid = start + (end - start) / 2;
            int lf = p << 1;
            int ri = lf + 1;
            ret = Math.max(query(lf, start, mid, i, j), query(ri, mid + 1, end, i, j));
        }
        ut.printDebug(p, "p", start, "start", end, "end", i, "i", j, "j", ret, "ret");
        return ret;
    }
}
