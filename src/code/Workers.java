package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class Workers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = in.nextInt();
        }
        int[] t = new int[n];
        for (int i = 0; i < n; i++) {
            t[i] = in.nextInt();
        }
        int min1, min2, min3;
        min1 = getMin(1, t, c, n);
        min2 = getMin(2, t, c, n);
        min3 = getMin(3, t, c, n);
        if (min1 < Integer.MAX_VALUE && min2 < Integer.MAX_VALUE) {
            out.print(Math.min(min1+min2, min3));
        } else {
            out.print(min3);
        }
    }

    private int getMin(int type, int[] t, int[] c, int n) {
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (t[i] == type) {
                ret = Math.min(ret, c[i]);
            }
        }
        return ret;
    }
}
