package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Overrandomized {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
//        System.out.println("testNumber = " + testNumber);
        int u = in.nextInt();
//        System.out.println("u = " + u);
        int N = 10000;
        List<Pair<Long,String>> input = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            long q = in.nextLong();
            String r = in.nextString();
            input.add(Pair.of(q,r));
        }

        String ans;
        if (u == 2) {
            //TODO
            ans = solve(input, N, u);
        } else if (u == 16) {
            ans = solve(input, N, u);
        } else {
            throw new RuntimeException("Unexpected input");
        }
        out.println(String.format("Case #%d: %s", testNumber, ans));
    }

    private String solve(List<Pair<Long,String>> input, int N, int u) {

        TreeMap<Character, Integer> mp = new TreeMap<>();
        for (int i = 0; i < N; i++) {
            Pair<Long, String> pr = input.get(i);
//            char firstChar = pr.getRight().charAt(0);
//            int val = mp.getOrDefault(firstChar, 0);
//            mp.put(firstChar, val+1);
            String str = pr.getRight();
            int strLen = str.length();
            if (strLen == u) {
                char firstChar = str.charAt(0);
                int val = mp.getOrDefault(firstChar, 0);
                mp.put(firstChar, val+1);
                for (int j = 0; j < strLen; j++) {
                    char c = str.charAt(j);
                    int vall = mp.getOrDefault(c, 0);
                    mp.put(c, vall);
                }
            }
        }
        List<Pair<Character, Integer>> mpList = mp.entrySet().stream()
                .map(en -> Pair.of(en.getKey(), en.getValue())).collect(Collectors.toList());
        Comparator<Pair<Character, Integer>> comparator = Comparator.comparing(Pair::getRight);
        mpList.sort(comparator);
//        System.out.println("mp = " + mp);
        List<Character> charList = mpList.stream().map(Pair::getLeft).collect(Collectors.toList());
        Collections.reverse(charList);
//        System.out.println("charList = " + charList);
        if (charList.size() < 10) {
            //TODO: Add case for additional missing letters
            throw new RuntimeException("Missing letters");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(charList.get(9));
        for (int i = 0; i < 9; i++) {
            stringBuilder.append(charList.get(i));
        }
        return stringBuilder.toString();
    }
}
