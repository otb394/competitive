package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EasyProblemFromRujiaLiu {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (!in.isExhausted()) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] a = in.nextIntArray(n);
            List<List<Integer>> arr = new ArrayList<>();
            int mx = Arrays.stream(a).max().orElseThrow(RuntimeException::new);
            for (int i = 0; i <= mx; i++) {
                arr.add(new ArrayList<>());
            }
            for (int i = 0; i < n; i++) {
                arr.get(a[i]).add(i);
            }
            for (int i = 0; i < m; i++) {
                int k = in.nextInt();
                int v = in.nextInt();
                if (v > mx) {
                    out.println(0);
                } else {
                    List<Integer> positions = arr.get(v);
                    if (positions.size() < k) {
                        out.println(0);
                    } else {
                        int pos = positions.get(k-1);
                        out.println(pos + 1);
                    }
                }
            }
        }
    }
}
