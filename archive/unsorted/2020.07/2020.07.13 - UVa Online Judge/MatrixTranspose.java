package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MatrixTranspose {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (!in.isExhausted()) {
            int m = in.nextInt();
            int n = in.nextInt();
            List<Element> list = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                int r = in.nextInt();
                int[] c = in.nextIntArray(r);
                int[] v = in.nextIntArray(r);
                for (int j = 0; j < r; j++) {
                    list.add(new Element(i, c[j]-1, v[j]));
                }
            }
            list = list.stream().map(ele -> new Element(ele.col, ele.row, ele.value)).collect(Collectors.toList());
            Map<Integer, List<Element>> mp = new HashMap<>();
            for (Element el : list) {
                List<Element> cols = Optional.ofNullable(mp.get(el.row)).orElseGet(ArrayList::new);
                cols.add(el);
                mp.put(el.row, cols);
            }
            ut.printDebug(mp, "mp");
            out.println(n + " " + m);
            for (int i = 0; i < n; i++) {
                List<Element> cols = Optional.ofNullable(mp.get(i)).orElseGet(ArrayList::new);
                int r = cols.size();
                out.print(r);
                for (Element col : cols) {
                    out.print(" " + (col.col+1));
                }
                out.println();
                out.println(cols.stream().map(cl -> Integer.toString(cl.value)).collect(Collectors.joining(" ")));
//                for (Element col : cols) {
//                    out.print(col.value + " ");
//                }
//                out.println();
            }
        }
    }

    private class Element {
        public int row;
        public int col;
        public int value;

        public Element(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "row=" + row +
                    ", col=" + col +
                    ", value=" + value +
                    '}';
        }
    }
}
