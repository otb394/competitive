package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import util.MathUtility;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class RobotPathDecoding {
    private static final int MOD = 1000000000;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String input = in.nextString();
        Map<Integer,Integer> getClosing = new HashMap<>();

        int len = input.length();
        Stack<Integer> openBrackInd = new Stack<>();
        for (int i = 0; i < len; i++) {
            if (input.charAt(i) == '(') {
                openBrackInd.push(i);
            } else if (input.charAt(i) == ')') {
                int latestOpenBrack = openBrackInd.pop();
                getClosing.put(latestOpenBrack, i);
            }
        }

        Pair<Integer, Integer> curr = Pair.of(0,0);
        Pair<Integer, Integer> delta = getDelta(input, 0, input.length()-1, getClosing);
        Pair<Integer, Integer> ans = apply(curr, delta);
        out.println(String.format("Case #%d: %d %d", testNumber, ans.getRight()+1, ans.getLeft()+1));
    }

    private Pair<Integer, Integer> getDelta(String s, int l, int r, Map<Integer,Integer> getClosing) {
        int delX = 0;
        int delY = 0;
        int i =0;
        int len = s.length();
        while (i < len) {
            char c = s.charAt(i);
            if (c >= '2' && c <= '9') {
                int count = c-'0';
                i++;
                int closing = getClosing.get(i+l);
                Pair<Integer,Integer> subdel = getDelta(s.substring(i+1, closing-l), i+l+1, closing-1, getClosing);
                Pair<Integer,Integer> tsubdel = apply(subdel, count);
                delX = MathUtility.add(delX, tsubdel.getLeft(), MOD);
                delY = MathUtility.add(delY, tsubdel.getRight(), MOD);
                /*
                int xDel = tsubdel.getLeft();
                int yDel = tsubdel.getRight();
                if (xDel >= 0) {
                    delX = MathUtility.add(delX, xDel, MOD);
                } else {
                    delX = MathUtility.sub(delX, Math.abs(xDel), MOD);
                }

                if (yDel >= 0) {
                    delY = MathUtility.add(delY, yDel, MOD);
                } else {
                    delY = MathUtility.sub(delY, Math.abs(yDel), MOD);
                }
                */
                i = closing-l+1;
            } else {
                if (c == 'N') {
                    delX = MathUtility.sub(delX, 1, MOD);
                } else if (c == 'S') {
                    delX = MathUtility.add(delX, 1, MOD);
                } else if (c == 'W') {
                    delY = MathUtility.sub(delY, 1, MOD);
                } else if (c=='E') {
                    delY = MathUtility.add(delY, 1, MOD);
                } else {
                    throw new RuntimeException("Something");
                }
                i++;
            }
        }
        return Pair.of(delX, delY);
    }

    private Pair<Integer,Integer> apply(Pair<Integer,Integer> curr, Pair<Integer,Integer> delta) {
        int finalX = MathUtility.add(curr.getLeft(), delta.getLeft(), MOD);
        int finalY = MathUtility.add(curr.getRight(), delta.getRight(), MOD);
        return Pair.of(finalX, finalY);
    }

    private Pair<Integer,Integer> apply(Pair<Integer,Integer> del, int count) {
        return Pair.of(MathUtility.mult(del.getLeft(), count, MOD), MathUtility.mult(del.getRight(), count, MOD));
    }
}
