package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WakingUpBrain {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
//        boolean fir = true;
        while (!in.isExhausted()) {
            List<List<Integer>> graph = new ArrayList<>();
            int n = in.nextInt();
            for (int i = 0; i < 26; i++) {
                graph.add(new ArrayList<>());
            }
            int m = in.nextInt();
            String open = in.nextString();
            boolean[] awake = new boolean[26];
            int na = 3;
            for (int i = 0; i < 3; i++) {
                awake[get(open.charAt(i))] = true;
            }
            int[] an = new int[26];
            for (int i = 0; i < m; i++) {
                String pr = in.nextString();
                int f = get(pr.charAt(0));
                int s = get(pr.charAt(1));
                graph.get(f).add(s);
                graph.get(s).add(f);
            }
            Queue<Integer> queue = new LinkedList<>();
            int[] dis = new int[26];
            for (int i = 0; i < 3; i++) {
                int f = get(open.charAt(i));
                queue.add(f);
                dis[f] = 0;
            }
            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int v : graph.get(u)) {
                    if (!awake[v]) {
                        an[v]++;
                        if (an[v] == 3) {
                            dis[v] = dis[u] + 1;
                            awake[v] = true;
                            queue.add(v);
                            na++;
                        }
                    }
                }
            }
            int ans = Arrays.stream(dis).max().orElseThrow(RuntimeException::new);
//            if (fir) {
//                fir = false;
//            } else {
//                out.println();
//            }
            if (na < n) {
                out.println("THIS BRAIN NEVER WAKES UP");
            } else {
                out.printf("WAKE UP IN, %d, YEARS\n", ans);
            }
        }
    }

    private int get(char c) {
        return (c - 'A');
    }

    private char get(int i) {
        return (char)(i + 'A');
    }
}
