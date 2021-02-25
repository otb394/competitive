package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class AdaKing {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int k = in.nextInt();
        boolean[][] chess = new boolean[8][8];
        int ob = 64 - k;
        boolean done = false;
        for (int i = 7; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                if (ob > 0) {
                    chess[i][j] = true;
                    ob--;
                } else {
                    done = true;
                    break;
                }
            }
            if (done) {
                break;
            }
        }
        String[] ans = new String[8];
        for (int i = 0; i < 8; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < 8; j++) {
                builder.append(chess[i][j] ? 'X' : '.');
            }
            if (i == 0) {
                builder.setCharAt(0, 'O');
            }
            ans[i] = builder.toString();
        }
        for (String s : ans) {
            out.println(s);
        }
        out.println();
    }
}
