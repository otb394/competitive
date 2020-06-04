package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import algorithms.BinarySearch;
import util.MiscUtility;

import java.util.Random;
import java.util.function.Function;

public class GFindAGift {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int k = in.nextInt();

            boolean done = false;
            for (int j = 0; j < 30; j++) {
                int rand = getRandomIndex(n);
                Response res = query(1, rand, out, in);
                if (res == Response.SECOND) {
                    out.println("! 1");
                    out.flush();
                    done = true;
                    break;
                }
            }
            if (done) {
                continue;
            }

            int r = 1;
            int st = n-k;
            while (r < st) {
                int dr = r<<1;
                if (dr >= n) {
                    break;
                }
                Response res = query(1, r, r+1, dr, out, in);
                if (res != Response.EQUAL) {
                    break;
                }
                r = dr;
            }

            int finalR = r;
            Function<Integer, Boolean> func = ind -> !(query(finalR +1, ind, 1, ind-finalR, out, in) == Response.EQUAL);
            int end = Math.min(2*r+1, n+1);
            int ans = BinarySearch.searchFirstOne(r+1, end, func);
            MiscUtility.assertion(ans != end);
            out.println("! " + ans);
            out.flush();
        }
    }

    private Response query(int fi, int fj, int si, int sj, OutputWriter out, InputReader in) {
        int sf = fj-fi+1;
        int ss = sj-si+1;
        out.println(String.format("? %d %d", sf, ss));
        out.flush();
        for (int i = fi; i <= fj; i++) {
            out.print(i + " ");
        }
        out.println();
        out.flush();
        for (int i = si; i <= sj; i++) {
            out.print(i + " ");
        }
        out.println();
        out.flush();

        String res = in.nextString();
        return Response.valueOf(res);
    }

    private Response query(int i, int j, OutputWriter out, InputReader in) {
        out.println("? 1 1");
        out.flush();
        out.println(i);
        out.flush();
        out.println(j);
        out.flush();

        String res = in.nextString();
        return Response.valueOf(res);
    }

    private enum Response {
        FIRST, SECOND, EQUAL, WASTED
    }

    private int getRandomIndex(int n) {
        if (n == 2) return 2;
        return new Random().nextInt(n-2) + 2;
    }
}
