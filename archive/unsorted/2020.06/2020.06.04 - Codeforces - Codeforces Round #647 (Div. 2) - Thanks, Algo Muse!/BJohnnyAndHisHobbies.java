package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class BJohnnyAndHisHobbies {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] s = in.nextIntArray(n);
        Set<Integer> st = new HashSet<>();
        for (int i = 0; i < n; i++) {
            st.add(s[i]);
        }
        int ans = -1;
        for (int k = 1; k < 1024; k++) {
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                int tval = s[i]^k;
                if (!st.contains(tval)) {
                    flag = false;
                }
            }
            if (flag) {
                ans = k;
                break;
            }
        }
        out.println(ans);
    }
}
