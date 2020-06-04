package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class ANearlyLuckyNumber {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String n = in.nextString();
        int count = 0;
        int len = n.length();
        for (int i = 0; i < len; i++) {
            if (n.charAt(i) == '4' || n.charAt(i) == '7')
                count++;
        }
        String cs = Integer.toString(count);
        int le = cs.length();
        boolean flag = true;
        for (int i = 0; i < le; i++) {
            if (cs.charAt(i) != '4' && cs.charAt(i) != '7') {
                flag = false;
                break;
            }
        }
        String ans = (flag) ? ("YES") : "NO";
        out.print(ans);
    }
}
