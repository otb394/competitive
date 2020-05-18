package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class Countdown {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();
        int k=in.nextInt();
        int[] a=in.nextIntArray(n);
        int i=0;
        int cnt=0;
        while(i<n) {
            while(i<n && a[i]!=k) {
                i++;
            }
            int temp=k;
            while(i<n && a[i]==temp && temp>=1) {
                i++;
                temp--;
            }
            if (temp==0) {
                cnt++;
            }
        }
        out.println(String.format("Case #%d: %d", testNumber, cnt));
    }
}
