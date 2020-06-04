package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.AdjacencyList;
import datastructure.Graph;
import datastructure.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import static util.MiscUtility.assertion;

public class EGraphColoring {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();int m = in.nextInt();
        int no = in.nextInt();
        int nt = in.nextInt();
        int nh = in.nextInt();
        Set<Integer> zrs = new HashSet<>();
        Graph<Integer, Graph.BaseEdge<Integer>> g = new AdjacencyList<>();
        for (int i = 0; i < n; i++) {
            g.addVertex(i);
            zrs.add(i);
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();u--;
            int v = in.nextInt();v--;
            zrs.remove(u);zrs.remove(v);
            Graph.BaseEdge<Integer> edge = Graph.BaseEdge.of(u, v);
            g.addEdge(edge);
        }

        int[] assign = new int[n];
        boolean[] done = new boolean[n];
        int[] temp = new int[n];
        boolean[] disc = new boolean[n];
        int tc = 0;
        List<Pair<Set<Integer>, Set<Integer>>> sets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!done[i]) {
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                disc[i] = true;
                temp[i] = 1;
                done[i] = true;
                List<Set<Integer>> setlist = new ArrayList<>();
                setlist.add(new HashSet<>());
                setlist.add(new HashSet<>());
                setlist.get(0).add(i);
                while(!queue.isEmpty()) {
                    int tp = queue.poll();
                    done[tp] = true;
                    List<Graph.BaseEdge<Integer>> edges = g.getEdges(tp);
                    int val = temp[tp];
                    for (Graph.BaseEdge<Integer> edge: edges) {
                        int v = edge.second;
                        if (temp[v] == val) {
                            out.println("NO");
                            return;
                        }
                        temp[v] = getOtherLabel(val);
                        int ind = temp[v]-1;
                        setlist.get(ind).add(v);
                        if (!disc[v]) {
                            disc[v] = true;
                            queue.add(v);
                        }
                    }
                }
                int minLabel = (setlist.get(0).size() <= setlist.get(1).size()) ? (1) : 2;
                for (int num : setlist.get(minLabel-1)) {
                    assign[num] = 2;
                    tc++;
                }
                int othl = getOtherLabel(minLabel);
                for (int num : setlist.get(othl-1)) {
                    assign[num] = 4;
                }
                sets.add(Pair.of(setlist.get(minLabel-1), setlist.get(othl-1)));
            }
        }
        if (tc > nt) {
            out.println("NO");
            return;
        }
        int diff = nt - tc;
        if (diff != 0) {
            List<Integer> diffs = sets.stream().map(pr -> pr.getRight().size() - pr.getLeft().size())
                    .collect(Collectors.toList());
            Optional<List<Integer>> choices = subsetSum(diff, diffs);
            if (!choices.isPresent()) {
                out.println("NO");
                return;
            }
            List<Integer> choiceList = choices.orElseThrow(() -> new RuntimeException("Cannot happen"));
            for (int choice : choiceList) {
                Set<Integer> my = sets.get(choice).getLeft();
                for (int mys : my) {
                    assign[mys] = 4;
                }
                Set<Integer> theirs = sets.get(choice).getRight();
                for (int their: theirs) {
                    assign[their] = 2;
                }
            }
        }
//        if (diff > zrs.size()) {
//            out.println("NO");
//            return;
//        }
//        for (int zr : zrs) {
//            if (diff == 0) break;
//            assertion(assign[zr] != 2,
//                    String.format("assign[zr] != 2. zr = %d, assign[zr] = %d", zr, assign[zr]));
//            assign[zr] = 2;
//            diff--;
//        }
//
        List<Integer> ohs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (assign[i] == 4) {
                ohs.add(i);
            } else if (assign[i] == 0) {
                assertion(false, String.format("Assign[%d] = 0", i));
            }
        }
        int tot = no+nh;
        assertion(ohs.size() == tot, String.format("ohs.size [%d] not equal to tot [%d]", ohs.size(), tot));
        for (int i = 0; i < no; i++) {
            assign[ohs.get(i)] = 1;
        }
        for (int i = no; i < tot; i++) {
            assign[ohs.get(i)] = 3;
        }
        out.println("YES");
        for (int i = 0; i < n; i++) {
            out.print(assign[i]);
        }
        out.println();
    }

    private Optional<List<Integer>> subsetSum(int val, List<Integer> arr) {
        int len = arr.size();
        int maxVal = Math.max(val, arr.stream().mapToInt(i -> i).max()
                .orElseThrow(() -> new RuntimeException("Empty components array")));
        boolean[][] dp = new boolean[len][maxVal+1];
        boolean[][] choice = new boolean[len][maxVal+1];
        dp[0][0] = true;
        dp[0][arr.get(0)] = true;
        choice[0][arr.get(0)] = true;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= val; j++) {
                if (dp[i-1][j]) {
                    dp[i][j] = true;
                } else if (j >= arr.get(i) && dp[i-1][j-arr.get(i)]) {
                    dp[i][j] = true;
                    choice[i][j] = true;
                }
            }
        }
        if (!dp[len-1][val]) {
            return Optional.empty();
        } else {
            List<Integer> ret = new ArrayList<>();
            int vind = val;
            for (int i = len-1; i >= 0; i--) {
                assertion(dp[i][vind], String.format("Subset sum issue: dp[%d][%d] is false.", i, vind));
                if (choice[i][vind]) {
                    ret.add(i);
                    vind-=arr.get(i);
                }
            }
            return Optional.of(ret);
        }
    }

    private int getOtherLabel(int label) {
        if (label == 1) return 2;
        return 1;
    }
}
