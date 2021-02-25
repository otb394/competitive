package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DSolveTheMaze {
    int[] rd = new int[]{0, -1, 0, 1};
    int[] cd = new int[]{-1, 0, 1, 0};
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        String[] map = new String[n];
        for (int i = 0; i < n; i++) {
            map[i] = in.nextString();
        }
        boolean[][] init = getReachables(n, m, map);
        List<Pair<Integer,Integer>> bads = new ArrayList<>();
        boolean noGood = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i].charAt(j) == 'G') {
                    noGood = false;
                    if (!init[i][j]) {
                        out.println("No");
                        return;
                    }
                } else if (map[i].charAt(j) == 'B' && init[i][j]) {
                    bads.add(Pair.of(i, j));
                }
            }
        }
        if (noGood || bads.isEmpty()) {
            out.println("Yes");
            return;
        }
        StringBuilder[] newMap = new StringBuilder[n];
        for (int i = 0; i < n; i++) {
            newMap[i] = new StringBuilder(map[i]);
        }
        for (Pair<Integer,Integer> bad: bads) {
            int bi = bad.getLeft();
            int bj = bad.getRight();
            for (int i = 0; i < 4; i++) {
                int nr = bi + rd[i];
                int nc= bj + cd[i];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && init[nr][nc]) {
                    if (map[nr].charAt(nc) == 'G') {
                        out.println("No");
                        return;
                    }
                    if (map[nr].charAt(nc) == '.') {
                        newMap[nr].setCharAt(nc, '#');
                    }
                }
            }
        }
        String[] finalMap = new String[n];
        for (int i = 0; i < n; i++) {
            finalMap[i] = newMap[i].toString();
        }
        boolean[][] finals = getReachables(n, m, finalMap);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i].charAt(j) == 'B' && finals[i][j]) {
                    out.println("No");
                    return;
                }
                if (map[i].charAt(j) == 'G' && !finals[i][j]) {
                    out.println("No");
                    return;
                }
            }
        }
        out.println("Yes");
    }

    private boolean[][] getReachables(int n, int m, String[] map) {
        Queue<Pair<Integer,Integer>> q = new LinkedList<>();
        boolean[][] ret = new boolean[n][m];
        if (map[n-1].charAt(m-1) == '.') {
            ret[n-1][m-1] = true;
            q.add(Pair.of(n-1, m-1));
        }
        while(!q.isEmpty()) {
            Pair<Integer,Integer> u = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = u.getLeft() + rd[i];
                int nc = u.getRight() + cd[i];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !ret[nr][nc] && map[nr].charAt(nc) != '#') {
                    ret[nr][nc] = true;
                    q.add(Pair.of(nr, nc));
                }
            }
        }
        return ret;
    }
}
