package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AndOperation {
    MiscUtility ut;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        ut = new MiscUtility(false);
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        Arrays.sort(a);
        int mx = 31 - Integer.numberOfLeadingZeros(a[n-1]);
        int ans = 0;
        List<Integer> curr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            curr.add(a[i]);
        }
        for (int i = mx; i >= 0; i--) {
            ut.printDebug(curr, "curr");
            int cnt = 0;
            List<Integer> list = new ArrayList<>();
            int cn = curr.size();
            for (int j = 0; j < cn; j++) {
                if ((curr.get(j)&(1<<i)) > 0) {
                    cnt++;
                    list.add(curr.get(j));
                }
            }
            if (cnt > 1) {
                ans |= (1<<i);
                curr = list;
            }
        }
        out.println(ans);
    }
}
