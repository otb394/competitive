package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import util.MiscUtility;

public class BMultiplyBy2DivideBy6 {
    MiscUtility ut;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        ut = new MiscUtility(false);
        int n = in.nextInt();
        try {
            Pair<Integer, Integer> pr = getFactors(n);
            ut.printDebug(pr, "pr");
            if (pr.getLeft() > pr.getRight()) {
                out.println(-1);
            } else {
                out.println(pr.getLeft() + (2 * (pr.getRight() - pr.getLeft())));
            }
        } catch (Exception e) {
            out.println(-1);
        }
    }

    private Pair<Integer, Integer> getFactors(int n) {
        int o = 0;
        while (n%2 == 0) {
            o++;
            n/=2;
        }

        int s = 0;
        while (n%3 == 0) {
            s++;
            n/=3;
        }
        if (n != 1) {
            throw new RuntimeException();
        }
        return Pair.of(o, s);
    }
}
