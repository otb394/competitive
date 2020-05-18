package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class AIchihimeAndTriangle {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long a=in.nextLong();long b=in.nextLong();long c=in.nextLong();long d=in.nextLong();
        long x=b;long y=c;long z=Math.min(b+c-1,d);
        out.println(x + " " + y + " " + z);
    }
}
