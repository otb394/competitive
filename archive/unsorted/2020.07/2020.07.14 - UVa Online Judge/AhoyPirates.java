package code;



import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.SegmentTree;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AhoyPirates {
    boolean[] a;
    int[] tr;
    Map<Integer, Character> lazy;
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        StringBuilder builder = new StringBuilder();
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int t = in.nextInt();
            String ts = in.nextString();
            for (int j = 0; j < t; j++) {
                builder.append(ts);
            }
        }
        String arr = builder.toString();
        int n = arr.length();
        a = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (arr.charAt(i) == '0') {
                a[i] = false;
            } else {
                a[i] = true;
            }
        }
        int q = in.nextInt();
        int N = 4*n+1;
        ut.printDebug(n, "n", N, "N");
        tr = new int[N];
        lazy = new HashMap<>();
        out.printf("Case %d:\n", testNumber);
        int qc = 0;
        build(1, 0, n-1);
        ut.printDebug(() -> arr, () -> "arr", () -> n, () -> "n", () -> Arrays.stream(tr).boxed().collect(Collectors.toList()), () -> "tr");
        for (int i = 0; i < q; i++) {
            int finalI = i;
            int finalI1 = i;
            ut.printDebug(() -> finalI1, () -> "i", () -> SegmentTree.toString(tr, 1, 0, n-1), () -> "tr");
            char ch = in.nextCharacter();
            int l = in.nextInt();
            int h = in.nextInt();
            ut.printDebug(l, "l", h, "h", ch, "ch");
            if (ch == 'F' || ch == 'E' || ch == 'I') {
                update(1, 0, n-1, l, h, ch);
            } else {
                qc++;
                int ans = query(1, 0, n-1, l, h);
                ut.printDebug(ans, "ans");
                out.printf("Q%d: %d\n", qc, ans);
            }
        }
    }

    private void build(int p, int start, int end) {
        if (start == end) {
            if (a[start]) tr[p] = 1;
            else tr[p] = 0;
        } else {
            int lf = p<<1;
            int ri = lf + 1;
            int mid = start + (end - start) / 2;
            build(lf, start, mid);
            build(ri, mid + 1, end);
            tr[p] = tr[lf] + tr[ri];
        }
    }

    private void update(int p, int start, int end, int i, int j, char op) {
        if (end < i || start > j) return;
        if (start >= i && end <= j) {
            if (op == 'F') {
                tr[p] = end - start + 1;
            } else if (op == 'E') {
                tr[p] = 0;
            } else if (op == 'I') {
                tr[p] = (end - start + 1) - tr[p];
            } else {
                MiscUtility.assertion(false, op + " is unrecognized.");
            }
//            li[p] = op;
            if (lazy.containsKey(p)) {
                char newOp = newOp(lazy.get(p), op);
                if (newOp == 'N') {
                    lazy.remove(p);
                } else {
                    lazy.put(p, newOp);
                }
            } else {
                lazy.put(p, op);
            }
        } else {
            int lf = p <<1;
            int ri = lf + 1;
            int mid = start + (end - start) / 2;
            if (lazy.containsKey(p)) {
                char lip = lazy.get(p);
                update(lf, start, mid, start, mid, lip);
                update(ri, mid + 1, end, mid + 1, end, lip);
                lazy.remove(p);
            }
            update(lf, start, mid, i, j, op);
            update(ri, mid + 1, end, i, j, op);
            tr[p] = tr[lf] + tr[ri];
        }
    }

    private int query(int p, int start, int end, int i, int j) {
        if (start >= i && end <= j) return tr[p];
        if (start > j || end < i) return 0;
        int lf = p << 1;
        int ri = lf + 1;
        int mid = start + (end - start) / 2;
        if (lazy.containsKey(p)) {
            char lip = lazy.get(p);
            update(lf, start, mid, start, mid, lip);
            update(ri, mid + 1, end, mid + 1, end, lip);
            lazy.remove(p);
        }
        return query(lf, start, mid, i, j) + query(ri, mid + 1, end, i, j);
    }

    private char newOp(char old, char op) {
        if (op == 'F' || op == 'E') return op;
        if (old == 'F') return 'E';
        if (old == 'E') return 'F';
        return 'N';
    }
}
