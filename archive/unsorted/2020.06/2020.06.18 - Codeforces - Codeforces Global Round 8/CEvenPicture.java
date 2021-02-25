package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.List;

public class CEvenPicture {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int baseSeg = n/2;
        int len = baseSeg*4 + 6;
        int baseK = len * 2 + 10 + baseSeg * 4;
        boolean odd = (n%2 != 0);
        if (odd) {
            baseK += 3;
        }
        out.println(baseK);
        int cnt = 0;
        out.println("0 0");
        out.println("0 -1");
        out.println("0 -2");
        cnt += 3;
        int y = -3;
        for (int i = 0; i < baseSeg; i++) {
            addSegment(0, y, out);
            cnt += 8;
            y -= 4;
        }
        for (int i = 0; i < 3; i++) {
            out.println("0 " + y);
            cnt++;
            y--;
        }
        for (int i = 0; i > y; i--) {
            out.println("6 " + i);
            cnt++;
        }
        for (int i = 0; i < 5; i++) {
            out.printf("%d %d\n", i+1, 0);
            cnt++;
        }
        for (int i = 0; i < 5; i++) {
            out.printf("%d %d\n", i+1, y+1);
            cnt++;
        }
        if (odd) {
            out.printf("%d %d\n", 0, 1);
            cnt++;
            out.printf("%d %d\n", -1, 1);
            cnt++;
            out.printf("%d %d\n", -1, 0);
            cnt++;
        }
        MiscUtility.assertion(cnt == baseK, String.format("BaseK [%d] is not cnt [%d]", baseK, cnt));
    }

    private void addSegment(int i, int j, OutputWriter out) {
        out.printf("%d %d\n", i, j);
        out.printf("%d %d\n", i, j-1);
        out.printf("%d %d\n", i, j-2);
        out.printf("%d %d\n", i, j-3);
        out.printf("%d %d\n", i-1, j-1);
        out.printf("%d %d\n", i+1, j-1);
        out.printf("%d %d\n", i-1, j-2);
        out.printf("%d %d\n", i+1, j-2);
    }
}

