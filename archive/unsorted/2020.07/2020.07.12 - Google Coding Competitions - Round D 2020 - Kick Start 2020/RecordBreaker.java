package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class RecordBreaker {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] v = in.nextIntArray(n);
        int largest = -1;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (v[i] > largest) {
                largest = v[i];
                if (i == (n-1) || (v[i] > v[i+1])) {
                    ans++;
                }
            }
        }
        out.println(String.format("Case #%d: %d", testNumber, ans));
    }
}
