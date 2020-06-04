package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class AArray {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        List<Integer> neg = new ArrayList<>();
        List<Integer> pos = new ArrayList<>();
        int zer = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                zer++;
            } else if (a[i] < 0) {
                neg.add(a[i]);
            } else {
                pos.add(a[i]);
            }
        }
        int cntneg = neg.size();
        int cntpos = pos.size();
        List<Integer> f = new ArrayList<>();
        List<Integer> s = new ArrayList<>();
        List<Integer> t = new ArrayList<>();
        if (pos.isEmpty()) {
            f.add(neg.get(0));
            s.add(neg.get(1));
            s.add(neg.get(2));
            for (int i = 3; i < cntneg; i++) {
                t.add(neg.get(i));
            }
            for (int i = 0; i < zer; i++) {
                t.add(0);
            }
        } else {
            f.add(neg.get(0));
            s.add(pos.get(0));
            for (int i = 1; i < cntpos; i++) {
                t.add(pos.get(i));
            }
            for (int i = 1; i < cntneg; i++) {
                t.add(neg.get(i));
            }
            for (int i = 0; i < zer; i++) {
                t.add(0);
            }
        }
        int nf = f.size();
        out.print(nf);
        for (int i = 0; i < nf; i++) {
            out.print(" " + f.get(i));
        }
        out.println();
        int ns = s.size();
        out.print(ns);
        for (int i = 0; i < ns; i++) {
            out.print(" " + s.get(i));
        }
        out.println();
        int nt = t.size();
        out.print(nt);
        for (int i = 0; i < nt; i++) {
            out.print(" " + t.get(i));
        }
        out.println();
    }
}
