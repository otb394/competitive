package code;



import FastIO.InputReader;
import FastIO.OutputWriter;
import algorithms.BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DGuessTheMaximums {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int maxNodVal;
        List<List<Integer>> subs = new ArrayList<>();
        List<Integer> otherList = new ArrayList<>();
        boolean[] others = new boolean[n + 1];
        for (int i = 0; i < k; i++) {
            int c = in.nextInt();
            int[] tArr = in.nextIntArray(c);
            for (int val : tArr) {
                others[val] = true;
            }
            subs.add(Arrays.stream(tArr).boxed().collect(Collectors.toList()));
        }
        for (int i = 1; i <= n; i++) {
            if (!others[i]) {
                otherList.add(i);
            }
        }

        maxNodVal = query(subs.stream().flatMap(List::stream).collect(Collectors.toList()), in, out);
        int mxInd;
        Function<Integer, Boolean> func =
                ind -> (query(subs.subList(0, ind + 1).stream().flatMap(List::stream)
                        .collect(Collectors.toList()), in, out) == maxNodVal);
        if (k > 1) {
            mxInd = BinarySearch.searchFirstOne(-1, k-1, func);
        } else {
            mxInd = 0;
        }
        if (mxInd == k) {
            throw new RuntimeException("Unexpected state. max val node is not found in array");
        }
        int[] ans = new int[k];

        if (k != 1) {
            List<Integer> allOtherNodes = IntStream.range(0, k).filter(y -> (y != mxInd)).boxed().map(subs::get)
                    .flatMap(List::stream).collect(Collectors.toList());
            List<Integer> nq = Stream.concat(allOtherNodes.stream(), otherList.stream())
                    .collect(Collectors.toList());
            int otherAns = query(nq, in, out);
            if (otherAns > maxNodVal) {
                for (int i = 0; i < k; i++) {
                    ans[i] = otherAns;
                }
            } else {
                for (int i = 0; i < k; i++) {
                    if (i != mxInd) {
                        ans[i] = maxNodVal;
                    }
                }
                ans[mxInd] = otherAns;
            }
        } else {
            int otherAns = query(otherList, in, out);
            ans[0] = otherAns;
        }
        output(ans, in, out);
    }

    private int query(List<Integer> sub, InputReader in, OutputWriter out) {
        Set<Integer> st = new HashSet<>(sub);
        int c = sub.size();
        out.print("? " + c);
        for (int val : sub) {
            out.print(" " + val);
        }
        out.println();
        out.println();
        out.flush();

        int x = in.nextInt();
        if (x == -1) {
            throw new RuntimeException("x == -1 received");
        }

        return x;
    }

    private void output(int[] password, InputReader in, OutputWriter out) {
        out.print("!");
        for (int val : password) {
            out.print(" " + val);
        }
        out.println();
        out.flush();

        String outcome = in.nextString();
        if (outcome.equals("Incorrect")) {
            throw new RuntimeException("Incorrect assertion");
        }
    }
}
