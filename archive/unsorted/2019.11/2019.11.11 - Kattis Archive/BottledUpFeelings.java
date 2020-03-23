package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class BottledUpFeelings {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int s=in.nextInt();int v=in.nextInt();int u=in.nextInt();
        int maxp = s/v;
        int ans = -1;
        int ap = -1;int aq=-1;
        for (int p = 0; p <= maxp; p++) {
            int rem = s-p*v;
            if ((rem % u) == 0) {
                int q = rem/u;
                if (ans == -1) {
                    ans = p+q;
                    ap=p;aq=q;
                } else {
                    int temp = p+q;
                    if (temp < ans) {
                        ans = temp;
                        ap = p;aq=q;
                    }
                }
            }
        }
        if (ans == -1) {
            out.print("Impossible");
        } else {
            out.print(ap, aq);
        }
    }
}
