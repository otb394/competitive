package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class ChefWarsReturnOfTheJedi {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int h = in.nextInt();
        int p = in.nextInt();
        while (p > 0 && h > 0) {
            h -= p;
            p = p >> 1;
        }
        if (p == 0 && h > 0) {
            out.println(0);
        } else {
            out.println(1);
        }
    }
}
