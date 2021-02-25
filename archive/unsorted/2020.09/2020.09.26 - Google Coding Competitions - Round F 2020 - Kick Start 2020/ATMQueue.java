package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MathUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ATMQueue {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] rounds = new int[n];
        for (int i = 0; i < n; i++) {
            rounds[i] = MathUtility.ceil(a[i], x);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ans.add(i);
        }
        Comparator<Integer> comp = (i, j) -> {
            if (rounds[i] != rounds[j]) {
                return Integer.compare(rounds[i], rounds[j]);
            }
            return Integer.compare(i, j);
        };
        ans.sort(comp);
        out.printf("Case #%d: ", testNumber);
        for (int val : ans) {
            out.print((val+1) + " ");
        }
        out.println();
    }
}
