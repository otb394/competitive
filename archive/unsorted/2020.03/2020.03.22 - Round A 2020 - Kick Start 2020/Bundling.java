package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bundling {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();int k=in.nextInt();
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            strings.add(in.nextString());
        }
        long ans = solve(n,k,strings);
        out.println("Case #" + testNumber + ": " + ans);
    }

    private long solve(int n, int k, List<String> strings) {
        Trie<Vertex, Character> trie = new Trie<>(Vertex::new);

        for (String s: strings) {
            int len = s.length();
            Vertex current = trie.getRoot();
            for (int i = 0; i < len; i++) {
                Vertex nd = Optional.ofNullable(current.get(s.charAt(i))).orElseGet(Vertex::new);
                nd.count++;
                current.put(s.charAt(i), nd);
                current = nd;
            }
        }
        return trie.stream().mapToLong(v -> v.count/k).sum();
    }

    public static class Vertex extends Trie.Node<Vertex, Character> {
        public int count;

        public Vertex() {
            super();
            this.count = 0;
        }
    }
}
