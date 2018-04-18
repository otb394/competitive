package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class EnormousInputTest {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n,k;
        n = in.nextInt();
        k = in.nextInt();
        int cnt = 0;
        while(n-- > 0) {
            int t = in.nextInt();
            if (t%k == 0) {
                cnt++;
            }
        }
        out.print(cnt);
    }
}
