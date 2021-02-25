package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BRestoreThePermutationByMerger {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int N = n << 1;
        int[] a = in.nextIntArray(N);
        Set<Integer> st = new HashSet<>();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (st.contains(a[i])) {
                continue;
            } else {
                ans.add(a[i]);
                st.add(a[i]);
            }
        }
        for(int val : ans) {
            out.print(val + " " );
        }
        out.println();
    }
}
