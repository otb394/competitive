package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MathUtility;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class IDCodes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (true) {
            String s = in.nextString();
            if ("#".equals(s)) {
                break;
            }
            char[] c = s.toCharArray();
            int len = c.length;
            Integer[] arr = new Integer[len];
            for (int i = 0; i < len; i++) {
                arr[i] = i;
            }

            boolean done = MathUtility.next_permutation(arr, 0, len, Comparator.comparingInt(ii -> c[ii]));
            char[] ans = new char[len];
            for (int i = 0; i < len; i++) {
                ans[i] = c[arr[i]];
            }
            if (done) {
                out.println(new String(ans));
            } else {
                out.println("No Successor");
            }
        }
    }
}
