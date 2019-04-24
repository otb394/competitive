package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MathUtility;

public class CNekoDoesMaths {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a=in.nextInt();
        int b=in.nextInt();
        if (a<=b) {
            out.print(solve(a, b));
        } else {
            out.print(solve(b,a));
        }
    }

    private int solve(int a,int b) {
        int d=b-a;
        int[] factors = MathUtility.factorize(d);
//        System.out.println("Factors");
//        for(int i=0;i<factors.length;i++) {
//            System.out.print(factors[i] + " ");
//        }
//        System.out.println();
        long minLCM = MathUtility.lcm(a,b);
        int ans = 0;
        for (int factor : factors) {
            int nextMult = nextMultiple(a, factor);
            int newDiff=nextMult-a;
            long newLCM = MathUtility.lcm(nextMult, b+newDiff);
            if (newLCM<minLCM) {
                minLCM=newLCM;
                ans=newDiff;
            }
        }
        return ans;
    }

    private int nextMultiple(int a, int m) {
        double q = ((double)a)/((double)m);
        int rq=(int) Math.ceil(q);
        return m*rq;
    }
}
