package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class ChefAndString {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.nextString();
        int n = s.length();
        int ans= 0;
        int i =0;
        while (i < (n-1)) {
            if (s.charAt(i) != s.charAt(i+1)) {
                ans++;
                i+=2;
            } else {
                i++;
            }
        }
        out.println(ans);
    }
}
