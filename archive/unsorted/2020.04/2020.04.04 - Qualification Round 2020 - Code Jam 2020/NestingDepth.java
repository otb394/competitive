package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class NestingDepth {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s=in.nextString();
        String ans=solve(s);
        out.println(String.format("Case #%d: %s", testNumber, ans));
    }

    private String solve(String s) {
        int n=s.length();
        int[] aux=new int[n];
        aux[0]=(s.charAt(0)-'0');
        for (int i = 1; i < n; i++) {
            int firstDig = s.charAt(i)-'0';
            int secDig = s.charAt(i-1)-'0';
            aux[i]=firstDig-secDig;
        }
        StringBuilder ans= new StringBuilder();
        for (int i = 0; i < n; i++) {
            int rc = aux[i];
            if (rc > 0) {
                for (int j = 0; j < rc; j++) {
                    ans.append('(');
                }
            } else if (rc < 0) {
                rc = Math.abs(rc);
                for (int j = 0; j < rc; j++) {
                    ans.append(')');
                }
            }
            ans.append(s.charAt(i));
        }
        int last = s.charAt(n-1)-'0';
        for (int i = 0; i < last; i++) {
            ans.append(')');
        }
        return ans.toString();
    }
}
