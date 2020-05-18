package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class OversizedPancakeChoppers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();
        int d=in.nextInt();
        long[] a=in.nextLongArray(n);
        int ans = solve(n,d,a);
        out.println(String.format("Case #%d: %d", testNumber, ans));
    }

    private int solve(int n, int d, long[] a) {
        if (n == 1) {
            return d-1;
        }
        List<Long> A = new ArrayList<>();
        for (long l : a) {
            A.add(l);
        }
        A.sort(Long::compareTo);

        if (d == 2) {
            List<Long> Ap = A.stream().distinct().collect(Collectors.toList());
            if (Ap.size() == A.size()) {
                return 1;
            } else {
                return 0;
            }
        } else if (d == 3) {
            Map<Long, Integer> mp = new HashMap<>();
            long maxA = Long.MIN_VALUE;
            for (long l : a) {
                int val = mp.getOrDefault(l, 0);
                mp.put(l, val+1);
                maxA = Math.max(maxA, l);
            }
            long largerThan3 = mp.values().stream().filter(val -> val >= 3).count();
            if (largerThan3 > 0L) {
                return 0;
            }
            Set<Long> Avals = mp.keySet();
            for (long aval : Avals) {
                long hlf = aval/2;
                if (Avals.contains(hlf)) {
                    return 1;
                }
            }
            long finalMaxA = maxA;
            long largerThan2 = mp.entrySet().stream().filter(en -> en.getValue() >= 2)
                    .filter(en -> !en.getKey().equals(finalMaxA)).count();
            if (largerThan2 > 0) {
                return 1;
            }
            return 2;
        } else {
            //TODO
            return n-1;
        }
    }
}
