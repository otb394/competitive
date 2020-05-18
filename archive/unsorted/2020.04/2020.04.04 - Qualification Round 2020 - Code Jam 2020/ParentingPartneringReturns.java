package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import datastructure.Pair;

public class ParentingPartneringReturns {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        List<Pair<Integer,Integer>> times = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            times.add(Pair.of(x,y));
        }
        String ans = solve(n, times);
        out.println(String.format("Case #%d: %s", testNumber, ans));
    }

    private String solve(int n, List<Pair<Integer,Integer>> times) {
        List<Pair<Pair<Integer,Integer>, Integer>> markedTimes =
                IntStream.range(0,n).boxed().map(i -> Pair.of(times.get(i), i)).collect(Collectors.toList());
        Comparator<Pair<Pair<Integer,Integer>, Integer>> comp = Comparator.comparing(pr -> pr.getLeft().getLeft());
        comp = comp.thenComparing(pr -> pr.getLeft().getRight());
        markedTimes.sort(comp);

        int cBusyTill = 0;
        int jBusyTill = 0;
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int start = markedTimes.get(i).getLeft().getLeft();
            if (cBusyTill <= start) {
                ans.append('C');
                cBusyTill = markedTimes.get(i).getLeft().getRight();
            } else if (jBusyTill <= start) {
                ans.append('J');
                jBusyTill = markedTimes.get(i).getLeft().getRight();
            } else {
                return "IMPOSSIBLE";
            }
        }
        String ansString = ans.toString();
        char[] actAns = new char[n];
        for (int i = 0; i < n; i++) {
            int index = markedTimes.get(i).getRight();
            actAns[index] = ansString.charAt(i);
        }
        return new String(actAns);
    }
}
