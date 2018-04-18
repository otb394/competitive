package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        long sum = 0L;
        for (int i = 0; i < n; i++) {
            sum+=a[i];
        }
        long hf = (sum%2L == 0L) ? (sum/2L) : (sum/2L + 1L);
        long curr = 0L;
        for (int i = 0; i < n; i++) {
            curr += a[i];
            if (curr >= hf) {
                out.print(i+1);
                break;
            }
        }
    }
}
