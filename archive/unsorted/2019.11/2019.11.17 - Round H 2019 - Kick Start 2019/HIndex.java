package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class HIndex {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a=in.nextIntArray(n);
        int h=0;
        int cnt=0;
        int[] ans = new int[n];
        int[] f = new int[100001];
        for (int i = 0; i < n; i++) {
            f[a[i]]++;
            if (a[i]<=h) {
                ans[i] = h;
            } else {
                cnt++;
                while (cnt >= (h+1)) {
                    cnt -= f[h+1];
                    h++;
                }
                ans[i]=h;
            }
        }
        out.printf("Case #%d: ", testNumber);
        out.println(ans);
    }
}
