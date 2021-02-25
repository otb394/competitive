package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import datastructure.UnionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheForrestForTheTrees {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        List<Pair<Character, Character>> input = new ArrayList<>();
        while (true) {
            String s = in.nextString();
            if (s.charAt(0) == '*') {
                break;
            }
            input.add(Pair.of(s.charAt(1), s.charAt(3)));
        }
        String[] tokens = in.nextString().split(",");
        Arrays.sort(tokens, String::compareTo);
        int n = tokens.length;
        Map<Character, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char tc = tokens[i].charAt(0);
            mp.put(tc, i);
        }
        UnionFind ufd = new UnionFind(n);
        for (Pair<Character, Character> pr : input) {
            int u = mp.get(pr.getLeft());
            int v = mp.get(pr.getRight());
            ufd.union(u, v);
        }
        int tr = 0;
        int ac = 0;
        for (int i = 0; i < n; i++) {
            if (ufd.findSet(i) == i) {
                if (ufd.getSizeOfSet(i) == 1) {
                    ac++;
                } else {
                    tr++;
                }
            }
        }
        out.printf("There are %d tree(s) and %d acorn(s).\n", tr, ac);
    }
}
