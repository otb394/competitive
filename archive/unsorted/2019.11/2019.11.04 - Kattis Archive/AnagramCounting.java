package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramCounting {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        try {
            List<BigInteger> fact = getFact(110);
            while(true) {
                String s = in.nextString();
                int n = s.length();
                Map<Character, Integer> mp = new HashMap<>();
                for (int i = 0; i < n; i++) {
                    char c = s.charAt(i);
                    int val = mp.getOrDefault(c, 0);
                    mp.put(c, val+1);
                }
                BigInteger ans = fact.get(n);
                for(Map.Entry<Character, Integer> entry: mp.entrySet()) {
                    int val = entry.getValue();
                    ans = ans.divide(fact.get(val));
                }
                out.println(ans);
            }
        } catch (Exception e) {}
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
