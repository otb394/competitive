package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import algorithms.BinarySearch;

public class IncrementalHouseOfPancakes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long l=in.nextLong();
        long r=in.nextLong();
        long ans;
        if (l >= r) {
            long diff = l - r;
            long init = getMaxN(1, 1, diff);
            l-= getAPSum(1, 1, init);
            if (l>=r) {
                long p=getMaxN(init+1, 2, l);
                long q=getMaxN(init+2,2, r);
                if (p <= q) {
                    ans = init+2*p;
                    l-=getAPSum(init+1,2,p);
                    r-=getAPSum(init+2,2,p);
                } else {
                    ans = init+2*q+1L;
                    l-=getAPSum(init+1,2,q+1);
                    r-=getAPSum(init+2,2,q);
                }
            } else {
                long p=getMaxN(init+1, 2, r);
                long q=getMaxN(init+2,2, l);
                if (p <= q) {
                    ans = init+2*p;
                    r-=getAPSum(init+1,2,p);
                    l-=getAPSum(init+2,2,p);
                } else {
                    ans = init+2*q+1L;
                    r-=getAPSum(init+1,2,q+1);
                    l-=getAPSum(init+2,2,q);
                }
            }
        } else {
            long diff = r - l;
            long init = getMaxN(1, 1, diff);
            r-= getAPSum(1, 1, init);
            if (l>=r) {
                long p=getMaxN(init+1, 2, l);
                long q=getMaxN(init+2,2, r);
                if (p <= q) {
                    ans = init+2*p;
                    l-=getAPSum(init+1,2,p);
                    r-=getAPSum(init+2,2,p);
                } else {
                    ans = init+2*q+1L;
                    l-=getAPSum(init+1,2,q+1);
                    r-=getAPSum(init+2,2,q);
                }
            } else {
                long p=getMaxN(init+1, 2, r);
                long q=getMaxN(init+2,2, l);
                if (p <= q) {
                    ans = init+2*p;
                    r-=getAPSum(init+1,2,p);
                    l-=getAPSum(init+2,2,p);
                } else {
                    ans = init+2*q+1L;
                    r-=getAPSum(init+1,2,q+1);
                    l-=getAPSum(init+2,2,q);
                }
            }
        }

        out.println(String.format("Case #%d: %d %d %d", testNumber, ans, l, r));
    }

    private long getMaxN(long a, long d, long v) {
        long maxN = 2123456789L;
        return BinarySearch.searchLastZero(1L, maxN, n -> (getAPSum(a,d,n) > v));
    }
    private long getAPSum(long a, long d, long n) {
        return (n * (2L*a + (n-1L)*d))/2L;
    }
}
