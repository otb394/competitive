package code;

import io.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.util.HashMap;
import java.util.Map;

public class Newspaper {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        ut.printDebug(testNumber, "testNumber");
//        InputReader in = new InputReader(System.in);
        int k = in.nextInt();
        ut.printDebug(k, "k");
        Map<Character, Long> mp = new HashMap<>();
        for (int i = 0; i < k; i++) {
            String sc = in.next();
            ut.printDebug(sc, "sc");
            MiscUtility.assertion(sc.length() == 1, String.format("sc [%s] is not of size 1", sc));
            char c = sc.charAt(0);
//            char c = in.nextCharacter();
            long val = in.nextLong();
            mp.put(c, val);
        }
        int m = in.nextInt();
        long ans = 0L;
        for (int i = 0; i < m; i++) {
            String line = in.readLine();
            int sz = line.length();
            for (int j = 0; j < sz; j++) {
                char c = line.charAt(j);
                ans += mp.getOrDefault(c, 0L);
            }
        }
        long dollars = ans / 100L;
        long cents = ans % 100L;
        out.printf("%d.%02d$\n", dollars, cents);
    }
}
