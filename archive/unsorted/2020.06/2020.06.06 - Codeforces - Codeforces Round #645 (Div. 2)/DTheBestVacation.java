package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import algorithms.BinarySearch;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class DTheBestVacation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int N = in.nextInt();
        long x = in.nextLong();
        int[] D = in.nextIntArray(N);
        int[] d = Stream.concat(Arrays.stream(D).boxed(), Arrays.stream(D).boxed()).mapToInt(i -> i).toArray();
        int n = 2*N;
        long ans = 0L;
        long[] pre = new long[n];
        pre[0] = d[0];
        long[] monthVal = new long[n];
        long[] preMonVal = new long[n];
        for (int i = 0; i < n; i++) {
            long dl = d[i];
            monthVal[i] = (dl * (dl+1L))/2L;
        }
        preMonVal[0] = monthVal[0];
        for (int i = 1; i < n; i++) {
            preMonVal[i] = preMonVal[i-1] + monthVal[i];
        }
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i-1] + d[i];
        }
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, getStart(i, n, x, pre, preMonVal));
        }
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, getEnd(i, n, x, pre, preMonVal, d));
        }
        out.println(ans);
    }

    private long getStart(int i, int n, long x, long[] pre, long[] preMonthVal) {
        Function<Integer, Boolean> func = ind -> getPreCount(i, ind, pre) > x;
        int j = BinarySearch.searchLastZero(i-1, n, func);
        long done = getPreCount(i, j, pre);
        if (j == i-1) {
            return (x * (x + 1L)) / 2L;
        }
        long rem = x-done;
        if (j == n-1) {
            if (rem != 0L) {
                return 0L;
            } else {
                return getPreCount(i, j, preMonthVal);
            }
        }
        long ret = getPreCount(i, j, preMonthVal);
        ret += (rem * (rem + 1L))/2L;
        return ret;
    }

    private long getPreCount(int i, int j, long[] pre) {
        if (i > j) return 0L;
        if (i == 0) return pre[j];
        return pre[j] - pre[i-1];
    }

    private long getEnd(int i, int n, long x, long[] pre, long[] preMonthVal, int[] d) {
        Function<Integer,Boolean> func = ind -> getPreCount(ind, i, pre) <= x;
        int j = BinarySearch.searchFirstOne(-1, i+1, func);
        if (j == (i+1)) {
            return getFromEndOfMonth(x, d[i]);
        }
        long done = getPreCount(j, i, pre);
        long rem = x-done;
        if (j == 0) {
            if (rem != 0L) {
                return 0L;
            } else {
                return getPreCount(j, i, preMonthVal);
            }
        }
        long ret = getPreCount(j, i, preMonthVal);
        ret += getFromEndOfMonth(rem, d[j-1]);
        return ret;
    }

    private long getFromEndOfMonth(long x, long tot) {
        if (x > tot) {
            throw new RuntimeException(String.format("Total needed from month [%d] is larger than month size [%d]",
                    x, tot));
        }
        long base = tot - x;
        return (base * x) + ((x * (x+1L)) / 2L);
    }
}
