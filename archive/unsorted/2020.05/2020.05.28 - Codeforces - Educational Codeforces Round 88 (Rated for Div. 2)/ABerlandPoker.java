package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MathUtility;

public class ABerlandPoker {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int each = n/k;
        if (m <= each) {
            out.println(m);
        } else {
            int left = m-each;
            int oth = k-1;
            int p = MathUtility.ceil(left, oth);
            out.println(each - p);
        }
    }
}
