package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import code.datastructure.Pair;
import util.MathUtility;

import java.util.Optional;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();int m=in.nextInt();
        long[] x=in.nextLongArray(n);
        long[] p=in.nextLongArray(m);
        Optional<Pair<Long,Integer>> ans = solve(n,m,x,p);
        if (!ans.isPresent()) {
            out.print("NO");
        }
        ans.ifPresent(an -> {
            out.println("YES");
            out.print(an.getLeft());out.print(" ");out.print(an.getRight()+1);
        });
    }

    private Optional<Pair<Long, Integer>> solve(int n, int m,long[] x,long[] p) {
        long[] dis = new long[n-1];
        for (int i=1;i<n;i++) dis[i-1]=x[i]-x[0];
        long hcf= MathUtility.hcf(dis, n-1);
        for(int i=0;i<m;i++) {
            if (hcf%p[i] == 0L) {
                return Optional.of(Pair.of(x[0], i));
            }
        }
        return Optional.empty();
    }
}
