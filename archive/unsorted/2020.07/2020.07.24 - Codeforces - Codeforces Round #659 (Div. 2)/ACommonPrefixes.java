package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.List;

public class ACommonPrefixes {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        List<String> ans = new ArrayList<>();
        if (n == 1) {
            StringBuilder bu = new StringBuilder();
            if (a[0] > 0) {
                for (int i = 0; i < a[0]; i++) {
                    bu.append('a');
                }
                String temp = bu.toString();
                out.println(temp);
                out.println(temp);
                return;
            } else {
                out.println('a');
                out.println('b');
                return;
            }
        }
        StringBuilder builder = new StringBuilder();
        if (a[0] > 0) {
            for (int i = 0; i < a[0]; i++) {
                builder.append('a');
            }
        } else {
            builder.append('b');
        }
        String prev = builder.toString();
        ans.add(prev);
        for (int i = 0; i < (n-1); i++) {
            int ts = Math.max(1, Math.max(a[i], a[i+1]));
            int pn = prev.length();
            StringBuilder b = new StringBuilder();
            for (int j = 0; j < a[i]; j++) {
                b.append(prev.charAt(j));
            }
            int rem = ts - a[i];
            if (rem > 0) {
                if (pn > a[i]) {
                    b.append(next(prev.charAt(a[i])));
                    rem--;
                    for (int j = 0; j < rem; j++) {
                        b.append('a');
                    }
                } else {
                    for (int j = 0; j < rem; j++) {
                        b.append('a');
                    }
                }
            }
            String sb = b.toString();
            ans.add(sb);
            prev = sb;
        }
        //Add last
        StringBuilder tb = new StringBuilder();
        if (a[n-1] > 0) {
            for (int i = 0; i < a[n - 1]; i++) {
                tb.append(prev.charAt(i));
            }
        } else {
            tb.append(next(prev.charAt(0)));
        }
        ans.add(tb.toString());

        ut.printDebug(testNumber, "testNumber", ans, "ans");
        for (String val : ans) {
            out.println(val);
        }
    }

    private char next(char ch) {
        if (ch == 'z') return 'a';
        return ((char)(ch + 1));
    }
}
