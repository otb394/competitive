package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Fraction;
import util.MiscUtility;

public class RequestForProposal {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = 1;
        while (true) {
            int n = in.nextInt();
            int p = in.nextInt();
            if (n == 0 && p == 0) {
                break;
            }
            if (t != 1) {
                out.println();
            }
            for (int i = 0; i < n; i++) {
                String s = in.readLine();
            }
            Fraction best = null;
            String bestName = "Dummy";
            double bestPrice = Double.MAX_VALUE;
            for (int i = 0; i < p; i++) {
                String name = in.readLine();
                ut.printDebug(name, "name");
                double pr = in.nextDouble();
                int num = in.nextInt();
                for (int j = 0; j < num; j++) {
                    String s = in.readLine();
                }
                Fraction compliance = new Fraction(num, n);
                if (best == null || compliance.compareTo(best) > 0
                        || ((compliance.compareTo(best) == 0) && (pr < bestPrice))) {
                    best = compliance;
                    bestName = name;
                    bestPrice = pr;
                }
            }
            out.printf("RFP #%d\n%s\n", t, bestName);
            t++;
        }
    }
}
