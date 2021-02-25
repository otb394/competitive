package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class DTaskOnTheBoard {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.nextString();
        int m = in.nextInt();
        int[] b = in.nextIntArray(m);
        List<List<Integer>> ordPos = new ArrayList<>();
        int done = 0;
        boolean[] flag = new boolean[m];
        while (done < m) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                if (!flag[i]) {
                    long val = getVal(ordPos, i);
                    if (val == b[i]) {
                        list.add(i);
                        flag[i] = true;
                        done++;
                    }
                }
            }
            ordPos.add(list);
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < m; i++) {
            builder.append('a');
        }
        int[] cnts = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            cnts[s.charAt(i) - 'a']++;
        }
        int ind = 0;
        for (int i = 25; i >= 0; i--) {
            if (ind >= ordPos.size()) {
                break;
            }
            int len = ordPos.get(ind).size();
            if (len <= cnts[i]) {
                for (int pos : ordPos.get(ind)) {
                    builder.setCharAt(pos, ((char)('a' + i)));
                }
                ind++;
            }
        }
        out.println(builder.toString());
    }

    private long getVal(List<List<Integer>> ordPos, int index) {
        long ans = 0L;
        for (List<Integer> val : ordPos) {
            for (int vval : val) {
                ans += Math.abs(index - vval);
            }
        }
        return ans;
    }
}
