package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagrams {
    MiscUtility ut = new MiscUtility(false);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();
        in.readLine(false);
        for (int i = 1; i <= t; i++) {
            slve(i, in, out);
        }
    }

    public void slve(int testNumber, InputReader in, OutputWriter out) {
        List<String> p = new ArrayList<>();
        Map<String, String> sortMap = new HashMap<>();
        Comparator<String> comp = Comparator.comparing(sortMap::get);
        comp = comp.thenComparing(ss -> ss);
        while (true) {
            String s = in.readLine(false);
            if (s.trim().length() == 0) {
                break;
            }
            p.add(s);
        }
        for (String s : p) {
            sortMap.put(s, getSorted(s));
        }
        p.sort(comp);
        ut.printDebug(p, "p");
        int n = p.size();
        int i = 0;
        if (testNumber != 1) {
            out.println();
        }
        List<Pair<String, String>> ansList = new ArrayList<>();
        while (i < n) {
            int j = i+1;
            String sorted = sortMap.get(p.get(i));
            while (j < n && sortMap.get(p.get(j)).equals(sorted)) j++;
            for (int k = i; k < (j-1); k++) {
                for (int l = k+1; l < j; l++) {
                    ansList.add(Pair.of(p.get(k), p.get(l)));
//                    out.printf("%s = %s\n", p.get(k), p.get(l));
                }
            }
            i = j;
        }
        ansList.sort(Comparator.comparing(Pair<String, String>::getLeft).thenComparing(Pair::getRight));
        for (Pair<String, String> pr : ansList) {
            out.printf("%s = %s\n", pr.getLeft(), pr.getRight());
        }
    }

    private String getSorted(String s) {
        s = s.replaceAll("\\s", "");
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
