package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;

import java.util.ArrayList;
import java.util.List;

public class PaintersDuel {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int s = in.nextInt();
        int ra = in.nextInt();
        int pa = in.nextInt();
        int rb = in.nextInt();
        int pb = in.nextInt();
        int c = in.nextInt();

        List<Pair<Integer, Integer>> prs = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            prs.add(Pair.of(in.nextInt(), in.nextInt()));
        }

        int ans;
        if (s == 2) {
            if (c == 2) {
                ans = 0;
            } else if (c == 1) {
                int ci = prs.get(0).getLeft();
                int cj = prs.get(0).getRight();
                if (ci == 2 && cj == 2) {
                    ans = 0;
                } else {
                    if (isCentre(Pair.of(ra, pa))) {
                        ans = 1;
                    } else if (isCentre(Pair.of(rb, pb))) {
                        ans = -1;
                    } else {
                        ans = 1;
                    }
                }
            } else {
                if (isCentre(Pair.of(ra, pa))) {
                    ans = 1;
                } else if (isCentre(Pair.of(rb, pb))) {
                    ans = -1;
                } else {
                    ans = 2;
                }
            }
        } else {
            //TODO
            ans = 1;
        }
        out.printf("Case #%d: %d\n", testNumber, ans);
    }

    private int getPosIndex(int i, int j) {
        return i*i + j;
    }

    private Pair<Integer, Integer> getPhPos(int index) {
        int r = (int) Math.sqrt(index);
        int c = index - r*r;
        return Pair.of(r, c);
    }

    private int getAns(long mask, int apos, int bpos, int score, boolean aturn, int s) {
        //TODO: Memoize
        //TODO: Recurse

        if (aturn) {
            Pair<Integer, Integer> currPos = getPhPos(apos);
            int ai = currPos.getLeft();
            int aj = currPos.getRight();

            if (aj != 0) {
                int mi = getPosIndex(ai, aj-1);
                if ((mask & (1L << mi)) == 0L) {
//                    int val = getAns(mask | (1L << mi), mi, bpos, )
                }
            }
        } else {

        }
        return 0;
    }

    private boolean isCentre(Pair<Integer, Integer> pindex) {
        return (pindex.getRight() == 2 && pindex.getLeft() == 2);
    }
}
