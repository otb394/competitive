package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import util.MathUtility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiFunction;

public class FRobotsOnAGrid {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        String[] map = new String[n];
        for (int i = 0; i < n; i++) {
            map[i] = in.nextString();
        }
        String[] dir = new String[n];
        for (int i = 0; i < n; i++) {
            dir[i] = in.nextString();
        }
        BiFunction<Integer, Integer, Integer> coordToIndex = (i, j) -> (i*m+j);
        int[][] indeg = new int[n][m];
        Set<Integer> st = new HashSet<>();
        int mx = n*m;
        for (int i = 0; i < mx; i++) {
            st.add(i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int nxi = next(i, j, coordToIndex, dir);
                st.remove(nxi);
                int di = nxi/m;
                int dj = nxi%m;
                indeg[di][dj]++;
            }
        }
        Queue<Integer> q = new LinkedList<>(st);
        while (!q.isEmpty()) {
            int u = q.poll();
            int nx = next(u/m, u%m, coordToIndex, dir);
            int ni = nx/m;
            int nj = nx%m;
            indeg[ni][nj]--;
            if (indeg[ni][nj] == 0) {
                q.add(nx);
            }
        }

        boolean[][] done = new boolean[n][m];
        boolean[][] place = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (indeg[i][j] != 0 && !done[i][j]) {
                    List<Integer> cycle = new ArrayList<>();
                    int thisInd = coordToIndex.apply(i, j);
                    int curr = thisInd;
                    do {
                        cycle.add(curr);
                        done[curr/m][ curr%m] = true;
                        curr = next(curr/m, curr%m, coordToIndex, dir);
                    } while(curr != thisInd);
                    int nn = cycle.size();
                    for (int k = 0; k < nn; ++k) {
                        int node = cycle.get(k);
                        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
                        queue.add(Pair.of(node, 0));
                        int ni = node/m;
                        int nj = node%m;
                        if (map[ni].charAt(nj) == '0') {
                            place[ni][nj] = true;
                        }

                        while (!queue.isEmpty()) {
                            Pair<Integer, Integer> u = queue.poll();
                            int indu = u.getLeft();
                            int r = indu/m;
                            int c = indu%m;
                            process(r-1, c, n, m, indu, indeg, queue, coordToIndex, dir, u, k, nn, map, place, cycle);
                            process(r+1, c, n, m, indu, indeg, queue, coordToIndex, dir, u, k, nn, map, place, cycle);
                            process(r, c+1, n, m, indu, indeg, queue, coordToIndex, dir, u, k, nn, map, place, cycle);
                            process(r, c-1, n, m, indu, indeg, queue, coordToIndex, dir, u, k, nn, map, place, cycle);
                        }
                    }
                }
            }
        }

        int fans = 0;
        int sans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (indeg[i][j] != 0) {
                    fans++;
                    if (place[i][j]) {
                        sans++;
                    }
                }
            }
        }
        out.println(fans + " " + sans);
    }

    private int next(int i, int j, BiFunction<Integer, Integer, Integer> coordToIndex, String[] dir) {
        switch (dir[i].charAt(j)) {
            case 'U': return coordToIndex.apply(i-1, j);
            case 'D': return coordToIndex.apply(i+1, j);
            case 'L': return coordToIndex.apply(i, j-1);
            case 'R':
            default: return coordToIndex.apply(i, j+1);
        }
    }

    private void process(int i, int j, int n, int m, int indu, int[][] indeg, Queue<Pair<Integer, Integer>> queue,
                         BiFunction<Integer, Integer, Integer> coordToIndex, String[] dir, Pair<Integer, Integer> u,
                         int k, int nn, String[] map, boolean[][] place, List<Integer> cycle) {
        if (i >= 0 && i < n && j >= 0 && j < m) {
            if (next(i, j, coordToIndex, dir) == indu && indeg[i][j] == 0) {
                int steps = u.getRight() + 1;
                int nodeIndex = cycle.get(MathUtility.sub(k, steps%nn, nn));
                if (map[i].charAt(j) == '0') {
                    place[nodeIndex/m][nodeIndex%m] = true;
                }
                queue.add(Pair.of(coordToIndex.apply(i, j), steps));
            }
        }
    }
}
