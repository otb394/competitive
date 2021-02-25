package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaheshAndHisLostArray {
    MiscUtility ut;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        ut = new MiscUtility(false);
        int n = in.nextInt();
        int N = (1<<n);
        int[] a = in.nextIntArray(N);
        Arrays.sort(a);
        if (n == 1) {
            out.println(a[1]);
            return;
        }
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> rem = new HashMap<>();
        Map<Integer, Integer> obs = new HashMap<>();
        ut.printDebug(n, "n");
        ans.add(a[1]);
        ans.add(a[2]);
        add(rem, a[1]);
        add(rem, a[2]);
        add(rem, a[1] + a[2]);
        add(obs, a[1]);
        add(obs, a[2]);
        for (int i = 3; i < N; i++) {
            int rv = rem.getOrDefault(a[i], 0);
            int ov = obs.getOrDefault(a[i], 0);
            if (rv <= ov) {
                ans.add(a[i]);
                List<Integer> list = new ArrayList<>();
                for (Map.Entry<Integer, Integer> entry: rem.entrySet()) {
                    int key = entry.getKey();
                    int val = entry.getValue();
                    for (int j = 0; j < val; j++) {
                        int nval = key + a[i];
                        list.add(nval);
//                        add(rem, nval);
                    }
                }
                for (int tval : list) {
                    add(rem, tval);
                }
                add(rem, a[i]);
            }
            add(obs, a[i]);
        }
        MiscUtility.assertion(ans.size() == n, String.format("ans.size() [%d] is not eq to n [%d]", ans.size(),
                n));
        ans.sort(Integer::compareTo);
        for (int val : ans) {
            out.print(val + " " );
        }
        out.println();
    }

    private void add(Map<Integer, Integer> mp, int key) {
        int val = mp.getOrDefault(key, 0);
        mp.put(key, val + 1);
    }
}
