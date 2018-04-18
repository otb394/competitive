package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class CountGoodPrefixes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.nextString();
        long n = in.nextLong();
        int size = s.length();
        long[] C = getCumArray(size, s);

        long ans = 0L;
        long a = C[size-1];
        ans += getAnsZY(a, n);
        for (int y = 1; y < size; y++) {
            long b = C[y - 1];
            ans += getAnsNZY(a, b, n);
        }
        out.println(ans);
    }

    private long[] getCumArray(int size, String s) {
        long[] C = new long[size];
        long curr = 0L;
        for (int i = 0; i < size; i++) {
            if (s.charAt(i) == 'a') {
                curr++;
                C[i] = curr;
            } else {
                curr--;
                C[i] = curr;
            }
        }
        return C;
    }

    private long getAnsNZY(long a, long b, long n) {
        long x;
        if (a > 0L) {
            x = getMinX(b, a);
            return getCount(Math.max(0L, x), n-1L);
        } else if (a < 0L) {
            x = getMaxX(b, a);
            return getCount(0L, Math.min(x, n-1L));
        } else {
            if (b > 0L) {
                return getCount(0L, n-1L);
            } else {
                return 0L;
            }
        }
    }

    private long getAnsZY(long a, long n) {
        if (a>0L) {
            return getCount(1L, n);
        } else {
            return 0L;
        }
    }

    private long getMinX(long b, long a) {
        return Math.floorDiv(-b, a) + 1L;
    }

    private long getMaxX(long b, long a) {
        long negB = -b;
        if (Math.floorMod(negB, a) == 0L) {
            return Math.floorDiv(negB, a) - 1L;
        } else {
            return Math.floorDiv(negB, a);
        }
    }

    private long getCount(long min, long max) {
        if (min <= max) {
            return max - min + 1L;
        } else {
            return 0L;
        }
    }
}
