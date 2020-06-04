package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import algorithms.BinarySearch;
import datastructure.Fraction;

import java.util.function.Function;

public class CMixingWater {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long h = in.nextLong();h<<=1L;
        long c = in.nextLong();c<<=1L;
        long t = in.nextLong();t<<=1L;
        long a = (h+c)/2L;
        if (t <= a) {
            out.println(2);
        } else {
            h>>=1L;
            c>>=1L;
            t>>=1L;
            long finalH = h;
            long finalC = c;
            long finalT = t;
            Fraction ft = new Fraction(finalT, 1L);
            Function<Long, Boolean> func =
                    some -> ((getTemp(some, finalH, finalC).compareTo(ft)) < 0);
            long mx = BinarySearch.getMax(1, func);
            long intx = BinarySearch.searchLastZero(0L, mx, func);
            long othx = BinarySearch.searchFirstOne(0L, mx, func);
            Fraction val = getTemp(intx, h, c);
            Fraction vall = getTemp(othx, h, c);
            if (val.sub(ft).abs().compareTo(vall.sub(ft).abs()) <= 0) {
                out.println(2L*intx+1L);
            } else {
                out.println(2L*othx+1L);
            }
        }
    }

    private Fraction getTemp(long x, long h, long c) {
        return new Fraction(x*h + h + x*c, 2*x + 1);
    }

}
