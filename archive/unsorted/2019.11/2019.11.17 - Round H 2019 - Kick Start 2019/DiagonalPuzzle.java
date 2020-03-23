package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import code.datastructure.Pair;
import code.datastructure.UnionFind;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiagonalPuzzle {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
//        System.out.println("=========================");
        int n = in.nextInt();
        String[] grid = new String[n];
        for (int i = 0; i < n; i++) {
            grid[i]=in.nextString();
        }
        Queue<Integer> q = new LinkedList<>();
        int N = 4*n-2;
        boolean[] disc = new boolean[N];
        List<Pair<Integer, Integer>> sets = new ArrayList<>();
        UnionFind uf = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            if (!disc[i]) {
//                System.out.println("i = " + i);
                disc[i]=true;
                int mySet = i;
                Integer otherSet = null;
                q.add(i);
                while (!q.isEmpty()) {
                    int d = q.remove();
//                    System.out.println("d = " + d);
                    List<Pair<Integer,Integer>> coord = getCoord(d, n);
//                    System.out.println("coord = " + coord);
                    for (Pair<Integer,Integer> c : coord) {
                        int otherD = getOther(d, c, n);
//                        System.out.println("c = " + c);
//                        System.out.println("otherD = " + otherD);
                        if (getChar(grid, c.getLeft(), c.getRight()) == '#') {
//                            System.out.printf("Union of %d and %d\n", d, otherD);
                            uf.union(d, otherD);
                        } else {
                            if (uf.findSet(d) == uf.findSet(mySet)) {
                                if (otherSet == null) {
                                    otherSet = uf.findSet(otherD);
                                } else {
//                                    System.out.printf("Union of %d and %d\n", otherSet, otherD);
                                    uf.union(otherSet, otherD);
                                }
                            } else {
//                                System.out.printf("Union of %d and %d\n", mySet, otherD);
                                uf.union(mySet, otherD);
                                otherSet = uf.findSet(d);
                            }
//                            System.out.println("otherSet = " + otherSet);
                        }
                        if (!disc[otherD]) {
                            disc[otherD]=true;
//                            System.out.printf("Adding otherD (%d) to the queue\n", otherD);
                            q.add(otherD);
//                            System.out.println("q = " + q);
                        }
                    }
                }
                if (otherSet != null) {
                    int fi = uf.findSet(mySet);
                    int fj = uf.findSet(otherSet);
//                    System.out.printf("(%d,%d) added to the sets\n", fi, fj);
                    sets.add(Pair.of(fi, fj));
                }
            }
        }
        int ans = sets.stream().map(pr -> Math.min(uf.getSizeOfSet(pr.getLeft()), uf.getSizeOfSet(pr.getRight())))
                .mapToInt(i -> i).sum();
//        for (Pair<Integer, Integer> pr : sets) {
//            System.out.printf("Set element = (%d,%d)\n", pr.getLeft(), pr.getRight());
//            System.out.printf("Left set size (with rep %d) = %d\n", uf.findSet(pr.getLeft()), uf.getSizeOfSet(pr.getLeft()));
//            System.out.printf("Right set size (with rep %d) = %d\n", uf.findSet(pr.getRight()), uf.getSizeOfSet(pr.getRight()));
//        }
//        System.out.println("ans = " + ans);
        out.printf("Case #%d: %d\n", testNumber, ans);
    }

    private char getChar(String[] grid, int i, int j) {
        String row = grid[i];
        return row.charAt(j);
    }

    private int getOther(int d, Pair<Integer,Integer> c, int n) {
        Pair<Integer, Integer> pr = getDiagonals(c.getLeft(), c.getRight(),n);
        if (pr.getLeft() == d) {
            return pr.getRight();
        } else {
            return pr.getLeft();
        }
    }

    private List<Pair<Integer,Integer>> getCoord(int diagNo, int n) {
        if (diagNo < (2*n-1)) {
            if (diagNo < (n-1)) {
                return IntStream.range(0, diagNo+1)
                        .boxed()
                        .map(i -> Pair.of(i, diagNo - i))
                        .collect(Collectors.toList());
            } else {
                return IntStream.range(diagNo - n + 1, n).boxed()
                        .map(i -> Pair.of(i, diagNo - i))
                        .collect(Collectors.toList());
            }
        } else {
            int diff = diagNo - 3*n+2;
            if (diff <= 0) {
                return IntStream.range(0, n+diff).boxed()
                        .map(i -> Pair.of(i, i-diff)).collect(Collectors.toList());
            } else {
                return IntStream.range(diff, n).boxed()
                        .map(i -> Pair.of(i, i-diff)).collect(Collectors.toList());
            }
        }
    }

    private Pair<Integer, Integer> getDiagonals(int i, int j,int n) {
        return Pair.of(i+j, 3*n-2+i-j);
    }
}
