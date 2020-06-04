package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class C1SimplePolygonEmbedding {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        n<<=1;
        double ang = (Math.PI) * ((double)(n-2)) / ((double)n);
        double theta = ang/2.0;
        double val = Math.tan(theta);
        out.println(val);
    }
}
