package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SquareDance {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int r=in.nextInt();
        int c = in.nextInt();
        long[][] map = new long[r][c];
        for (int i = 0; i < r; i++) {
            map[i] = in.nextLongArray(c);
        }
        long ans = solve(map,r,c);
//        out.prlongln(String.format("Case #%d: %d", testNumber, ans));
        out.println("Case #" + testNumber + ": " + ans);
    }

    private long solve(long[][] map, int r, int c) {
        List<Pair<Integer,Integer>> elimination = new ArrayList<>();
        long[][] rounds = new long[r][c];
        int[][] left = new int[r][c];
        int[][] right = new int[r][c];
        int[][] up = new int[r][c];
        int[][] down = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                left[i][j] = j-1;
                right[i][j] = j+1;
                up[i][j] = i-1;
                down[i][j] = i+1;
                elimination.add(Pair.of(i,j));
            }
        }

        long round = 1;

        while(!elimination.isEmpty()) {
            List<Pair<Integer,Integer>> candidates = elimination.stream().flatMap(curr -> getNeighbors(curr, left, right,
                    up, down, r,c).stream()).collect(Collectors.toList());
            Set<Pair<Integer,Integer>> toBeDeleted = candidates.stream()
                    .filter(curr -> toBeDeleted(curr, left, right, up, down, map, r, c)).collect(Collectors.toSet());
            //System.out.prlongln("To be deleted");
//            for (Pair<Long,Long> pr : toBeDeleted) {
                //System.out.prlongln("pr = " + pr);
//            }
            for (Pair<Integer,Integer> pr : toBeDeleted) {
                int dr = pr.getLeft();
                int dc = pr.getRight();
                rounds[dr][dc] = round;
                int lefc = left[dr][dc];
                int rigc = right[dr][dc];
                if (lefc != -1) {
                    right[dr][lefc] = rigc;
                }
                if (rigc != c) {
                    left[dr][rigc] = lefc;
                }
                int upr = up[dr][dc];
                int downr = down[dr][dc];
                if (upr != -1) {
                    down[upr][dc] = downr;
                }
                if (downr != r) {
                    up[downr][dc] = upr;
                }
            }
            round++;
            elimination = new ArrayList<>(toBeDeleted);
        }
        long ans = 0L;
        long rMOne = round - 1L;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (rounds[i][j] != 0) {
                    ans += map[i][j] * rounds[i][j];
                } else {
                    ans += map[i][j] * rMOne;
                }
            }
        }
        return ans;
    }

    private boolean toBeDeleted(Pair<Integer,Integer> curr, int[][] left, int[][] right, int[][] up, int[][] down,
                                long[][] map, int r, int c) {
        List<Pair<Integer,Integer>> neigbors = getNeighbors(curr, left, right, up, down, r, c);
        double val = neigbors.stream().mapToDouble(pr -> map[pr.getLeft()][pr.getRight()]).average().orElse(0.0);
        return (Double.compare(map[curr.getLeft()][curr.getRight()], val) < 0);
    }

    private List<Pair<Integer,Integer>> getNeighbors(Pair<Integer,Integer> curr, int[][] left, int[][] right,
                                                     int[][] up, int[][] down, int r, int c) {
        int i = curr.getLeft();
        int j = curr.getRight();
        List<Pair<Integer,Integer>> ret = new ArrayList<>();
        int lefc = left[i][j];
        if (lefc != -1) {
            ret.add(Pair.of(i,lefc));
        }
        int rightc = right[i][j];
        if (rightc != c) {
            ret.add(Pair.of(i,rightc));
        }
        int upr = up[i][j];
        if (upr != -1) {
            ret.add(Pair.of(upr,j));
        }
        int downr = down[i][j];
        if (downr != r) {
            ret.add(Pair.of(downr, j));
        }
        return ret;
    }
}
