package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class OperationsOnAMatrix {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();int m = in.nextInt();int q=in.nextInt();
        Map<Integer, Integer> mr = new HashMap<>();
        Map<Integer, Integer> mc = new HashMap<>();
        for (int i = 0; i < q; i++) {
            int x = in.nextInt();int y = in.nextInt();
            addMap(mr, x);
            addMap(mc, y);
        }
        long i = mr.values().stream().filter(t -> ((t%2) != 0)).count();
        long j = mc.values().stream().filter(t -> ((t%2) != 0)).count();
        long ans = (i*m) + (j*n) - 2*i*j;
        out.println(ans);
    }

    private void addMap(Map<Integer, Integer> map, int key) {
        int val = map.getOrDefault(key, 0);
        map.put(key, val + 1);
    }
}
