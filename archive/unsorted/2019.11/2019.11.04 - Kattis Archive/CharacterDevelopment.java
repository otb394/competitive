package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class CharacterDevelopment {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextLong();
        long ans = (1L<<n) - n - 1L;
        out.print(ans);
    }
}
