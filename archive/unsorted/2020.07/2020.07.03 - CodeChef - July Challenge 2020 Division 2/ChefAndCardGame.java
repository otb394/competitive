package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class ChefAndCardGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            b[i] = in.nextInt();
        }
        int pa = 0;
        int pb = 0;
        for (int i = 0; i < n; i++) {
            long tpa = getPower(a[i]);
            long tpb = getPower(b[i]);
            if (tpa > tpb) {
                pa++;
            } else if (tpa < tpb) {
                pb++;
            } else {
                pa++;
                pb++;
            }
        }
        if (pa > pb) {
            out.println(0 + " " + pa);
        } else if (pb > pa) {
            out.println(1 + " " + pb);
        } else {
            out.println(2 + " " + pa);
        }
    }

    private long getPower(int n) {
        return Integer.toString(n).chars().map(cp -> (cp - ((int)('0')))).sum();
    }
}
