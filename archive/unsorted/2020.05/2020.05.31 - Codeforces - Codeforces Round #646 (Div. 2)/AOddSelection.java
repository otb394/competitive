package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class AOddSelection {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        int[] a = in.nextIntArray(n);
        int e = 0;
        int o = 0;
        for (int i = 0; i < n; i++) {
            if (a[i]%2 == 0) {
                e++;
            } else {
                o++;
            }
        }

        int om = Math.min(o, x);
        boolean ans = false;
        for (int i = 1; i <= om; i+=2) {
            int ev = x-i;
            if (ev <= e) {
                ans = true;
                break;
            }
        }
        out.println(ans ? "Yes" : "No");
    }
}
