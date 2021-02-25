package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;
import java.util.stream.IntStream;

public class COmkarAndBaseball {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        for (int i = 0; i < n; i++) {
            a[i]--;
        }
        if (inZero(a, n)) {
            out.println(0);
        } else if (inOne(a, n)) {
            out.println(1);
        } else {
            out.println(2);
        }
    }

    private boolean inZero(int[] a, int n) {
        for (int i = 0; i < n; i++) {
            if (a[i] != i) return false;
        }
        return true;
    }

    private boolean inOne(int[] a, int n) {
        int f = IntStream.range(0, n).boxed().filter(i -> a[i] != i).findFirst().orElseThrow(RuntimeException::new);
        int s = IntStream.range(0, n).boxed().map(i -> n-1-i).filter(i -> a[i] != i).findFirst()
                .orElseThrow(RuntimeException::new);
        for (int i = f; i <= s; i++) {
            if (a[i] == i) return false;
        }
        return true;
    }
}
