package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class HardSequence {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] ans=new int[129];
        ans[1]=0;
        for (int i = 2; i <= 128; i++) {
            int temp = -1;
            int val = ans[i-1];
            for (int j = i-2; j >= 1; j--) {
                if (ans[j] == val) {
                    temp = j;
                    break;
                }
            }
            if (temp==-1) {
                ans[i]=0;
            } else {
                ans[i]=i-1-temp;
            }
        }
        int t=in.nextInt();
        for (int i = 0; i < t; i++) {
            int n=in.nextInt();
            out.println(getAns(ans,n));
        }
    }

    private int getAns(int[] ans, int n) {
        int val=ans[n];
        int ret = 0;
        for (int i = 1; i <= n; i++) {
            if (ans[i] == val) {
                ret++;
            }
        }
        return ret;
    }
}
