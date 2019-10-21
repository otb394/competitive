package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class ArrayModification {
    private static final int[][] PATHS = new int[][]{{0},{3,2},{2,3},{1,1}};

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();long k=in.nextLong();
        long[] a = in.nextLongArray(n);
        long p=k/n;
        long q=k%n;
        long[] f=new long[n];
        for (int i = 0; i < n; i++) {
            f[i]=p+((i<q)?1:0);
        }
        long[] b=new long[n];
        for (int i = 0; i < n; i++) {
            int other=n-1-i;
            if (i==other) {
                b[i]=(f[i]>0)?0:a[i];
            } else if (i < other) {
                int rem=(int)(f[i]%3);
                switch (rem) {
                    case 0:b[i]=a[i];
                        break;
                    case 1:b[i]=a[i]^a[other];
                        break;
                    case 2:
                    default:b[i]=a[other];
                }
            } else {
                int rem=(int)(f[i]%3);
                switch (rem) {
                    case 0:b[i]=a[i];
                        break;
                    case 1:b[i]=a[other];
                        break;
                    case 2:
                    default:b[i]=a[i]^a[other];
                }
            }
        }
        out.println(b);
    }
}
