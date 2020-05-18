package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class BConstructTheString {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();int a=in.nextInt();int b=in.nextInt();
        String s = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder str = new StringBuilder();
        int times = n/b;
        int rem = n%b;
        for (int i = 0; i < times; i++) {
            str.append(s,0,b);
        }
        for (int i = 0; i < rem; i++) {
            str.append(s.charAt(i));
        }
        out.println(str.toString());
    }
}
