package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Workout {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] m = in.nextIntArray(n);
        int ans = solve(m,n,k);
        out.println(String.format("Case #%d: %d", testNumber, ans));
    }

    private int solve(int[] m, int n, int k) {
        List<Integer> values = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            values.add(m[i] - m[i-1]);
        }

        int minMax = binSearchFirstOne(1, Collections.max(values), tar -> getVal(values, tar, k));
        return minMax;
    }

    private int binSearchFirstOne(int start, int end, Function<Integer, Boolean> func) {
        int l = start;
        int h = end-1;

        while(l < h) {
            int mid = l + (h-l) / 2;
            if (func.apply(mid)) {
                h = mid;
            } else {
                l = mid+1;
            }
        }

        return l;
    }

    private boolean getVal(List<Integer> values, int tar, int k) {
        int cnt = values.stream().filter(val -> val > tar).map(val -> minDiv(val, tar)).mapToInt(i -> i).sum();
        return cnt <= k;
    }

    private int minDiv(int num, int tar) {
        int divs = num / tar;
        if (num%tar != 0) divs++;
        return divs-1;
    }
}
