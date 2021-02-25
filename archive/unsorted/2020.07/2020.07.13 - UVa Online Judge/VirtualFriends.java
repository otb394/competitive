package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import datastructure.UnionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class VirtualFriends {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int f = in.nextInt();
        List<Pair<String, String>> input = new ArrayList<>();
        Set<String> st = new HashSet<>();
        for (int i = 0; i < f; i++) {
            String u = in.nextString();
            String v = in.nextString();
            input.add(Pair.of(u, v));
            st.add(u);
            st.add(v);
        }
        List<String> names = st.stream().sorted().collect(Collectors.toList());
        int nn = names.size();
        Map<String, Integer> mp = new HashMap<>();
        for (int i = 0; i < nn; i++) {
            mp.put(names.get(i), i);
        }
        UnionFind uf = new UnionFind(nn);
        for (int i = 0; i < f; i++) {
            int u = mp.get(input.get(i).getLeft());
            int v = mp.get(input.get(i).getRight());
            uf.union(u, v);
            out.println(uf.getSizeOfSet(u));
        }
    }
}
