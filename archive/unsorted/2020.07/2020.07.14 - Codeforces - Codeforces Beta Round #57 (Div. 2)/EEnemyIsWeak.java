package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import algorithms.BinarySearch;

import java.util.Arrays;
import java.util.function.Function;

public class EEnemyIsWeak {
    boolean[] right;
    boolean[] left;
    int[] arr;
    int n;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.nextInt();
        int[] a = in.nextIntArray(n);
        arr = Arrays.stream(a).boxed().sorted().mapToInt(i -> i).toArray();
        left = new boolean[n];
        right = new boolean[n];
        Arrays.fill(right, true);
        int N = 4*n + 1;
        int[] trl = new int[N];
        int[] trr = new int[N];
        long ans = 0L;
        int index = getIndex(a[0]);
        right[index] = false;
        build(trl, 1, 0, n-1, left);
        build(trr, 1, 0, n-1, right);
        for (int i = 1; i < (n-1); i++) {
            int ind = getIndex(a[i]);
            int lind = getIndex(a[i-1]);
            update(trr, 1, 0, n-1, ind, false);
            update(trl, 1, 0, n-1, lind, true);
            long lfc = query(trl, 1, 0, n-1, ind+1, n-1);
            long ric = query(trr, 1, 0, n-1, 0, ind-1);
            ans += lfc * ric;
        }
        out.println(ans);
    }

    private void build(int[] tr, int p, int start, int end, boolean[] ar) {
        if (start == end) {
            tr[p] = ar[start] ? 1 : 0;
        } else {
            int lf = p << 1;
            int ri = lf + 1;
            int mid = start + (end - start) / 2;
            build(tr, lf, start, mid, ar);
            build(tr, ri, mid + 1, end, ar);
            tr[p] = tr[lf] + tr[ri];
        }
    }

    private int query(int[] tr, int p, int start, int end, int i, int j) {
        if (start >= i && end <= j) return tr[p];
        if (start > j || end < i) return 0;
        int lf = p << 1;
        int ri = lf + 1;
        int mid = start + (end - start) / 2;
        return query(tr, lf, start, mid, i, j) + query(tr, ri, mid + 1, end, i, j);
    }

    private void update(int[] tr, int p, int start, int end, int index, boolean value) {
        if (index < start || index > end) return;
        if (start == end) {
            tr[p] = value ? 1 : 0;
        } else {
            int lf = p << 1;
            int ri = lf + 1;
            int mid = start + (end - start) / 2;
            update(tr, lf, start, mid, index, value);
            update(tr, ri, mid + 1, end, index, value);
            tr[p] = tr[lf] + tr[ri];
        }
    }

    private int getIndex(int val) {
        Function<Integer, Boolean> func = ind -> arr[ind] >= val;
        return BinarySearch.searchFirstOne(-1, n-1, func);
    }
}
