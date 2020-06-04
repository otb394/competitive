package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class AAmusingJoke {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String f = in.nextString();
        String s = in.nextString();
        String totl = f+s;
        List<Character> common = new ArrayList<>();
        int nt = totl.length();
        for (int i = 0; i < nt; i++) {
            common.add(totl.charAt(i));
        }
        common.sort(Character::compareTo);
        String last = in.nextString();
        List<Character> two = new ArrayList<>();
        int nl = last.length();
        for (int i = 0; i < nl; i++) {
            two.add(last.charAt(i));
        }
        two.sort(Character::compareTo);
        if (common.equals(two)) {
            out.print("YES");
        } else {
            out.print("NO");
        }
    }
}
