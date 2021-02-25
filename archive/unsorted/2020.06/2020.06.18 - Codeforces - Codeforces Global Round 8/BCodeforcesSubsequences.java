package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class BCodeforcesSubsequences {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long k = in.nextLong();
        long val = 1;
        long[] arr = new long[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = 1;
        }
        int index = 0;
        while (val < k) {
            val /= arr[index];
            arr[index]++;
            val *= arr[index];
            index = (index + 1) % 10;
        }
        StringBuilder builder = new StringBuilder();
        String s = "codeforces";
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < arr[i]; j++) {
                builder.append(s.charAt(i));
            }
        }
        out.println(builder.toString());
    }
}
