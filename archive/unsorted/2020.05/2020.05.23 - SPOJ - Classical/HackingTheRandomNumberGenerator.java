package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class HackingTheRandomNumberGenerator {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = in.nextIntArray(n);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(arr[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int num = arr[i];
            int oth = num + k;
            if (set.contains(oth)) ans++;
        }
        out.print(ans);
    }
}
