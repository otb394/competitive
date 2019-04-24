package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        String s = in.nextString();
        boolean flag = false;
        for (int i = 1; i<n;i++) {
            if (s.charAt(i) < s.charAt(i-1)) {
                out.println("YES");
                out.print(i + " " + (i+1));
                flag=true;
                break;
            }
        }
        if (!flag) {
            out.print("NO");
        }
    }
}
