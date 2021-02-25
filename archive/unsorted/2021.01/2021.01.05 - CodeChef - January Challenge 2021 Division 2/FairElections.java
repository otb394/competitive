package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;

public class FairElections {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] b = in.nextIntArray(m);
        int f = 0;
        for (int val : a) f+= val;
        int s = 0;
        for (int val : b) s+=val;
        if (f <=s) {
            Arrays.sort(a);
            Arrays.sort(b);
            int i = 0;
            int j = m-1;
            int diff = s - f + 1;
            int ans = 0;
            while(diff > 0 && i < n && j >= 0 && a[i] < b[j]) {
                diff -= 2 * (b[j] - a[i]);
                i++;
                j--;
                ans++;
            }
            if (diff <= 0) {
                out.println(ans);
            } else {
                out.println(-1);
            }
        } else {
            out.println(0);
        }
    }
}
