package code;

import io.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

public class SmallestKMP {
    MiscUtility ut = new MiscUtility();
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();
        String p = in.next();
        int[] sa = new int[26];
        int ns = s.length();
        for (int i = 0; i < ns; i++) {
            sa[s.charAt(i) - 'a']++;
        }
        int np = p.length();
        for (int i = 0; i < np; i++) {
            char ch = p.charAt(i);
            sa[ch - 'a']--;
        }
        StringBuilder builder = new StringBuilder();
        boolean done = false;
        for (int i = 0; i < 26; i++) {
            char ch = ((char)(i + 'a'));
            if (done || ch < p.charAt(0)) {
                int cnt = sa[ch - 'a'];
                for (int j = 0; j < cnt; j++) {
                    builder.append(ch);
                }
            } else {
                if (ch == p.charAt(0)) {
                    StringBuilder temp = new StringBuilder();
                    int cntt = sa[ch - 'a'];
                    for (int j = 0; j < cntt; j++) {
                        temp.append(ch);
                    }
                    String ts = temp.toString();
                    String first = p + ts;
                    String second = ts + p;
                    if (first.compareTo(second) < 0) {
                        builder.append(first);
                    } else {
                        builder.append(second);
                    }
                    done = true;
                } else {
                    builder.append(p);
                    done = true;
                    int cnt = sa[ch - 'a'];
                    for (int j = 0; j < cnt; j++) {
                        builder.append(ch);
                    }
                }
            }
        }
        if (!done) {
            builder.append(p);
        }
        String ans = builder.toString();
        MiscUtility.assertion(ans.length() == s.length(), String.format("ans[%s] is not same length s[%s]", ans, s));
        int[] other = new int[26];
        for (int i = 0; i < ns; i++) {
            other[ans.charAt(i) - 'a']++;
        }
        for (int i = 0; i < np; i++) {
            other[p.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            MiscUtility.assertion(other[i] == sa[i],
                    String.format("For i[%d] sa[i][%d] is different than other[i][%d]", i, sa[i], other[i]));
        }
        if (testNumber > 1) {
            out.println();
        }
        out.print(ans);
    }
}
