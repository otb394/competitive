package datastructure;

import util.MathUtility;

public class Fraction implements Comparable<Fraction> {
    public long num;
    public long denom;

    public Fraction(long num, long denom) {
        if (denom == 0) {
            throw new RuntimeException("Denominator cannot be 0");
        }
        this.num = num;
        this.denom = denom;
    }

    public Fraction add(Fraction other) {
        long lcm = MathUtility.lcm(denom, other.denom);
        long mul = lcm / denom;
        long omul = lcm / other.denom;
        long nu = num * mul + other.num * omul;
        return new Fraction(nu, lcm);
    }

    public Fraction sub(Fraction other) {
        return add(new Fraction(-other.num, other.denom));
    }

    public Fraction reduce() {
        long hcf = MathUtility.hcf(num, denom);
        return new Fraction(num / hcf, denom / hcf);
    }

    public Fraction abs() {
        return new Fraction(Math.abs(num), Math.abs(denom));
    }

    @Override
    public int compareTo(Fraction o) {
        return Long.compare(this.num * o.denom, this.denom * o.num);
    }
}
