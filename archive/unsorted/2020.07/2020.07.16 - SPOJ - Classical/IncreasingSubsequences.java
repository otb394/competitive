package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import util.MathUtility;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class IncreasingSubsequences {
    private static final int MOD = 5000000;
    int[] ans;
    int[] tans;
    MiscUtility ut = new MiscUtility();
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] aa = in.nextIntArray(n);
        List<Integer> ca = Arrays.stream(aa).boxed().collect(Collectors.toList());
        Collections.reverse(ca);
        int[] a = ca.stream().mapToInt(y -> y).toArray();
        ans = new int[n];
        tans = new int[n];
        Arrays.fill(ans, 1);
        List<Pair<Integer, Integer>> b = new ArrayList<>();
        ut.printDebug(of(Arrays.stream(a).boxed().collect(Collectors.toList())), "a", n, "n", k, "k");
        for (int i = 2; i <= k; i++) {
            b.clear();
            for (int j = 0; j < n; j++) {
                b.add(Pair.of(a[j], j));
            }
            ut.printDebug(b, "b", i, "i");
            mergeSort(b, 0, n-1);
            System.arraycopy(tans, 0, ans, 0, n);
            ut.printDebug(of(Arrays.stream(ans).boxed().collect(Collectors.toList())), of("ans"));
            Arrays.fill(tans, 0);
        }
        int an = 0;
        for (int i = 0; i < n; i++) {
            an = MathUtility.add(an, ans[i], MOD);
        }
        out.println(an);
    }

    private void mergeSort(List<Pair<Integer, Integer>> a, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(a, start, mid);
        mergeSort(a, mid + 1, end);
        int n = mid - start + 1;
        int[] cum = new int[n];
        cum[0] = ans[a.get(start).getRight()];
        for (int i = 1; i < n; i++) {
            cum[i] = MathUtility.add(cum[i-1], ans[a.get(i + start).getRight()], MOD);
        }
        merge(a, start, mid, end, cum);
    }

    private void merge(List<Pair<Integer, Integer>> a, int start, int mid, int end, int[] cum) {
        int n = end - start + 1;
        if (n == 0) return;
        List<Pair<Integer, Integer>> temp = new ArrayList<>();
        int i = start;
        int j = mid + 1;
        while (i <= mid && j <= end) {
            int aj = a.get(j).getLeft();
            int ai = a.get(i).getLeft();
            if (aj < ai) {
                int val = getSum(i-start, mid-start, cum);
                tans[a.get(j).getRight()] = MathUtility.add(tans[a.get(j).getRight()], val, MOD);
                temp.add(a.get(j));j++;
            } else {
                temp.add(a.get(i));i++;
            }
        }
        while (i <= mid) {
            temp.add(a.get(i));i++;
        }
        while (j <= end) {
            temp.add(a.get(j));j++;
        }
        for (int l = 0; l < n; l++) {
            a.set(l + start, temp.get(l));
        }
    }

    private static <T> Supplier<T> of(T ob) {
        return () -> ob;
    }

    private int getSum(int i, int j, int[] cum) {
        if (i == 0) return cum[j];
        return MathUtility.sub(cum[j], cum[i-1], MOD);
    }
}
