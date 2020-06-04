package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import algorithms.BinarySearch;

import java.util.Arrays;
import java.util.function.Function;

public class DXeniaAndColorfulGems {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int nr = in.nextInt();
        int ng = in.nextInt();
        int nb = in.nextInt();
        long[] r= in.nextLongArray(nr);
        long[] g = in.nextLongArray(ng);
        long[] b = in.nextLongArray(nb);
        Arrays.sort(r);
        Arrays.sort(g);
        Arrays.sort(b);
        long ans = Long.MAX_VALUE;
        ans = Math.min(ans, getAns(r ,nr, g, ng, b, nb));
        ans = Math.min(ans, getAns(r ,nr, b, nb, g, ng));
        ans = Math.min(ans, getAns(g ,ng, b, nb, r, nr));
        ans = Math.min(ans, getAns(g ,ng, r, nr, b, nb));
        ans = Math.min(ans, getAns(b ,nb, r, nr, g, ng));
        ans = Math.min(ans, getAns(b ,nb, g, ng, r, nr));
        out.println(ans);
    }

    private long getAns(long[] a, int na, long[] b, int nb, long[] c, int nc) {
        int bi = 0;
        int ci = 0;
        long ret = Long.MAX_VALUE;
        for (int i = 0; i < na; i++) {
            long va = a[i];
            while(bi < nb && b[bi] < va) bi++;
            if (bi == nb) break;
            long vb = b[bi];
            while(ci < nc && c[ci] < vb) ci++;
            if (ci == nc) break;
            long vc = c[ci];
            long avb = getClosest(b, nb, (va + vc) / 2.0);
            ret = Math.min(ret, compute(va, avb, vc));
        }
        return ret;
    }

    private long compute(long a, long b, long c) {
        return square(a-b) + square(b-c) + square(a-c);
    }

    private long square(long x) {
        return (x*x);
    }

    private long getClosest(long[] arr, int n, double val) {
        Function<Integer, Boolean> func = ind -> (arr[ind] >= val);
        int index = BinarySearch.searchFirstOne(0, n, func);
        if (index == 0) return arr[0];
        if (index == n) return arr[n-1];
        if (Math.abs(arr[index] - val) <= Math.abs(arr[index-1] - val)) {
            return arr[index];
        } else {
            return arr[index-1];
        }
    }
}
