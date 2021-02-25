package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CEhabAndPrefixMEXs {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int i = 0;
        Deque<Integer> open = new ArrayDeque<>();
//        List<Integer> open = new ArrayList<>();
        int[] b = new int[n];
        while (i < n && a[i] == 0) {
            open.add(i);
            i++;
        }
        while (i < n) {
            int j = i+1;
            while (j < n && a[j] == a[i]) j++;
            if (i == 0) {
                b[i] = 0;
            } else {
                b[i] = a[i-1];
            }
            int x = b[i];
            int y = a[i];
            if (open.size() < (y-x-1)) {
                out.println(-1);
                return;
            }
            for (int k = x+1; k < y; k++) {
                int pos = open.removeFirst();
                b[pos] = k;
            }
            for (int k = i+1; k < j; k++) {
                open.add(k);
            }
            i = j;
        }
        int inf = a[n-1] + 1;
        for (int val : open) {
            b[val] = inf;
        }
        for (int j = 0; j < n; j++) {
            out.print(b[j] + " ");
        }
        out.println();
    }
}
