package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        String s = in.nextString();
        out.print(solve(n,s));
    }

    private String solve(int n, String s) {
        boolean[] deleted=new boolean[n];
        int i,j;
        i=0;j=0;
        int cnt = n;
        boolean vChance = true;
        while(cnt>11) {
            if (vChance) {
                while (i < n && s.charAt(i) == '8') {
                    i++;
                }
                if (i == n) {
                    return "YES";
                } else {
                    deleted[i] = true;
                    i++;
                }
                cnt--;
                vChance=false;
            } else {
                while(j<n && s.charAt(j)!='8') j++;
                if (j==n) {
                    return "NO";
                } else {
                    deleted[j]=true;
                    j++;
                }
                cnt--;
                vChance=true;
            }
        }
        for (int index=0;index<n;index++) {
            if (!deleted[index]) {
                if (s.charAt(index) == '8') {
                    return "YES";
                } else {
                    return "NO";
                }
            }
        }
        throw new RuntimeException("Exception state");
    }
}
