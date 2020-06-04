package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;

public class BMariaBreaksTheSelfIsolation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        for (int i = 0; i < n; i++) {
            a[i]++;
        }
        Arrays.sort(a);
        int ans = n+1;
        for (int i = n-1; i >= 0; i--) {
            if (a[i] <= ans) {
                break;
            } else {
                ans--;
            }
        }
        out.println(ans);
    }
}
