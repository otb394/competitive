package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class Ananagrams {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Map<String, List<Integer>> mp = new HashMap<>();
        List<String> words = new ArrayList<>();
        while (true) {
            String s = in.nextString();
            if (s.equals("#")) {
                break;
            }
            words.add(s);
        }
        int ns = words.size();
        for (int i = 0; i < ns; i++) {
            String word = words.get(i);
            char[] c = word.toLowerCase().toCharArray();
            Arrays.sort(c);
            String sorted = new String(c);
            List<Integer> indexes = Optional.ofNullable(mp.get(sorted)).orElseGet(ArrayList::new);
            ut.printDebug(word, "word", sorted, "sorted", indexes, "indexes");
            indexes.add(i);
            ut.printDebug(indexes, "indexes after");
            mp.put(sorted, indexes);
        }
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry: mp.entrySet()) {
            List<Integer> indexes = entry.getValue();
            ut.printDebug(indexes, "indexes");
            if (indexes.size() == 1) {
                ans.add(words.get(indexes.get(0)));
            }
        }
        ans.sort(String::compareTo);
        for (String word : ans) {
            out.println(word);
        }
    }
}
