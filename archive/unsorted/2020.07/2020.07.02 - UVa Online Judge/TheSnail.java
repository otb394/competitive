package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

public class TheSnail {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (true) {
            int h = in.nextInt();
            if (h == 0) {
                break;
            }
            int u = in.nextInt();
            int d = in.nextInt();
            int f = in.nextInt();
            int day = 1;
            double c = u;
            double dis = 0.0;
            double red = ((double)u) * ((double)f)/100.0;
            ut.printDebug(red, "red");
            while (true) {
                ut.printDebug(day, "day", c, "climb", dis, "dis");
                if (c > 0) {
                    dis += c;
                }
                if (dis > h) {
                    out.printf("success on day %d\n", day);
                    break;
                }
                dis -= d;
                if (dis < 0) {
                    out.printf("failure on day %d\n", day);
                    break;
                }
                day++;
                c -= red;
            }
        }
    }
}
