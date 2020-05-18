package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;

public class OverexcitedFan {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x=in.nextInt();
        int y=in.nextInt();
        String m = in.nextString();
        int len = m.length();
        Pair<Integer, Integer> curr = Pair.of(x,y);

        int time;
        for (time = 1; time <= len; time++) {
            curr = getNext(curr, m.charAt(time-1));
            if (canReach(curr.getLeft(), curr.getRight(), time)) {
                break;
            }
        }
        if (time == (len+1)) {
            out.println(String.format("Case #%d: IMPOSSIBLE", testNumber));
        } else {
            out.println(String.format("Case #%d: %d", testNumber, time));
        }
    }

    private Pair<Integer,Integer> getNext(Pair<Integer,Integer> curr, char dir) {
        if (dir == 'N') {
            return Pair.of(curr.getLeft(), curr.getRight()+1);
        } else if (dir == 'S') {
            return Pair.of(curr.getLeft(), curr.getRight()-1);
        } else if (dir == 'W') {
            return Pair.of(curr.getLeft()-1, curr.getRight());
        } else {
            return Pair.of(curr.getLeft()+1, curr.getRight());
        }
    }

    private boolean canReach(int i, int j, int time) {
        int dis = Math.abs(i) + Math.abs(j);
        return dis <= time;
    }
}
