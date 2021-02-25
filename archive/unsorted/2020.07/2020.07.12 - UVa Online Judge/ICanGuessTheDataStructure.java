package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class ICanGuessTheDataStructure {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (!in.isExhausted()) {
            int n = in.nextInt();
            ut.printDebug(n, "n");
            boolean[] poss = new boolean[3];
            Arrays.fill(poss, true);
            Stack<Long> st = new Stack<>();
            Queue<Long> q = new LinkedList<>();
            PriorityQueue<Long> pq = new PriorityQueue<>(Comparator.reverseOrder());
            int rem = 3;
            boolean done = false;
            List<Pair<Integer, Long>> inputs = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int c = in.nextInt();
                long xx = in.nextLong();
                inputs.add(Pair.of(c, xx));
            }
            for (int i = 0; i < n; i++) {
                int c = inputs.get(i).getLeft();
                long x = inputs.get(i).getRight();
                if (c == 1) {
                    if (poss[0]) {
                        st.add(x);
                    }
                    if (poss[1]) {
                        q.add(x);
                    }
                    if (poss[2]) {
                        pq.add(x);
                    }
                } else {
                    try {
                        if (poss[0]) {
                            long y = st.pop();
                            if (x != y) {
                                poss[0] = false;
                                rem--;
                            }
                        }
                        if (poss[1]) {
                            long y = q.poll();
                            if (x != y) {
                                poss[1] = false;
                                rem--;
                            }
                        }
                        if (poss[2]) {
                            long y = pq.poll();
                            if (x != y) {
                                poss[2] = false;
                                rem--;
                            }
                        }
                        if (rem == 0) {
                            out.println("impossible");
                            done = true;
                            break;
                        }
                    } catch (Exception e) {
                        out.println("impossible");
                        done = true;
                        break;
                    }
                }
            }
            if (done) {
                continue;
            }
            if (rem > 1) {
                out.println("not sure");
            } else if (rem == 1) {
                if (poss[0]) {
                    out.println("stack");
                } else if (poss[1]) {
                    out.println("queue");
                } else if (poss[2]) {
                    out.println("priority queue");
                } else {
                    throw new IllegalStateException();
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }
}
