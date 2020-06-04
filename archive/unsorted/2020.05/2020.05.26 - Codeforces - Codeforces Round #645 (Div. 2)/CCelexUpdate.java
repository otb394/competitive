package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.math.BigInteger;

public class CCelexUpdate {
    public BigInteger two = BigInteger.ONE.add(BigInteger.ONE);
    public BigInteger three = two.add(BigInteger.ONE);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long x1a = in.nextLong();BigInteger x1 = new BigInteger(Long.toString(x1a));
        long y1a = in.nextLong();BigInteger y1 = new BigInteger(Long.toString(y1a));
        long x2a = in.nextLong();BigInteger x2 = new BigInteger(Long.toString(x2a));
        long y2a = in.nextLong();BigInteger y2 = new BigInteger(Long.toString(y2a));
//        BigInteger a = Math.abs(x2-x1);
//        BigInteger b = Math.abs(y2-y1);
        BigInteger a = getRowSum(x1, y1, y2);
        BigInteger b, c;
        b = getColSum(y2, x1, x2);
        c = getCell(x1, y2);
//        BigInteger so = a + b - c;
        BigInteger so = a.add(b).subtract(c);
        a = getRowSum(x2, y1, y2);
        b = getColSum(y1, x1, x2);
        c = getCell(x2, y1);
//        BigInteger st = a + b - c;
        BigInteger st = a.add(b).subtract(c);
//        out.println(st-so+1L);
        out.println(st.subtract(so).add(BigInteger.ONE));
    }

    private BigInteger getSum(BigInteger a, BigInteger x, BigInteger n) {
//        if (n==0L) return 0L;
        if (n.equals(BigInteger.ZERO)) return BigInteger.ZERO;
//        BigInteger ans= n*a + ((n*(n-1L)/2L)*(n+3L*x-2L))/3L;
        BigInteger x3 = three.multiply(x);
        BigInteger oth = n.add(x3).subtract(two);
        BigInteger other = n.multiply(n.subtract(BigInteger.ONE)).divide(two).multiply(oth).divide(three);
        BigInteger ans = n.multiply(a).add(other);
        return ans;
    }

    private BigInteger getRowSum(BigInteger row, BigInteger i, BigInteger j) {
        BigInteger rf = getNum(BigInteger.ONE, two, row);
        BigInteger a = getSum(rf, row, j);
        BigInteger b = getSum(rf, row, i.subtract(BigInteger.ONE));
        return a.subtract(b);
    }

    private BigInteger getColSum(BigInteger col, BigInteger i, BigInteger j) {
        BigInteger cf = getNum(BigInteger.ONE, BigInteger.ONE, col);
        BigInteger c1 = col.add(BigInteger.ONE);
        BigInteger a = getSum(cf, c1, j);
        BigInteger b = getSum(cf, c1, i.subtract(BigInteger.ONE));
        return a.subtract(b);
    }

    private BigInteger getCell(BigInteger r, BigInteger c) {
        BigInteger rf = getNum(BigInteger.ONE, two, r);
        return getNum(rf, r, c);
    }

    private BigInteger getNum(BigInteger a, BigInteger x, BigInteger n) {
        if (n.equals(BigInteger.ONE)) return a;
//        if (n == 1L) return a;
        BigInteger sec = x.multiply(n.subtract(BigInteger.ONE));
        BigInteger th = n.subtract(two).multiply(n.subtract(BigInteger.ONE)).divide(two);
//        return (a + x*(n-1L) + ((n-2L) * (n-1L))/2L);
        return a.add(sec).add(th);
    }
}
