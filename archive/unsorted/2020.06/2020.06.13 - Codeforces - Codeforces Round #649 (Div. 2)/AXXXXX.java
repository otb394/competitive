package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class AXXXXX {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        int[] a = in.nextIntArray(n);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum+=a[i];
        }
        if (sum % x != 0) {
            out.println(n);
            return;
        }
        int i;
        for (i = 0; i < n; i++) {
            if (a[i] % x != 0) {
                break;
            }
        }
        if (i == n) {
            out.println(-1);
            return;
        }
        int[] pre = new int[n];
        pre[0] = a[0];
        for (int j = 1; j < n; j++) {
            pre[j] = pre[j-1] + a[j];
        }
        int fai = i;
        for (int j = i+1; j < n; j++) {
            if (pre[j] % x != 0) {
                fai = j;
            }
        }
        int ans = Math.max(fai + 1, n - i - 1);
        out.println(ans);
    }
}
