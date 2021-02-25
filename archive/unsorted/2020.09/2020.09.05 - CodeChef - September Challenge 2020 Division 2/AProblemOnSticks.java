package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Set;
import java.util.TreeSet;

public class AProblemOnSticks {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        Set<Integer> st = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            st.add(a[i]);
        }
        st.remove(0);
        out.println(st.size());
    }
}
