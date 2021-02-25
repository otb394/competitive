package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class ChefAndIcecream {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int f = 0;int t = 0; int s = 0;
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (a[i] == 10) {
                t++;
                if (f > 0) {
                    f--;
                } else {
                    flag = false;
                    break;
                }
            } else if (a[i] == 5) {
                f++;
            } else {
                s++;
                if (t > 0) {
                    t--;
                } else if (f >= 2) {
                    f-=2;
                } else {
                    flag = false;
                    break;
                }
            }
        }
        out.println(flag ? "YES" : "NO");
    }
}
