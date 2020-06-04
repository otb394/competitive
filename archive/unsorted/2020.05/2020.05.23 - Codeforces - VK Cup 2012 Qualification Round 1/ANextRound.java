package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ANextRound {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = in.nextIntArray(n);
        List<Integer> arr = Arrays.stream(a).filter(v -> v > 0).boxed().collect(Collectors.toList());
        int len = arr.size();
        int ans;
        if (len <= k) {
            ans = len;
        } else {
            int val = arr.get(k-1);
            int i = k-1;
            while(i < len && arr.get(i) == val) i++;
            ans = i;
        }
        out.print(ans);
    }
}
