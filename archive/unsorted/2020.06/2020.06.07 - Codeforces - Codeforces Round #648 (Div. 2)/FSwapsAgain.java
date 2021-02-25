package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FSwapsAgain {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] b = in.nextIntArray(n);
        if (!isSame(a, b)) {
            out.println("No");
            return;
        }
        if ((n&1) != 0) {
            if (a[n/2] != b[n/2]) {
                out.println("No");
                return;
            }
        }
        List<Pair<Integer,Integer>> po = new ArrayList<>();
        for (int i = 0; i < n/2; i++) {
            int x = a[i];
            int y = a[n-1-i];
            if (x > y) {
                int temp = x;
                x = y;
                y = temp;
            }
            po.add(Pair.of(x, y));
        }
        List<Pair<Integer,Integer>> pt = new ArrayList<>();
        for (int i = 0; i < n/2; i++) {
            int x = b[i];
            int y = b[n-1-i];
            if (x > y) {
                int temp = x;
                x = y;
                y = temp;
            }
            pt.add(Pair.of(x, y));
        }
        Comparator<Pair<Integer,Integer>> comp = Comparator.comparingInt(Pair<Integer,Integer>::getLeft)
                .thenComparingInt(Pair::getRight);
        List<Pair<Integer,Integer>> tpo = po.stream().sorted(comp).collect(Collectors.toList());
        List<Pair<Integer,Integer>> tpt = pt.stream().sorted(comp).collect(Collectors.toList());
        if (tpo.equals(tpt)) {
            out.println("Yes");
        } else {
            out.println("No");
        }
    }

    private boolean isSame(int[] a, int[] b) {
        List<Integer> lo = Arrays.stream(a).sorted().boxed().collect(Collectors.toList());
        List<Integer> lt = Arrays.stream(b).sorted().boxed().collect(Collectors.toList());
        return lo.equals(lt);
    }
}
