package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class HBinaryMedian {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();int m = in.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextString();
        }
        long mx = (1L<<m) - 1L;
        long ans = mx/2L;
        Set<Long> removed = new HashSet<>();
        for (int i = 0; i < n; i++) {
            long val = conv(a[i]);
            ans = getNextMedian(ans, mx, removed, val);
        }
        String pans = conv(ans, m);
        out.println(pans);
    }

    private long getNextMedian(long med, long mx, Set<Long> removed, long remove) {
        long curr = (mx+1L) - removed.size();
        removed.add(remove);
        if ((curr&1L) == 0L) {
            if (remove > med) {
                return med;
            } else {
                return getNext(med, removed, 1, mx);
            }
        } else {
            if (remove >= med) {
                return getNext(med, removed, -1, mx);
            } else {
                return med;
            }
        }
    }

    private long getNext(long val, Set<Long> removed, int dir, long mx) {
        if (dir == -1) {
            for (long i = val-1L; i >= 0L; i--) {
                if (!removed.contains(i)) {
                    return i;
                }
            }
            throw new RuntimeException("Unexpected");
        } else {
            for (long i = val+1L; i <= mx; i++) {
                if (!removed.contains(i)) {
                    return i;
                }
            }
            throw new RuntimeException("Unexpected");
        }
    }

    private long conv(String s) {
        int m = s.length();
        long ans = 0L;
        long mult = 1L;
        for (int i = m-1; i >= 0; i--) {
            ans += mult * (s.charAt(i) - '0');
            mult<<=1L;
        }
        return ans;
    }

    private String conv(long num, int m) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < m; i++) {
            builder.append('0');
        }
        int ind = m-1;
        while(num > 0L && ind >= 0) {
            if ((num&1L) != 0L) {
                builder.setCharAt(ind, '1');
            }
            num>>=1L;
            ind--;
        }
        return builder.toString();
    }
}
