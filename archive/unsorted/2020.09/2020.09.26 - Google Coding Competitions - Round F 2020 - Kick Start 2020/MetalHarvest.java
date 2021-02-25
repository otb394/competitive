package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import util.MathUtility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MetalHarvest {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] s = new int[n];
        int[] e = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = in.nextInt();
            e[i] = in.nextInt();
        }
        List<Pair<Integer, Integer>> ranges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ranges.add(Pair.of(s[i], e[i]));
        }
        ranges.sort(Comparator.comparingInt(Pair::getLeft));
        int ans = 0;
        int i = 0;
        int currD = 0;
        while (i < n) {
            int rst = ranges.get(i).getLeft();
            int ren = ranges.get(i).getRight();
            if (currD < rst) currD = rst;
            int rem = ren - currD;
            int req = MathUtility.ceil(rem, k);
            int currE = currD + req * k;
            ans += req;
            int j = i;
            while (j < n && ranges.get(j).getRight() <= currE) {
                j++;
            }
            i = j;
            currD = currE;
        }

        out.printf("Case #%d: %d\n", testNumber, ans);
    }
}
