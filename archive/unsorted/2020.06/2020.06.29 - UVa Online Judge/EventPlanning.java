package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

public class EventPlanning {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        try {
            while (true) {
                int n = in.nextInt();
                int p = in.nextInt();
                int h = in.nextInt();
                int w = in.nextInt();
                int ans = Integer.MAX_VALUE;
                boolean found = false;
                for (int i = 0; i < h; i++) {
                    int pr = in.nextInt();
                    boolean app = false;
                    int[] beds = in.nextIntArray(w);
                    for (int j = 0; j < w; j++) {
                        int bed = beds[j];
                        if (bed >= n) {
                            app = true;
                            break;
                        }
                    }
                    if (app) {
                        ans = Math.min(ans, pr * n);
                        found = true;
                    }
                }
                ut.printDebug(found, "found", ans, "ans", p, "p");
                found = found && (ans <= p);
                out.println(found ? ans : "stay home");
            }
        } catch (Exception e) {

        }
    }
}
