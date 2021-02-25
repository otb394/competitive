package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class AOmkarAndCompletion {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        List<Integer> ans = new ArrayList<>();
        int curr = 1;
        int i = 0;
        while (i < n) {
            ans.add(curr);
            i++;
            if (i < n) {
                ans.add(curr);
                i++;
                curr+=2;
            }
        }
        for (int val : ans) {
            out.print(val + " " );
        }
        out.println();
    }
}
