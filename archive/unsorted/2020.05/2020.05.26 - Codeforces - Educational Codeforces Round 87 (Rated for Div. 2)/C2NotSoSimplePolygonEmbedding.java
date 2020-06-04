package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class C2NotSoSimplePolygonEmbedding {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        n<<=1;
        double ang = (Math.PI) * ((double)(n-2)) / ((double)n);
        double theta = ang/2.0;
        double r = 0.5/Math.cos(theta);
        double D = 2.0*r;
        double alpha = theta + Math.PI / (2.0*n);
        double ans = D * Math.sin(alpha);
        out.println(ans);
    }
}
