package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import util.MathUtility;
import util.MiscUtility;

public class DSegmentIntersections {
    MiscUtility ut = new MiscUtility();
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        long k = in.nextLong();
        long lo = in.nextLong();
        long ro = in.nextLong();ro--;
        long lt = in.nextLong();
        long rt = in.nextLong();rt--;
        Pair<Long, Long> po = Pair.of(lo, ro);
        Pair<Long, Long> pt = Pair.of(lt, rt);
        Pair<Long, Long> pr = getXY(po, pt);
        long x = pr.getLeft();
        long y = pr.getRight();
        long I = intersect(po, pt) * n;
        long rem = Math.max(k - I, 0L);
        ut.printDebug(x, "x", y, "y", I, "I", rem, "rem", po, "po", pt, "pt", n, "n", k, "k");
        if (rem == 0) {
            out.println(0);
            return;
        }
        if (x < y) {
            // Can consider growing more than one
            long ans = 0;
            ut.printDebug(rem, "rem", n*y, "n*y");
            if (rem <= (n * y)) {
                long q = MathUtility.ceil(rem, y);
                ut.printDebug(q, "q");
                ans += q * x;
                ans += (rem / y) * y;
                ans += rem % y;
                out.println(ans);
            } else {
                ans += n * (y + x);
                rem -= n * y;
                ut.printDebug(rem, "rem", ans, "ans");
                ans += rem * 2;
                out.println(ans);
            }
        } else {
            // Only grow one
            long ans = x;
            if (rem <= y) {
                ans += rem;
                out.println(ans);
            } else {
                ans += y;
                rem -= y;
                ans += rem * 2;
                out.println(ans);
            }
        }
    }

    private Pair<Long, Long> getXY(Pair<Long, Long> f, Pair<Long, Long> s) {
        if (f.getLeft() > s.getLeft()) {
            Pair<Long, Long> temp = f;
            f = s;
            s = temp;
        }
        long x;
        if (f.getRight() < s.getLeft()) {
             x = s.getLeft() - f.getRight() - 1;
        } else {
            x = 0L;
        }
        long y;
        if (f.getRight() < s.getLeft()) {
            y = s.getRight() - f.getLeft() + 1;
        } else {
            long mini = Math.max(getSize(f), getSize(s));
            long inter = intersect(f, s);
            ut.printDebug(mini, "mini", inter, "inter");
            y = mini - inter;
        }
        return Pair.of(x, y);
    }

    private long intersect(Pair<Long, Long> po, Pair<Long, Long> pt) {
        long lf = Math.max(po.getLeft(), pt.getLeft());
        long ri = Math.min(po.getRight(), pt.getRight());
        return Math.max(ri - lf + 1, 0);
    }

    private boolean in(Pair<Long, Long> po, Pair<Long, Long> pt) {
        return po.getLeft() >= pt.getLeft() && po.getRight() <= pt.getRight();
    }

    private long getSize(Pair<Long, Long> pr) {
        return pr.getRight() - pr.getLeft() + 1;
    }
}
