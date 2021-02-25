package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class BGCDCompression {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int N = 2*n;
        int[] a = in.nextIntArray(N);
        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if ((a[i]&1) == 0) {
                even.add(i+1);
            } else {
                odd.add(i+1);
            }
        }
        if (odd.size() % 2 == 0) {
            if (odd.size() == 0) {
                for (int i = 2; i < N; i+=2) {
                    out.println(even.get(i) + " " + even.get(i+1));
                }
            } else {
                int ev = even.size();
                for (int i = 0; i < ev; i+=2) {
                    out.println(even.get(i) + " " + even.get(i+1));
                }
                int od = odd.size();
                for (int i = 2; i < od; i+=2) {
                    out.println(odd.get(i) + " " + odd.get(i+1));
                }
            }
        } else {
            int ev = even.size();
            for (int i = 1; i < ev; i+=2) {
                out.println(even.get(i) + " " + even.get(i+1));
            }
            int od = odd.size();
            for (int i = 1; i < od; i+=2) {
                out.println(odd.get(i) + " " + odd.get(i+1));
            }
        }
    }
}
