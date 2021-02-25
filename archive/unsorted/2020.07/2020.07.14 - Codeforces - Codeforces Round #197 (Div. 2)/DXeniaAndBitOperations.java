package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.SegmentTree;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class DXeniaAndBitOperations {
    int[] tr;
    int[] a;
    MiscUtility ut = new MiscUtility(false);
    List<BiFunction<Integer, Integer, Integer>> functions;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int N = 1<<n;
        a = in.nextIntArray(N);
        int NN = 4*N + 1;
        tr = new int[NN];
        functions = new ArrayList<>();
        functions.add((i, j) -> i | j);
        functions.add((i, j) -> i ^ j);
        boolean op = n%2 == 0;
        build(1, 0, N-1, op);
        ut.printDebug(() -> SegmentTree.toString(tr, 1, 0, N-1), () -> "tr");
        for (int i = 0; i < m; i++) {
            int p = in.nextInt();p--;
            int b = in.nextInt();
            update(1, 0, N-1, p, b, op);
            out.println(tr[1]);
        }
    }

    private void build(int p, int start, int end, boolean op) {
        if (start == end) {
            tr[p] = a[start];
        } else {
            int lf = p << 1;
            int ri = lf + 1;
            int mid = start + (end - start) / 2;
            build(lf, start, mid, !op);
            build(ri, mid + 1, end, !op);
            int ind = op ? 1 : 0;
            tr[p] = functions.get(ind).apply(tr[lf], tr[ri]);
        }
    }

    private void update(int p, int start, int end, int index, int value, boolean op) {
        if (index < start || index > end) return;
        if (start == end) {
            tr[p] = value;
        } else {
            int lf = p << 1;
            int ri = lf + 1;
            int mid = start + (end - start) / 2;
            update(lf, start, mid, index, value, !op);
            update(ri, mid + 1, end, index, value, !op);
            int ind = op ? 1 : 0;
            tr[p] = functions.get(ind).apply(tr[lf], tr[ri]);
        }
    }

//
//    private int query(int p, int start, int end, int i, int j, boolean op) {
//        if (start >= i && end <= j) return tr[p];
//        if (end < i || start > j) return -1;
//        int lf = p << 1;
//        int ri = lf + 1;
//        int mid = start + (end - start) / 2;
//        int lans = query(lf, start, mid, i, j, !op);
//        int rans = query(ri, mid + 1, end, i, j, !op);
//        if (lans == -1) return rans;
//        if (rans == -1) return lans;
//        int ind = op ? 1 : 0;
//
//    }
}
