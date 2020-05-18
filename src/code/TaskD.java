package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong();
        }
        Long[] ans = solve(n, arr);
        int z = ans.length;
        out.println(z);
        for (int i = 0; i < z; i++) {
            out.print(ans[i] + " ");
        }
    }

    private Long[] solve(int n, long[] arr) {
        TreeMap<Long, TreeSet<Integer>> multiple, single;
        multiple = new TreeMap<>();
        single = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            addValue(arr[i], i, multiple, single);
        }

//        System.err.println("Single");
//        single.forEach((k,v) -> {
//            System.err.print(k + "->");
//            System.err.print("[");
//            v.forEach(e -> {
//                System.err.print(e + ",");
//            });
//            System.err.println("]");
//        });
//
//        System.err.println("Multiple");
//        multiple.forEach((k,v) -> {
//            System.err.print(k + "->");
//            System.err.print("[");
//            v.forEach(e -> {
//                System.err.print(e + ",");
//            });
//            System.err.println("]");
//        });

        while(!multiple.isEmpty()) {
            Map.Entry<Long, TreeSet<Integer>> entry = multiple.firstEntry();
            TreeSet<Integer> set = entry.getValue();
            //Set would have multiple elements
            int minInd, secInd;
            minInd = set.first();
            secInd = set.higher(minInd);
            set.remove(minInd);
            set.remove(secInd);
            long val = entry.getKey();
            if (set.size() <= 1) {
                multiple.remove(val);
                if (set.size() == 1) {
                    single.put(val, set);
                }
            }
            addValue(val<<1L, secInd, multiple, single);
        }

        return getFinalArray(single);
    }

    private void addValue(long val, int index, TreeMap<Long, TreeSet<Integer>> multiple,
                          TreeMap<Long, TreeSet<Integer>> single) {
        if (multiple.containsKey(val)) {
            TreeSet<Integer> set = multiple.get(val);
            set.add(index);
        } else if (single.containsKey(val)) {
            TreeSet<Integer> set = single.get(val);
            set.add(index);
            multiple.put(val, set);
            single.remove(val);
        } else {
            TreeSet<Integer> set = new TreeSet<>();
            set.add(index);
            single.put(val, set);
        }
    }

    private Long[] getFinalArray(TreeMap<Long, TreeSet<Integer>> map) {
        return map.entrySet().stream().flatMap(e -> e.getValue().stream().map(f -> Pair.of(f, e.getKey())))
                .sorted(this::compare).map(Pair::getRight).toArray(Long[]::new);
    }

    private int compare(Pair<Integer, Long> a, Pair<Integer, Long> b) {
        return a.getLeft().compareTo(b.getLeft());
    }
}
