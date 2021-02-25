package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;

import java.util.ArrayList;
import java.util.List;

public class LoansomeCarBuyer {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (true) {
            int mon = in.nextInt();
            if (mon < 0) {
                break;
            }
            double down = in.nextDouble();
            double amt = in.nextDouble();
            int rec = in.nextInt();
            List<Pair<Integer, Double>> arr = new ArrayList<>();
            for (int i = 0; i < rec; i++) {
                arr.add(Pair.of(in.nextInt(), in.nextDouble()));
            }
            double dep = arr.get(0).getRight();
            double val = (down + amt) * (1.0 - dep);
            double owed = amt;
            double install = amt / ((double)mon);
            int cur = 1;
            int ari = 1;
            while (owed >= val) {
                if (ari < rec && cur == arr.get(ari).getLeft()) {
                    dep = arr.get(ari).getRight();
                    ari++;
                }
                val *= (1.0 - dep);
                owed -= install;
                cur++;
            }
            int ans = cur-1;
            String suff = (ans == 1) ? "month" : "months";
            out.println(ans + " " + suff);
        }
    }
}
