package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class CD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (true) {
            int n = in.nextInt();
            int m = in.nextInt();
            Set<Integer> ns = new HashSet<>();
            Set<Integer> ms = new HashSet<>();
            if (n == 0 && m == 0) {
                break;
            }
            for (int i = 0; i < n; i++) {
                ns.add(in.nextInt());
            }
            for (int i = 0; i < m; i++) {
                ms.add(in.nextInt());
            }
            ns.retainAll(ms);
            out.println(ns.size());
        }
    }
}
