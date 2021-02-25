package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EvenMatrix {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        if ((n&1) == 0) {
            boolean rev = false;
            int cnt = 1;
            for (int i = 0; i < n; i++) {
                if (!rev) {
                    for (int j = 0; j < n; j++) {
                        out.print(cnt + " ");
                        cnt++;
                    }
                    out.println();
                } else {
                    List<Integer> lst = new ArrayList<>();
                    for (int j = 0; j < n; j++) {
                        lst.add(cnt);
                        cnt++;
                    }
                    Collections.reverse(lst);
                    for (int j = 0; j < n; j++) {
                        out.print(lst.get(j) + " ");
                    }
                    out.println();
                }
                rev = !rev;
            }
        } else {
            int cnt = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    out.print(cnt + " ");
                    cnt++;
                }
                out.println();
            }
        }
    }
}
