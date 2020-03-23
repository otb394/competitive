package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;

public class NinePacks {
    private static final int INF = 100000;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int H=in.nextInt();
        int[] h=in.nextIntArray(H);
        int B=in.nextInt();
        int[] b=in.nextIntArray(B);
        int sh= Arrays.stream(h).sum();
        int sb=Arrays.stream(b).sum();
        int[] dph=getDP(h,H,sh);
        int[] dpb=getDP(b,B,sb);
        int min = Math.min(sh,sb);
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= min; i++) {
            if (dph[i]!=-1 && dpb[i]!=-1) {
                ans = Math.min(ans, dph[i]+dpb[i]);
            }
        }
        if (ans == Integer.MAX_VALUE) {
            out.print("impossible");
        } else {
            out.print(ans);
        }
    }

    private int[] getDP(int[] arr, int n, int sum) {
        int[] prev = new int[sum+1];
        int[] next = new int[sum+1];
        for (int i = 0; i <= sum; i++) {
            if (i==0) prev[i]=0;
            else {
                prev[i] = (arr[0] == i) ? 1 : -1;
            }
        }
        for (int i = 1; i < n; i++) {
            next[0]=0;
            for (int j = 1; j <= sum; j++) {
                next[j]=prev[j];
                if (j>=arr[i] && prev[j-arr[i]]!=-1) {
                    if (next[j]==-1) {
                        next[j]=prev[j-arr[i]]+1;
                    } else {
                        next[j]=Math.min(prev[j], prev[j-arr[i]]+1);
                    }
                }
            }

            for (int j = 0; j <= sum; j++) {
                prev[j]=next[j];
            }
        }
        return prev;
    }
}
