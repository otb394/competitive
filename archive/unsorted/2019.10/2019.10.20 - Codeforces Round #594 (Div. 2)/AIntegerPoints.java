package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class AIntegerPoints {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();long[] p=in.nextLongArray(n);
        int m=in.nextInt();long[] q=in.nextLongArray(m);
        long e1=0;
        long e2=0;
        long o1=0;
        long o2=0;
        for (int i = 0; i < n; i++) {
            if(p[i]%2==0) e1++;else o1++;
        }
        for (int i = 0; i < m; i++) {
            if(q[i]%2==0) e2++;else o2++;
        }
        long ans = e1*e2 + o1*o2;
        out.println(ans);
    }
}
