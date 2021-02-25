package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class MissingAPoint {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int N = 4*n - 1;
        int[] x = new int[N];
        int[] y = new int[N];
        for (int i = 0; i < N; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        Map<Integer, Integer> mpx = new HashMap<>();
        Map<Integer, Integer> mpy = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int valx = mpx.getOrDefault(x[i], 0);
            mpx.put(x[i], valx+1);
            int valy = mpy.getOrDefault(y[i], 0);
            mpy.put(y[i], valy+1);
        }
        int ansx = mpx.entrySet().stream().filter(en -> en.getValue()% 2 != 0).findFirst().map(Map.Entry::getKey)
                .orElseThrow(() -> new RuntimeException("No odd x coordinate"));
        int ansy = mpy.entrySet().stream().filter(en -> en.getValue()% 2 != 0).findFirst().map(Map.Entry::getKey)
                .orElseThrow(() -> new RuntimeException("No odd y coordinate"));
        out.println(ansx + " " + ansy);
    }
}
