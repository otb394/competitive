package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class CJohnnyAndAnotherRatingDrop {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextLong();
        long ans = 0L;
        if (n%2L == 0L) {
            ans+=n/2L;
        } else {
            ans += (n/2L) + 1L;
        }
        ans += getOdd(n);
        out.println(ans);
    }

    private long getOdd(long n) {
        long t = 1L;
        long r = 2L;
        long ret = 0L;
        while (t<n) {
            long temp = n>>r;
            ret += temp * r;
            long tv = t | (temp << r);
            if (tv < n) ret+=r;
            t<<=1L;
            t++;
            r++;
        }
        return ret;
    }
}
