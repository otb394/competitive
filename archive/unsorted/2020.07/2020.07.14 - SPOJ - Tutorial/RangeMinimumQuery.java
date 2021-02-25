package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.SegmentTree;

public class RangeMinimumQuery {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int q = in.nextInt();
        SegmentTree st = new SegmentTree(a, n);
        for (int i = 0; i < q; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            out.println(st.query(u, v));
        }
    }
}
