package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;

public class BGrowTheTree {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();long[] a=in.nextLongArray(n);
        Arrays.sort(a);
        long x=0L;long y=0L;
        int j=n-1;int i=0;
        while(i<j) {
            x+=a[j];y+=a[i];i++;j--;
        }
        if(i==j)x+=a[i];
        long ans=x*x+y*y;
        out.print(ans);
    }
}
