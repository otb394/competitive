package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import code.datastructure.Pair;

import java.util.ArrayList;
import java.util.List;

public class BNekoPerformsCatFurrierTransform {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x=in.nextInt();
        Pair<List<Integer>, Integer> pr = solve(x);
        int[] ans=pr.getLeft().stream().mapToInt(i -> i).toArray();
        out.println(pr.getRight());
        out.print(ans);
    }

    private Pair<List<Integer>, Integer> solve(int x) {
        List<Integer> list = new ArrayList<>();
        boolean start=true;
        int t=0;
        while(!isLongCat(x)) {
            if (start) {
                int zcnt = Integer.numberOfTrailingZeros(x);
                list.add(zcnt);
                x=(x^((1<<zcnt)-1));
                start=false;
                t++;
            } else {
                x+=1;
                start=true;
                t++;
            }
        }
        return Pair.of(list,t);
    }

    private boolean isLongCat(int x) {
        return isPowerOfTwo(x+1);
    }

    private boolean isPowerOfTwo(int x) {
        return x == (Integer.highestOneBit(x));
    }
}
