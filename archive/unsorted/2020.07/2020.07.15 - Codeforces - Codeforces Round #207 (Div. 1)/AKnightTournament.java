package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.SegmentTree;
import util.MiscUtility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AKnightTournament {
    int[] tr;
    Map<Integer, Integer> lazy;
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        lazy = new HashMap<>();
        int n = in.nextInt();
        int m = in.nextInt();
        int[] l = new int[n];
        int[] r = new int[n];
        int[] x = new int[n];
        for (int i = 0; i < m; i++) {
            l[i] = in.nextInt();l[i]--;
            r[i] = in.nextInt();r[i]--;
            x[i] = in.nextInt();x[i]--;
        }
        int N = 4*n+1;
        tr = new int[N];
        Arrays.fill(tr, -1);
        for (int i = m-1; i >= 0; i--) {
            if (l[i] < x[i]) {
                update(1, 0, n - 1, l[i], x[i] - 1, x[i]);
            }
            if (x[i] < r[i]) {
                update(1, 0, n - 1, x[i] + 1, r[i], x[i]);
            }
        }

        if (ut.debug) {
            System.out.println("tr");
            System.out.println(SegmentTree.toString(tr, 1, 0, n-1));
        }

        int[] ans = new int[n];
//        ans[x[m-1]] = -1;
        for (int i = 0; i < n; i++) {
            ans[i] = query(1, 0, n-1, i, i) + 1;
        }
        out.print(ans);
    }

    private void update(int p, int start, int end, int i, int j, int val) {
        if (end < i || start > j) return;
        if (start >= i && end <= j) {
            tr[p] = val;
            if (start != end) {
                lazy.put(p, val);
            }
            return;
        }
        int lf = p << 1;
        int ri = lf + 1;
        int mid = start + (end - start) / 2;
        release(p, start, end);
        update(lf, start, mid, i, j, val);
        update(ri, mid + 1, end, i, j, val);
    }

    private int query(int p, int start, int end, int i, int j) {
        if (start > j || end < i) return -1;
        if (start >= i && end <= j) {
            return tr[p];
        }
        int lf = p << 1;
        int ri = lf + 1;
        int mid = start + (end - start) / 2;
        release(p, start, end);
        return Math.max(query(lf, start, mid, i, j), query(ri, mid + 1, end, i, j));
    }

    private void release(int p, int start, int end) {
        if (lazy.containsKey(p)) {
            int val = lazy.get(p);
            lazy.remove(p);
            int lf = p << 1;
            int ri = lf + 1;
            int mid = start + (end-start) / 2;
            update(lf, start, mid, start, mid, val);
            update(ri ,mid + 1, end, mid + 1, end, val);
        }
    }
}
