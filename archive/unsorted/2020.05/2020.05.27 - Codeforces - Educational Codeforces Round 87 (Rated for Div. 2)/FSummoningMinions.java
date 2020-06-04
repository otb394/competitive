package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FSummoningMinions {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            b[i] = in.nextInt();
        }
        int[][] dp = new int[n][k+1];
        int[] indexes = new int[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }
        indexes = Arrays.stream(indexes).boxed().sorted(Comparator.comparingInt(ind -> b[ind])).mapToInt(i -> i)
                .toArray();
        boolean[][] select = new boolean[n][k+1];
        dp[0][0] = (k-1)*b[indexes[0]];
        dp[0][1] = a[indexes[0]];
        select[0][1] = true;
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] + (k-1)*(b[indexes[i]]);
        }
        for (int i = 1; i < n; i++) {
            int maxJ = Math.min(i+1, k);
            for (int j = 1; j <= maxJ; j++) {
                int first = dp[i-1][j-1] + a[indexes[i]] + (j-1)*b[indexes[i]];
                int second = (j <= i) ? dp[i-1][j] + (k-1)*b[indexes[i]] : (-1);
                if (first >= second) {
                    dp[i][j] = first;
                    select[i][j] = true;
                } else {
                    dp[i][j] = second;
                }
            }
        }
        List<Integer> selected = new ArrayList<>();
        boolean[] selectedFlag = new boolean[n];
        int tbs = k;
        for (int i = n-1; i >= 0; i--) {
            if (tbs == 0) break;
            if (select[i][tbs]) {
                selected.add(indexes[i]);
                selectedFlag[indexes[i]] = true;
                tbs--;
            }
        }
        List<Integer> listA = Arrays.stream(a).boxed().collect(Collectors.toList());
        List<Integer> listB = Arrays.stream(b).boxed().collect(Collectors.toList());
        String message =
                String.format("Selected size mismatch. size = %d, k = %d, n = %d, a = %s, b = %s", selected.size(),
                        k, n, listA, listB);
        MiscUtility.assertion(selected.size() == k, message);
        Collections.reverse(selected);
        int m = k + (n-k)*2;
        out.println(m);
        for (int i = 0; i < (k-1); i++) {
            out.print((selected.get(i)+1) + " " );
        }
        for (int i = 0; i < n; i++) {
            if (!selectedFlag[i]) {
                out.print((i+1) + " ");
                out.print(-(i+1) + " ");
            }
        }
        out.print(selected.get(k-1) + 1);
        out.println();
    }
}
