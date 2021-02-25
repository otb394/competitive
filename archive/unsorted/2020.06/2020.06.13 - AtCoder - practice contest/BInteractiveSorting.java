package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BInteractiveSorting {
    private int q;
    private Map<Pair<Character, Character>, Integer> cache;
    private MiscUtility ut;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        ut = new MiscUtility(false);
        cache = new HashMap<>();
        int n = in.nextInt();
        q = in.nextInt();

        String s = IntStream.range(0, n).boxed().map(i -> ((char)('A' + i)))
                .collect(Collector.of(StringBuilder::new, StringBuilder::append, StringBuilder::append,
                        StringBuilder::toString));
//        MiscUtility.printDebug(s, "s");
//        TreeSet<Character> st = new TreeSet<>((i, j) -> compare(i, j, in, out));
//        for (int i = 0; i < n; i++) {
//            st.add(s.charAt(i));
//        }
//        StringBuilder builder = new StringBuilder();
//        for (char c : st) {
//            builder.append(c);
//        }
//        String ans = builder.toString();
        Comparator<Character> comp = (i,j) -> compare(i, j, in, out);
        Collector<Character, StringBuilder, String> collector = Collector.of(StringBuilder::new, StringBuilder::append,
                StringBuilder::append, StringBuilder::toString);
        String ans;
        if (n != 5) {
            ans = s.chars().mapToObj(i -> (char) i)
                    .sorted(comp)
                    .collect(collector);
        } else {
            List<Character> list = sortAns(s.chars().mapToObj(i -> (char)i).collect(Collectors.toList()), comp);
            Collections.reverse(list);
            ans = list.stream().collect(collector);
//            ans = sortSpecial(s.chars().mapToObj(i -> (char)i).collect(Collectors.toList()), comp).stream()
//                    .collect(collector);
        }
        out.println("! " + ans);
        out.flush();
    }

    private List<Character> sortAns(List<Character> s, Comparator<Character> comp) {
        char a,b;
        if (comp.compare(s.get(0), s.get(1)) > 0) {
            a = s.get(0);
            b = s.get(1);
        } else {
            a = s.get(1);
            b = s.get(0);
        }
        char c,d;
        if (comp.compare(s.get(2), s.get(3)) > 0) {
            c = s.get(2);
            d = s.get(3);
        } else {
            c = s.get(3);
            d = s.get(2);
        }
        if (comp.compare(a, c) < 0) {
            char ta = a;
            char tb = b;
            a = c;
            b = d;
            c = ta;
            d = tb;
        }
        char e = s.get(4);
        List<Character> firstArr = insertOne(Stream.of(a, c, d).collect(Collectors.toList()), e, comp);
        ut.printDebug(firstArr, "firstArr");
        ut.printDebug(a, "a", b, "b", c, "c", d, "d", e, "e");
        if (firstArr.get(0) == e) {
            List<Character> secondArr = insertOne(Stream.of(e, c, d).collect(Collectors.toList()), b, comp);
            ut.printDebug(secondArr, "secondArr");
            MiscUtility.assertion(secondArr.get(0) != b);
            List<Character> ret = new ArrayList<>();
            for (char cc : secondArr) {
                ret.add(cc);
                if (cc == e) {
                    ret.add(a);
                }
            }
            ut.printDebug(ret, "ret");
            return ret;
        } else {
            List<Character> input = new ArrayList<>();
            for (char val : firstArr) {
                if (val != a) {
                    input.add(val);
                }
            }
            List<Character> second = insertOne(input, b, comp);
            ut.printDebug(input, "input");
            ut.printDebug(second, "second");
            ut.printDebug(a, "a");
            List<Character> ret = new ArrayList<>();
            ret.add(a);
            ret.addAll(second);
            ut.printDebug(ret, "ret");
            return ret;
        }
    }

    private List<Character> insertOne(List<Character> list, char e, Comparator<Character> comp) {
        if (comp.compare(e, list.get(1)) > 0) {
            if (comp.compare(e, list.get(0)) > 0) {
                List<Character> arr = new ArrayList<>();
                arr.add(e);
                arr.addAll(list);
                return arr;
            } else {
                List<Character> arr = new ArrayList<>();
                arr.add(list.get(0));
                arr.add(e);
                arr.addAll(list.subList(1, 3));
                return arr;
            }
        } else {
            if (comp.compare(e, list.get(2)) < 0) {
                List<Character> arr = new ArrayList<>(list);
                arr.add(e);
                return arr;
            } else {
                List<Character> arr = new ArrayList<>(list.subList(0, 2));
                arr.add(e);
                arr.add(list.get(2));
                return arr;
            }
        }
    }

    private List<Character> sortSpecial(List<Character> s, Comparator<Character> comp) {
        List<Character> ft = sort3(s.subList(0, 3), comp);
        ut.printDebug(q, "q");
        List<Character> lt = sort2(s.subList(3, 5), comp);
        ut.printDebug(q, "q");
        return merge(ft, lt, comp);
    }

    private List<Character> sort3(List<Character> s, Comparator<Character> comp) {
        return s.stream().sorted(comp).collect(Collectors.toList());
    }

    private List<Character> sort2(List<Character> s, Comparator<Character> comp) {
        return s.stream().sorted(comp).collect(Collectors.toList());
    }

    private List<Character> merge(List<Character> a, List<Character> b, Comparator<Character> comp) {
        List<Character> ret = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < 3 && j < 2) {
            if (comp.compare(a.get(i), b.get(j)) < 0) {
                ret.add(a.get(i));
                i++;
            } else {
                ret.add(b.get(j));
                j++;
            }
        }
        while (i < 3) {
            ret.add(a.get(i));
            i++;
        }
        while (j < 2) {
            ret.add(b.get(j));
            j++;
        }
        return ret;
    }

    private int compare(char a, char b, InputReader in, OutputWriter out) {
        if (a == b) return 0;
        Pair<Character, Character> pr = Pair.of(a, b);
        if (cache.containsKey(pr)) {
            return cache.get(pr);
        }
        if (q == 0) {
            throw new RuntimeException("Queries exhausted");
        }
        out.println("? " + a + " " + b);
        out.flush();
        q--;

        char c = in.nextCharacter();
        int ans = (c == '<') ? -1 : 1;
        cache.put(pr, ans);
        return ans;
    }
}
