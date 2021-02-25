package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

public class BArrayWalk {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int z = in.nextInt();
        int[] a = in.nextIntArray(n);
        int ans = 0;
        for (int i = 0; i <= k; i++) {
            ans += a[i];
        }
        for (int i = 1; i <= z; i++) {
            int rt = k - i;
            if (rt >= i) {
                int tans = 0;
                int mx = rt - i;
                int mp = 0;
                for (int j = 0; j < mx; j++) {
                    mp = Math.max(mp, a[j] + a[j+1]);
                }
                for (int j = 0; j <= mx; j++) {
                    tans += a[j];
                }
                int ttans = tans;
                tans += mp * i;
                int mpp = 0;
                for (int j = 0; j <= mx; j++) {
                    mpp = Math.max(mpp, a[j] + a[j+1]);
                }
                ttans += mpp * (i-1) + (a[mx] + a[mx+1]);
                ut.printDebug(n, "n", k, "k", z, "z", i, "i", rt, "rt", mp, "mp", tans, "tans", ttans, "ttans", ans, "ans", mx, "mx", mpp, "mpp");
                ans = Math.max(ans, Math.max(tans, ttans));
            }
//            int mx = k - 2 * i;
//            int tans = 0;
//            if (mx < n && mx >= 0) {
//                int mp = a[0] + a[1];
//                for (int j = 1; j < mx; j++) {
//                    mp = Math.max(mp, a[j] + a[j+1]);
//                }
//                for (int j = 0; j <= mx; j++) {
//                    tans += a[j];
//                }
//                tans += mp * i;
//            }
        }
        out.println(ans);
    }
}
