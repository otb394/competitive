package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class LockedTreasure {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t=in.nextInt();
        List<BigInteger> fact = getFact(30);
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int r = m-1;
            int d = n-r;
            BigInteger ans = fact.get(n);
            ans = ans.divide(fact.get(r));
            ans = ans.divide(fact.get(d));
            out.println(ans);
        }
    }

    private List<BigInteger> getFact(int n) {
        List<BigInteger> ret = new ArrayList<>();
        ret.add(BigInteger.ONE);
        for (int i = 1; i <= n; i++) {
            BigInteger prev = ret.get(i-1);
            ret.add(prev.multiply(BigInteger.valueOf(i)));
        }
        return ret;
    }
}
