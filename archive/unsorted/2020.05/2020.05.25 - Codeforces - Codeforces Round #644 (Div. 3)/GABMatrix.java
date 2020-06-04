package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

public class GABMatrix {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt(); int a = in.nextInt(); int b = in.nextInt();
        int[][] arr = new int[n][m];
        if ((a * n) == (b*m)) {
            PriorityQueue<Pair<Integer,Integer>> pq = new PriorityQueue<>(this::compare);
            for (int i = 0; i < m; i++) {
                pq.add(Pair.of(i, 0));
            }

            for (int i = 0; i < n; i++) {
                List<Pair<Integer,Integer>> cols = new ArrayList<>();
                for (int j = 0; j < a; j++) {
                    Pair<Integer,Integer> pr = Optional.ofNullable(pq.poll())
                            .orElseThrow(() -> new RuntimeException("Unexpected"));
                    int col = pr.getLeft();
                    arr[i][col] = 1;
//                    pq.add(Pair.of(col, pr.getRight()+1));
                    cols.add(Pair.of(col, pr.getRight()+1));
                }
                pq.addAll(cols);
            }
            out.println("YES");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    out.print(arr[i][j]);
                }
                out.println();
            }
        } else {
            out.println("NO");
        }
    }

    private int compare(Pair<Integer,Integer> a, Pair<Integer,Integer> b) {
        return a.getRight().compareTo(b.getRight());
    }
}
