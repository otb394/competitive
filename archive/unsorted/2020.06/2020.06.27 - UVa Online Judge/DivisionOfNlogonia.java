package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class DivisionOfNlogonia {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (true) {
            int k = in.nextInt();
            if (k == 0) break;
            int n = in.nextInt();
            int m = in.nextInt();
            for (int i = 0; i < k; i++) {
                int x = in.nextInt();x-=n;
                int y = in.nextInt();y-=m;
                if (x == 0 || y == 0) {
                    out.println("divisa");
                } else if (x > 0 && y > 0) {
                    out.println("NE");
                } else if (x > 0) {
                    out.println("SE");
                } else if (y > 0) {
                    out.println("NO");
                } else {
                    out.println("SO");
                }
            }
        }
    }
}
