package code;

import FastIO.OutputWriter;
import datastructure.Pair;
import io.InputReader;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderCharacters {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();
        Map<Character, Integer> counts = new HashMap<>();
        int n = s.length();
        List<Pair<Character, Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            char gc = Character.toLowerCase(ch);
            int val = counts.getOrDefault(gc, 0);
            counts.put(gc, val + 1);
            list.add(Pair.of(ch, i));
        }
        list.sort(Comparator.comparingInt((Pair<Character, Integer> pr)
                        -> counts.getOrDefault(Character.toLowerCase(pr.getLeft()), 0))
                        .thenComparingInt(Pair::getRight));
        StringBuilder builder = new StringBuilder();
        String ans = (counts.getOrDefault(list.get(0).getLeft(), 0) == 1) ? String.valueOf(list.get(0).getLeft()) : "No non repeating characters";
        out.println(ans);
        for (Pair<Character, Integer> pr : list) {
            builder.append(pr.getLeft());
        }
        out.println(builder.toString());
    }
}
