package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import algorithms.BinarySearch;
import util.MiscUtility;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeetCode410 {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int m = in.nextInt();
        out.println(splitArray(a, m));
    }

    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        long sum = Arrays.stream(nums).boxed().mapToLong(i -> i).sum();
        Function<Long, Boolean> func = val -> check(nums, m, n, val);
        return (int) BinarySearch.searchFirstOne(-1, sum, func);
    }

    private boolean check(int[] a, int m, int n, long val) {
        int i = 0;
        int par = 0;
        ut.printDebug(() -> m, () -> "m", () -> n, () -> "m", () -> val, () -> "val",
                () -> Arrays.stream(a).boxed().collect(Collectors.toList()), () -> "a");
        while (i < n) {
            long sum = a[i];
            if (sum > val) return false;
            int j = i + 1;
            int last = Math.min(n-m+par, n-1);
            ut.printDebug(i, "i", last, "last");
            while (j <= last && ((a[j] + sum) <= val)) {
                sum += a[j];
                j++;
            }
            par++;
            i = j;
        }
        return par <= m;
    }
}
