package datastructure;

import java.util.Arrays;

public class SegmentTree {
    private int[] a;
    private int[] tr;
    private int n;
    private boolean[] lazy;
    private int[] lazyInfo;

    public SegmentTree(int[] a, int n) {
        this.n = n;
        this.a = a;
        int N = 4*n+1;
        tr = new int[N];
        lazy = new boolean[N];
        lazyInfo = new int[N];
        build(1, 0, n-1);
    }

    private void build(int p, int start, int end) {
        if (start == end) {
            tr[p] = a[start];
        } else {
            int mid = start + (end-start)/2;
            int lf = p<<1;
            int ri = lf + 1;
            build(lf, start, mid);
            build(ri, mid + 1, end);
            tr[p] = Math.min(tr[lf], tr[ri]);
        }
    }

    public int query(int qStart, int qEnd) {
        return query(1, 0, n-1, qStart, qEnd);
    }

    private int query(int p, int start, int end, int qStart, int qEnd) {
        if (start >= qStart && end <= qEnd) {
            return tr[p];
        } else if (end < qStart || start > qEnd) {
            return Integer.MAX_VALUE;
        } else {
            int mid = start + (end - start)/2;
            int lf = p<<1;
            int ri = lf + 1;
            if (lazy[p]) {
                //Do lazy updates for children
                update(lf, start, mid, start, mid, lazyInfo[p]);
                update(ri, mid + 1, end, mid + 1, end, lazyInfo[p]);
                lazy[p] = false;
            }
            return Math.min(query(lf, start, mid, qStart, qEnd), query(ri, mid + 1, end, qStart, qEnd));
        }
    }

    private void update(int p, int start, int end, int index, int newValue) {
        if (start > index || end < index) return;
        if (start == end) {
            tr[p] = newValue;
            return;
        }
        int lf = p<<1;
        int ri = lf + 1;
        int mid = start + (end - start)/2;
        update(lf, start, mid, index, newValue);
        update(ri, mid + 1, end, index, newValue);
        tr[p] = Math.min(tr[lf], tr[ri]);
    }

    //Lazy evaluation. Setting all values in query range to newValue
    private void update(int p, int start, int end, int qStart, int qEnd, int newValue) {
        if (end < qStart || start > qEnd) return;
        if (start >= qStart && end <= qEnd) {
            tr[p] = newValue;
            lazy[p] = true;
            lazyInfo[p] = newValue;
            return;
        }
        int lf = p<<1;
        int ri = lf + 1;
        int mid = start + (end - start)/2;
        update(lf, start, mid, qStart, qEnd, newValue);
        update(ri, mid + 1, end, qStart, qEnd, newValue);
        tr[p] = Math.min(tr[lf], tr[ri]);
    }

    @Override
    public String toString() {
        return toString(tr, 1, 0, n-1);
    }

    public static String toString(int[] tree, int p, int start, int end) {
        return toString(tree, p, start, end, new Prefix(0));
    }

    public static <T> String toString(T[] tree, int p, int start, int end) {
        return toString(tree, p, start, end, new Prefix(0));
    }

    private static String toString(int[] tree, int p, int start, int end, Prefix prefix) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s%d: %d [%d, %d]\n", prefix, p, tree[p], start, end));
        if (start != end) {
            int lf = p << 1;
            int ri = lf + 1;
            int mid = start + (end - start) / 2;
            builder.append(toString(tree, lf, start, mid, prefix.next()));
            builder.append(toString(tree, ri, mid + 1, end, prefix.next()));
        }
        return builder.toString();
    }

    private static <T> String toString(T[] tree, int p, int start, int end, Prefix prefix) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s%d: %s [%d, %d]\n", prefix, p, tree[p].toString(), start, end));
        if (start != end) {
            int lf = p << 1;
            int ri = lf + 1;
            int mid = start + (end - start) / 2;
            builder.append(toString(tree, lf, start, mid, prefix.next()));
            builder.append(toString(tree, ri, mid + 1, end, prefix.next()));
        }
        return builder.toString();
    }
}
