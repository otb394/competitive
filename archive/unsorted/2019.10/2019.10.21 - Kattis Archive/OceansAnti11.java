package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MathUtility;

import static util.MathUtility.add;
import static util.MathUtility.mult;

public class OceansAnti11 {
    private static int MOD = 1000000007;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();
        int[] ans=new int[n+1];
        if (n>=1) {
            ans[1] = 2;
        }
        if (n>=2) {
            ans[2] = 3;
        }
        if (n>=3) {
            ans[3] = 5;
        }
        for (int i = 4; i <= n; i++) {
            int temp=ans[i-2];
            temp= mult(2,temp, MOD);
            ans[i]=add(ans[i-3], temp, MOD);
        }
        out.println(ans[n]);
    }
}
