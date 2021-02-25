package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;

import java.util.ArrayList;
import java.util.List;

public class Toys {
    int[] tree;
    long[] arr;
    long[] tr;
    int[] e;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        e = new int[n];
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            e[i] = in.nextInt();
            r[i] = in.nextInt();
        }
        if (n == 1) {
            out.printf("Case #%d: 0 %d\n", testNumber, e[0]);
            return;
        }
        long sum = 0L;
        for (int i = 0; i < n; i++) {
            sum += e[i];
        }
        long[] t = new long[n];
        for (int i = 0; i < n; i++) {
            t[i] = sum - e[i];
        }
        int N = 4 * n + 1;
        arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = t[i] - r[i];
        }
        tree = new int[N];
        tr = new long[N];
        buildd(1, 0, n-1);
        build(1, 0, n-1);
        long thresh = 0L;
        List<Pair<Integer, Long>> delInd = new ArrayList<>();
        long ans = 0;
        int ansc = n;
        while (true) {
            int index = query(1, 0, n-1, thresh);
            if (index == (n)) {
                break;
            }
            long bfr = query(1, 0, n-1, 0, index-1);
            long cans = bfr + sum - thresh;
            if (cans >= ans) {
                if (cans == ans) {
                    ansc = Math.min(ansc, delInd.size());
                } else {
                    ans = cans;
                    ansc = delInd.size();
                }
            }
            delInd.add(Pair.of(index, thresh));
            update(1, 0, n-1, index);
            updatee(1, 0, n-1, index);
            thresh += e[index];
        }
        if (delInd.size() < n) {
            out.printf("Case #%d: %d INDEFINITELY\n", testNumber, delInd.size());
        } else {
            out.printf("Case #%d: %d %d\n", testNumber, ansc, ans);
        }
    }

    private void buildd(int p, int start, int end) {
        if (start == end) {
            tr[p] = e[start];
        } else {
            int mid = start + (end - start) / 2;
            int lf = p << 1;
            int ri = lf + 1;
            buildd(lf, start, mid);
            buildd(ri, mid + 1, end);
            tr[p] = tr[lf] + tr[ri];
        }
    }

    private long query(int p, int start, int end, int qs, int qe) {
        if (start > qe || end < qs) return 0;
        if (start >= qs && end <= qe) {
            return tr[p];
        }
        int mid = start + (end - start) / 2;
        int lf = p << 1;
        int ri = lf + 1;
        long la = query(lf, start, mid, qs, qe);
        long ra = query(ri, mid + 1, end, qs, qe);
        return la + ra;
    }

    private void updatee(int p, int start, int end, int index) {
        if (start > index || end < index) return;
        tr[p] -= e[index];
        if (start != end) {
            int mid = start + (end - start) / 2;
            int lf = p << 1;
            int ri = lf + 1;
            updatee(lf, start, mid, index);
            updatee(ri, mid + 1, end, index);
        }
    }

    private void build(int p, int start, int end) {
        if (start == end) {
            tree[p] = start;
        } else {
            int mid = start + (end - start) / 2;
            int lf = p << 1;
            int ri = lf + 1;
            build(lf, start, mid);
            build(ri, mid + 1, end);
            if (arr[tree[lf]] <= arr[tree[ri]]) {
                tree[p] = tree[lf];
            } else {
                tree[p] = tree[ri];
            }
        }
    }

    private void update(int p, int start, int end, int index) {
        if (start > index || end < index) return;
        if (start == end) {
            tree[p] = end + 1;
        } else {
            int lf = p << 1;
            int ri = lf + 1;
            int mid = start + (end - start) / 2;
            update(lf, start, mid, index);
            update(ri, mid + 1, end, index);
            if (tree[lf] == (mid+1)) {
                tree[p] = tree[ri];
            } else {
                if (tree[ri] == (end + 1)) {
                    tree[p] = tree[lf];
                } else {
                    if (arr[tree[lf]] <= arr[tree[ri]]) {
                        tree[p] = tree[lf];
                    } else {
                        tree[p] = tree[ri];
                    }
                }
            }
        }
    }

    private int query(int p, int start, int end, long thresh) {
        if (tree[p] == (end + 1) || (arr[tree[p]] >= thresh)) {
            return end + 1;
        }
        if (start == end) {
            if (arr[start] < thresh) {
                return start;
            } else {
                return end + 1;
            }
        } else {
            int mid = start + (end - start) / 2;
            int lf = p << 1;
            int ri = lf + 1;
            int la = query(lf, start, mid, thresh);
            if (la == (mid + 1)) {
                return query(ri, mid + 1, end, thresh);
            } else {
                return la;
            }
        }
    }
}
