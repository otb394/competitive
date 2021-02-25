package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import algorithms.BinarySearch;
import datastructure.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class LockedDoors {
//    private final int INF = 112345;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int sq = (int) Math.sqrt(n);
        int[] d = in.nextIntArray(n-1);
        int N = n-1;
        List<Integer> ans = new ArrayList<>();
        int inf = d[0];
        for (int i = 1; i < N; i++) {
            inf = Math.max(inf, d[i]);
        }
        inf++;
        for (int i = 0; i < q; i++) {
            int s = in.nextInt();s--;
            int k = in.nextInt();
            if (k == 1) {
                ans.add(s);
                continue;
            }
            if (s == 0) {
                ans.add(k-1);
                continue;
            }
            if (s == n-1) {
                ans.add(n-k);
                continue;
            }
            int minab = Math.min(d[s-1], d[s]);
            int minV = getMinValForRooms(k, n, s, minab, inf);
            Pair<Integer, Integer> po = openLess(minV, n, s);
            Pair<Integer, Integer> pt = openLess(minV-1, n, s);
//            int ko = po.getLeft() + po.getRight() + 1;
            int kt = pt.getLeft() + pt.getRight() + 1;
            if (po.getLeft().equals(pt.getLeft())) {
                int lr = pt.getRight();
                int ar = lr + (k - kt);
                ans.add(s + ar);
            } else if (po.getRight().equals(pt.getRight())) {
                int lr = pt.getLeft();
                int al = lr - (k - kt);
                ans.add(s + al);
            } else {
                throw new IllegalStateException();
            }
        }

        out.printf("Case #%d: ", testNumber);
        for (int tv : ans) {
            out.print(tv + " ");
        }
        out.println();
    }

    //Minimum value such that no. of open rooms >= k
    private int getMinValForRooms(int k, int n, int s, int minab, int inf) {
        //k == 1? Return 0
        if (k == 1) return 0;
        Function<Integer, Boolean> func = val -> getRoomsLess(val, n, s) >= k;
        return BinarySearch.searchFirstOne(minab, inf, func);
    }

    //TODO: RMQ
    private int getMax(int i, int j) {
        return 0;
//        return query(i, j);
    }

    private int getRoomsLess(int val, int n, final int s) {
        Pair<Integer, Integer> pr = openLess(val, n, s);
        return pr.getLeft() + pr.getRight() + 1;
    }

    //The number of rooms visited if all doors strictly less than val are opened
    private Pair<Integer, Integer> openLess(int val, int n, final int s) {
        Function<Integer, Boolean> lfunc = x -> !(getMax(s-x, s-1) < val);
        int dl = BinarySearch.searchLastZero(0, s+1, lfunc);
        Function<Integer, Boolean> rfunc = x -> !(getMax(s, s+x-1) < val);
        int dr = BinarySearch.searchLastZero(0, n-s, rfunc);
        return Pair.of(dl, dr);
    }
}
