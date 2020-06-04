package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import algorithms.BinarySearch;

import java.util.function.Function;

public class AggressiveCows {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int c= in.nextInt();
        int[] x = in.nextSortedIntArray(n);
        Function<Integer, Boolean> fn = some -> !calc(x, n, c, some);
        int val = BinarySearch.searchLastZero(0, 1000000001, fn);
        out.println(val);
    }

    private boolean calc(int[] x, int n, int c, int dis) {
        int i = 0;
        int ass=0;
        while(ass < c && i < n) {
            ass++;
            int j = i+1;
            while(j < n && ((x[j]-x[i]) < dis)) j++;
            i = j;
        }
        return (ass == c);
    }
}
