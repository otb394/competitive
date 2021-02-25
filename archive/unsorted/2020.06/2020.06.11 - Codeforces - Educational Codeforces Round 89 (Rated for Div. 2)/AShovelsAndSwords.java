package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

public class AShovelsAndSwords {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }
        if (b >= 2*a) {
            out.println(a);
            return;
        }
        int diff = b-a;
        if (diff >= a) {
            out.println(a);
        } else {
            int rem = a-diff;
//            MiscUtility.printDebug(rem, "rem");
            int ans = diff;
            ans += (rem/3) * 2;
            if (rem % 3 == 2) {
                ans++;
            }
            out.println(ans);
        }
    }
}
