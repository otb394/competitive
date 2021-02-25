package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import datastructure.UnionFind;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class EDirectingEdges {
    List<List<Pair<Integer, Boolean>>> graph;
    boolean[] dis;
    boolean[] done;
    UnionFind ufd;
    Set<Pair<Integer, Integer>> others;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        others = new HashSet<>();
        List<Pair<Pair<Integer, Integer>, Boolean>> edgeList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int t = in.nextInt();
            int u = in.nextInt();u--;
            int v = in.nextInt();v--;
            if (t == 1) {
                graph.get(u).add(Pair.of(v, true));
            } else {
                graph.get(u).add(Pair.of(v, false));
                graph.get(v).add(Pair.of(u, false));
            }
            edgeList.add(Pair.of(Pair.of(u, v), t == 1));
        }
        ufd = new UnionFind(n);
        for (Pair<Pair<Integer, Integer>, Boolean> pr : edgeList) {
            if (pr.getRight()) {
                int u = pr.getLeft().getLeft();
                int v = pr.getLeft().getRight();
                ufd.union(u, v);
            }
        }
        dis = new boolean[n];
        done = new boolean[n];
//        Queue<Integer> queue = new LinkedList<>();
//        boolean cy = false;
        try {
            for (int i = 0; i < n; i++) {
                if (!dis[i]) {
                    dis[i] = true;
                    dfs(i);
                }
            }
        } catch (CycleFoundException ex) {
            out.println("NO");
            return;
        }
//        for (int i = 0; i < n; i++) {
//            if (!dis[i]) {
//                queue.add(i);
//                dis[i] = true;
//                while (!queue.isEmpty()) {
//                    int u = queue.poll();
//                    for (Pair<Integer, Boolean> pr : graph.get(u)) {
//                        if (pr.getRight()) {
//                            int v = pr.getLeft();
//                            if (dis[v]) {
//                                cy = true;
//                                break;
//                            } else {
//                                dis[v] = true;
//                                queue.add(v);
//                            }
//                        }
//                    }
//                    if (cy) {
//                        break;
//                    }
//                }
//                if (cy) {
//                    break;
//                }
//                done[i] = true;
//            }
//        }
//        if (cy) {
//            out.println("NO");
//            return;
//        }
        out.println("YES");
        for (Pair<Pair<Integer, Integer>, Boolean> pr : edgeList) {
            int u = pr.getLeft().getLeft();
            int v = pr.getLeft().getRight();
            if (pr.getRight()) {
                out.println((u+1) + " " + (v+1));
            } else {
                int pu = ufd.findSet(u);
                int pv = ufd.findSet(v);
                if (pu != pv) {
                    if (pu < pv) {
                        out.println((u+1) + " " + (v+1));
                    } else {
                        out.println((v+1) + " " + (u+1));
                    }
                } else {
                    if (others.contains(Pair.of(u, v))) {
                        out.println((u+1) + " " + (v+1));
                    } else if (others.contains(Pair.of(v, u))) {
                        out.println((v+1) + " " + (u+1));
                    } else {
                        throw new IllegalStateException();
                    }
                }
            }
        }
    }

    private void dfs(int s) {
        for (Pair<Integer, Boolean> pr : graph.get(s)) {
            int v = pr.getLeft();
            if (pr.getRight()) {
                if (done[v]) {
                    continue;
                }
                if (dis[v]) {
                    throw new CycleFoundException();
                }
                dis[v] = true;
                dfs(v);
            } else {
                if (ufd.isSameSet(s, v)) {
                    if (dis[v]) {
                        if (done[v]) {
                            others.add(Pair.of(s, v));
//                            others.put(s, v);
                        } else {
                            others.add(Pair.of(v, s));
//                            others.put(v, s);
                        }
                    }
                }
            }
        }
        done[s] = true;
    }

    private class CycleFoundException extends RuntimeException {

    }
}
