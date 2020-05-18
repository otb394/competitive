package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class DAntiSudoku {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        char[][] map = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                map[i][j] = in.nextCharacter();
            }
        }
        map[0][0] = map[0][1];
        map[1][3] = map[1][4];
        map[2][6] = map[2][7];
        map[3][1] = map[3][2];
        map[4][4] = map[4][5];
        map[5][7] = map[5][8];
        map[6][2] = map[6][1];
        map[7][5] = map[7][4];
        map[8][8] = map[8][7];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                out.print(map[i][j]);
            }
            out.println();
        }
    }
}
