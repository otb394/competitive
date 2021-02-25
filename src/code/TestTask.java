package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestTask {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int tt = 10;
        out.println(tt);
        for (int ii = 0; ii < tt; ii++) {
            int n = 5;
            Random random = new Random();
            List<Integer> arr = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                arr.add(random.nextInt(21));
            }
            List<Integer> totl = new ArrayList<>();
            totl.addAll(arr);
            totl.addAll(arr);
            Collections.shuffle(totl);
            int[] a = new int[n];
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = totl.get(i);
            }
            for (int i = 0; i < n; i++) {
                b[i] = totl.get(i+n);
            }
            out.println(n);
            out.print(a);
            out.println();
            out.print(b);
            out.println();
        }
    }
}
