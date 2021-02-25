package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class AJugglingLetters {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            strings.add(in.nextString());
        }
        int[] f = new int[26];
        for (int i = 0; i < n; i++) {
            String s = strings.get(i);
            int len = s.length();
            for (int j = 0; j < len; j++) {
                char ch = s.charAt(j);
                f[ch - 'a']++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (f[i] % n != 0) {
                out.println("NO");
                return;
            }
        }
        out.println("YES");
    }
}
