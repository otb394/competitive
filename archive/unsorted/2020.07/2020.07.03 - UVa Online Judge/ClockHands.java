package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

public class ClockHands {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (true) {
            String s = in.nextString();
            if ("0:00".equals(s)) {
                break;
            }
            String[] tokens = s.split(":");
            int h = Integer.parseInt(tokens[0]);
            int m = Integer.parseInt(tokens[1]);
            double ah = getAngleHour(h);
            double am = getAngleMin(m);
            ah += (am / 360.0) * (30.0);
            double ans = Math.abs(ah - am);
            ut.printDebug(ans, "ans", h, "h", m, "m", ah, "ah", am, "am");
            if (ans > 180.0) {
                ans = 360 - ans;
            }
            out.printf("%.3f\n", ans);
        }
    }

    private double getAngle(int hand, int total) {
        return (360.0 / ((double) total)) * ((double) hand);
    }

    private double getAngleMin(int hand) {
        return getAngle(hand, 60);
    }

    private double getAngleHour(int hand) {
        return getAngle(hand % 12, 12);
    }
}
