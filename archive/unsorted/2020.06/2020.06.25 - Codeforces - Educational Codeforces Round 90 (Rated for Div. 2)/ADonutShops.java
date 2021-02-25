package code;



import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Fraction;

public class ADonutShops {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        Fraction fa = new Fraction(a, 1);
        Fraction fb = new Fraction(c, b);
        int comp = fa.compareTo(fb);
        if (comp < 0) {
            out.println("1 -1");
        } else if (comp == 0) {
            if (b == 1) {
                out.println("-1 -1");
            } else {
                out.println("1 -1");
            }
        } else {
            if (a > c) {
                out.println("-1 1");
            } else if (a == c) {
                out.println("-1 " + b);
            } else {
                out.println("1 " + b);
            }
        }
    }
}
