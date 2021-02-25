package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class DAGoodString {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        String s = in.nextString();
        int ans = getAns(0, 0, n-1, s);
        out.println(ans);
    }

    private int getAns(int ind, int i, int j, String s) {
        char c = get(ind);
        if (i == j) {
            return s.charAt(i) == c ? 0 : 1;
        }
        int mid = i + (j - i) / 2;
        int fo = 0;
        for (int k = i; k <= mid; k++) {
            if (s.charAt(k) != c) {
                fo++;
            }
        }
        int so = getAns(ind + 1, mid + 1, j, s);
        int ft = 0;
        for (int k = mid + 1; k <= j; k++) {
            if (s.charAt(k) != c) ft++;
        }
        int st = getAns(ind + 1, i, mid, s);
        return Math.min(fo + so, ft + st);
    }

    private char get(int i) {
        return (char) ('a' + i);
    }
}
