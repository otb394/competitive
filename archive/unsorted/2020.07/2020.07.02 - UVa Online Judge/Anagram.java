package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MathUtility;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Anagram {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.nextString();
        Map<Character, Integer> mp = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            char small = ((char)('a' + i));
            char large = ((char)('A' + i));
            mp.put(large, 2*i);
            mp.put(small, 2*i+1);
        }
        Comparator<Character> comp = Comparator.comparingInt(mp::get);
        int n = s.length();
//        int[] p = new int[n];
        Character[] p = new Character[n];
        for (int i = 0; i < n; i++) {
            p[i] = s.charAt(i);
//            p[i] = mp.get(s.charAt(i));
        }
        Arrays.sort(p, comp);
        Set<String> st = new HashSet<>();
        do {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < n; i++) {
//                int index = p[i];
//                char cc = getChar(index);
                char cc = p[i];
                builder.append(cc);
            }
            String tans = builder.toString();
            if (!st.contains(tans)) {
                out.println(tans);
                st.add(tans);
            }
        } while (MathUtility.next_permutation(p, 0, n, comp));
    }

    private char getChar(int index) {
        int base = index / 2;
        if (index % 2 == 0) {
            return ((char)(base + 'A'));
        } else {
            return ((char)(base + 'a'));
        }
    }
}
