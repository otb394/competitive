package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class CRotationMatching {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] b = in.nextIntArray(n);
        for (int i = 0; i < n; i++) {
            a[i]--;
            b[i]--;
        }
        int[] bpos = new int[n];
        for (int i = 0; i < n; i++) {
            bpos[b[i]] = i;
        }
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            int val = a[i];
            int j = bpos[val];
            int sh;
            if (j >= i) {
                sh = j-i;
            } else {
                sh = n-(i-j);
            }
            nums[sh]++;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, nums[i]);
        }
        out.println(ans);
    }
}
