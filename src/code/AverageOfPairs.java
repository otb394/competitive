package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class AverageOfPairs {
    private static final int MAX = 2000;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        out.println(solve(n, arr));
    }

    private long solve(int n, int[] arr) {
        for (int i = 0; i < n; i++) {
            arr[i]+=(MAX>>1);
        }
        long[] freq = new long[MAX+1];
        for (int i = 0; i < n; i++) {
            freq[arr[i]]++;
        }

        long ans = 0L;
        for (int i = 0; i <= MAX; i++) {
            for (int j = i+1; j <= MAX; j++) {
                int sm = i+j;
                if ((sm & 1) == 0) {
                    int mean = sm / 2;
                    if (freq[mean] > 0) {
                        ans += freq[i] * freq[j];
                    }
                }
            }
        }

        for (int i = 0; i <= MAX; i++) {
            ans+=(freq[i]*(freq[i]-1L))/2L;
        }

        return ans;
    }
}
