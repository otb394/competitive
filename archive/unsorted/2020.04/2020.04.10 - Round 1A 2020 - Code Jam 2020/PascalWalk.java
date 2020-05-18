package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PascalWalk {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();
        List<Pair<Integer,Integer>> ans = solve(n);
        out.println(String.format("Case #%d:", testNumber));
        ans.forEach(pr -> out.println((pr.getLeft()+1) + " " + (pr.getRight()+1)));
    }

    private List<Pair<Integer,Integer>> solve(int n) {
        if (n > 30) {
            Pair<List<Pair<Integer,Integer>>,Integer> some = getPath(n-30);
            List<Pair<Integer,Integer>> path = some.getLeft();
            int np = path.size();
            int sumTillNow = some.getRight();
            List<Pair<Integer,Integer>> remPath = rem(n-sumTillNow, path.get(np-1));
            return Stream.concat(path.stream(), remPath.stream()).collect(Collectors.toList());
        } else {
            List<Pair<Integer,Integer>> ret = new ArrayList<>();
            Pair<Integer,Integer> curr = Pair.of(0,0);
            ret.add(curr);
            ret.addAll(rem(n-1, curr));
            return ret;
        }
    }

    private Pair<List<Pair<Integer,Integer>>,Integer> getPath(int n) {
        //System.out.println("Entering getPath");
        //System.out.println("n = " + n);
        int sum = 1;
        Pair<Integer,Integer> curr = Pair.of(0,0);
        List<Pair<Integer,Integer>> ret = new ArrayList<>();
        ret.add(curr);
        int t = 1;
        int r = 0;
        while(t<=n) {
            //System.out.println("r = " + r);
            //System.out.println("t = " + t);
            //System.out.println("sum = " + sum);
            //System.out.println("curr = " + curr);
            if ((t&n) != 0) {
                int col = curr.getRight();
                //System.out.println("col = " + col);
                if (col == 0) {
                    for (int i = 1; i <= r; i++) {
                        ret.add(Pair.of(r,i));
                    }
                    sum+=(1<<r)-1;
                    curr = Pair.of(r,r);
                } else if (col == r) {
                    for (int i = r-1; i >= 0; i--) {
                        ret.add(Pair.of(r,i));
                    }
                    sum+=(1<<r)-1;
                    curr = Pair.of(r,0);
                } else {
                    throw new RuntimeException(String.format("Unexpected state (%d, %d)", curr.getLeft(), col));
                }
            }
            t<<=1;
            r++;
            curr = getNext(curr);
            ret.add(curr);
            sum++;
        }
        return Pair.of(ret,sum);
    }

    private Pair<Integer,Integer> getNext(Pair<Integer,Integer> curr) {
        int r = curr.getLeft();
        int c = curr.getRight();
        if (c == 0) {
            return Pair.of(r+1, 0);
        } else if (r ==c) {
            return Pair.of(r+1,c+1);
        } else {
            throw new RuntimeException(String.format("getNext called on inapt coord (%d,%d)", r,c));
        }
    }

    private List<Pair<Integer,Integer>> rem(int n, Pair<Integer,Integer> curr) {
        if (n == 0) {
            return new ArrayList<>();
        } else {
            List<Pair<Integer,Integer>> ans = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                curr = getNext(curr);
                ans.add(curr);
            }
            return ans;
        }
    }
}
