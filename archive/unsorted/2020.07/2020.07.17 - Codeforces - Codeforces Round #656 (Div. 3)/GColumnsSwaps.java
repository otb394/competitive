package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import util.MiscUtility;
import util.Suppliers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GColumnsSwaps {
    List<List<Integer>> index;
    int[][] a;
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        a = new int[2][n];
        for (int i = 0; i < n; i++) {
            a[0][i] = in.nextInt();a[0][i]--;
        }
        for (int i = 0; i < n; i++) {
            a[1][i] = in.nextInt();a[1][i]--;
        }
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                add(counts, a[j][i]);
            }
        }
        ut.printDebug(Suppliers.of(Arrays.stream(a[0]).boxed().collect(Collectors.toList())), "a[0]",
                Suppliers.of(Arrays.stream(a[1]).boxed().collect(Collectors.toList())), "a[1]");
        ut.printDebug(counts, "counts");
        for (int i = 0; i < n; i++) {
            if (counts.getOrDefault(i, 0) != 2) {
                out.println(-1);
                return;
            }
        }
        index = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            index.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                int val = a[j][i];
                index.get(val).add(i);
            }
        }
        ut.printDebug(index, "index");
        int ans = 0;
        boolean[] dis = new boolean[n];
//        int[] p = new int[n];
        boolean[] dans = new boolean[n];
        int[] cy = new int[n];
        int cz=0;
        boolean[] tb = new boolean[n];
        for (int i = 0; i < n; i++) {
            ut.printDebug(i, "i");
            if (dis[i]) continue;
            if (a[0][i] == a[1][i]) continue;
            dis[i] = true;
            int curr = i;
            boolean cb = true;
            int tans = 1;
            cz=0;
            cy[cz] = i;
            tb[cz] = true;
            cz++;
            int tc = 0;
            while (true) {
                ut.printDebug(curr, "curr");
                int nx = getOther(curr, tc);
                boolean nb;
                if (a[tc][nx] == a[tc][curr]) {
                    nb = !cb;
                    tc = (tc + 1) % 2;
                } else {
                    nb = cb;
                }
//                boolean nb = (a[0][nx] == a[0][curr]) == (!cb);
                if (nx == i) {
                    if (!nb) {
                        out.println(-1);
                        return;
                    }
                    break;
                } else {
                    dis[nx] = true;
                    curr = nx;
                    cb = nb;
                    cy[cz] = nx;
                    tb[cz] = nb;
                    cz++;
                    if (nb) tans++;
                }
            }
            int ot = cz-tans;
            ut.printDebug(tans, "tans", ot, "ot");
            if (tans < ot) {
                for (int j = 0; j < cz; j++) {
                    dans[cy[j]] = tb[j];
                }
                ans += tans;
            } else {
                for (int j = 0; j < cz; j++) {
                    dans[cy[j]] = !tb[j];
                }
                ans += ot;
            }
        }
        out.println(ans);
        for (int i = 0; i < n; i++) {
            if (dans[i]) {
                out.print((i+1) + " ");
            }
        }
        out.println();
    }

    private int getOther(int i, int tc) {
//        int f = a[0][i];
        int f = a[tc][i];
        List<Integer> ind = index.get(f);
        for (int j = 0; j < 2; j++) {
            int in = ind.get(j);
            if (in != i) return in;
        }
        throw new IllegalStateException();
    }

    private void add(Map<Integer, Integer> mp, int key) {
        int val = mp.getOrDefault(key, 0);
        mp.put(key, val + 1);
    }
}
