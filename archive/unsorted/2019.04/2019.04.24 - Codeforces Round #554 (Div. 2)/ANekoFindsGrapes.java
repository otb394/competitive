package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class ANekoFindsGrapes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();
        int m=in.nextInt();
        int[] a=in.nextIntArray(n);
        int[] b=in.nextIntArray(m);
        out.print(solve(n,m,a,b));
    }

    private int solve(int n,int m,int[] a,int[] b) {
        int cecount = count(a);
        int cocount = n-cecount;
        int kecount = count(b);
        int kocount = m-kecount;
        return Math.min(cecount, kocount) + Math.min(cocount, kecount);
    }

    private int count(int[] arr) {
        int sum=0;
        for(int i: arr) {
            if ((i&1) == 0) {
                sum++;
            }
        }
        return sum;
    }
}
