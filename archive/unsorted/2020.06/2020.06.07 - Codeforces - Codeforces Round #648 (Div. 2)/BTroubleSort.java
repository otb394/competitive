package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BTroubleSort {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] b = in.nextIntArray(n);
        List<Integer> ao = new ArrayList<>();
        List<Integer> at = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (b[i] == 0) {
                ao.add(a[i]);
            } else {
                at.add(a[i]);
            }
        }
        boolean ok = false;
        if (ao.size() > 0 && at.size() > 0) ok = true;
        else {
            ok = isSorted(Arrays.stream(a).boxed().collect(Collectors.toList()));
        }
        out.println(ok ? "Yes" : "No");
    }

    private boolean isSorted(List<Integer> list) {
        List<Integer> another = list.stream().sorted().collect(Collectors.toList());
        return another.equals(list);
    }
}
