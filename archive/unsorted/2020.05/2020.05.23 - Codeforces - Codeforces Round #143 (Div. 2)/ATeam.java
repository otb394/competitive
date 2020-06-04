package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class ATeam {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int count = 0;
        for (int i = 0; i < n; i++) {
            int x,y,z;
            x=in.nextInt();
            y = in.nextInt();
            z = in.nextInt();
            if (x + y + z >= 2) count++;
        }
        out.print(count);
    }
}
