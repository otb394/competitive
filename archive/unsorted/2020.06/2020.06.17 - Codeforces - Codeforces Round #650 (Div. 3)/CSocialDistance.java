package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CSocialDistance {
    MiscUtility ut;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        ut = new MiscUtility(false);
        int n = in.nextInt();
        int k = in.nextInt();
        String s = in.nextString();
        List<Integer> ones = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ones.add(i);
            }
        }
        int on = ones.size();
        Deque<Integer> deque = new ArrayDeque<>();
        int curr = 0;
        if (on == 0) {
            for (int i = 0; i < n; i++) {
                deque.add(i);
            }
        } else {
            for (int i = 0; i < n; i++) {
                ut.printDebug(i, "i", curr, "curr");
                if (curr == 0) {
                    if (Math.abs(ones.get(curr) - i) > k) {
                        deque.add(i);
                    }
                } else if (curr == on) {
                    ut.printDebug(curr, "curr");
                    if (Math.abs(ones.get(curr - 1) - i) > k) {
                        deque.add(i);
                    }
                } else {
                    if ((Math.abs(ones.get(curr) - i) > k) && (Math.abs(ones.get(curr - 1) - i) > k)) {
                        deque.add(i);
                    }
                }
                if (curr != on && i == ones.get(curr)) curr++;
            }
        }
        int ans = 0;
        while (!deque.isEmpty()) {
            int pos = deque.pollFirst();
            ans++;
            while (!deque.isEmpty() && (Math.abs(deque.peekFirst() - pos) <= k)) deque.pollFirst();
        }
        out.println(ans);
    }

}
