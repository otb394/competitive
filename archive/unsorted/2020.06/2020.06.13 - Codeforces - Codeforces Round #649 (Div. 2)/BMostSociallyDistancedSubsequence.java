package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class BMostSociallyDistancedSubsequence {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] p = in.nextIntArray(n);
        List<Integer> ansSeq = new ArrayList<>();
        ansSeq.add(p[0]);
        for (int i = 1; i < (n-1); i++) {
            if (p[i] > p[i-1] && p[i] > p[i+1]) {
                ansSeq.add(p[i]);
            } else if (p[i] < p[i-1] && p[i] < p[i+1]) {
                ansSeq.add(p[i]);
            }
        }
        ansSeq.add(p[n-1]);
        out.println(ansSeq.size());
        for (int val : ansSeq) {
            out.print(val + " ");
        }
        out.println();
    }
}
