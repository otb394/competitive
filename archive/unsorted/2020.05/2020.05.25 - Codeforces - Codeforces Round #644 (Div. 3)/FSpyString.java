package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FSpyString {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextString();
        }
        if (n == 1) {
            out.println(a[0]);
            return;
        }
        if (m == 1) {
            out.println("a");
            return;
        }
        List<String> arr = Arrays.stream(a).distinct().collect(Collectors.toList());
        n = arr.size();
        if (n==1) {
            out.println(arr.get(0));
            return;
        }

        boolean happy = false;
        List<String> hp = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < m; i++) {
            stringBuilder.append('A');
        }
        for (int i = 0; i < (n-1); i++) {
            for (int j = i+1; j < n; j++) {
                int dis = getManhattan(arr.get(i), arr.get(j));
                if (dis > 2) {
                    out.println("-1");
                    return;
                } else if (dis == 2) {
                    happy = true;
                    String f = arr.get(i);
                    String s = arr.get(j);
                    int len = f.length();
                    for (int k = 0; k < len; k++) {
                        if (f.charAt(k) != s.charAt(k)) {
                            StringBuilder builder = new StringBuilder(f);
                            builder.setCharAt(k, s.charAt(k));
                            hp.add(builder.toString());
                        }
                    }
                    break;
                } else {
                    String f = arr.get(i);
                    String s = arr.get(j);
                    int len = f.length();
                    for (int k = 0; k < len; k++) {
                        if (f.charAt(k) == s.charAt(k)) {
                            char ch = f.charAt(k);
                            char sbch = stringBuilder.charAt(k);
                            if (sbch == 'A') {
                                stringBuilder.setCharAt(k, ch);
                            } else if (sbch !=ch) {
                                out.println("-1");
                                return;
                            }
                        }
                    }
                }
            }
            if (happy) {
                break;
            }
        }
        if (happy) {
            for (String ans : hp) {
                boolean ok = true;
                for (int i = 0; i < n; i++) {
                    String f = arr.get(i);
                    int dis = getManhattan(f, ans);
                    if (dis > 1) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    out.println(ans);
                    return;
                }
            }
            out.println("-1");
        } else {
            for (int i = 0; i < m; i++) {
                if (stringBuilder.charAt(i) == 'A') {
                    stringBuilder.setCharAt(i, 'a');
                }
            }
            String an = stringBuilder.toString();
            out.println(an);
        }
    }

    private int getManhattan(String f, String s) {
        int len = f.length();
        int ret = 0;
        for (int i = 0; i < len; i++) {
            if (f.charAt(i) != s.charAt(i)) {
                ret++;
            }
        }
        return ret;
    }
}
