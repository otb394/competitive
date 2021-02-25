package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;

public class DoctorChef {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] cap = new int[n];
        System.arraycopy(a, 0, cap, 0, a.length);
        int i = 0;
        long days = 0L;
        Arrays.sort(a);
        while (i < n) {
            int j = i +1;
            while (j < n && a[j] <= a[j-1]*2) {
                j++;
            }
            while (x < a[i]) {
                days++;
                a[j-1] -= x;
                x <<= 1;
                a[j-1] = Math.min(cap[j-1], a[j-1]*2);
            }
            for (int k = i; k < j; k++) {
                if (a[k] > 0) {
                    days++;
                    x = 2*a[k];
                    a[k] = 0;
                    if (a[j-1] > 0) {
                        a[j-1] = Math.min(cap[j-1], a[j-1]*2);
                    }
                }
            }
            i = j;
        }
        out.println(days);
    }
}
