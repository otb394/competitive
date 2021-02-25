package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.util.Comparator;
import java.util.PriorityQueue;

public class WhatIsTheMedian {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
//        Long mid = null;
        PriorityQueue<Long> less = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Long> more = new PriorityQueue<>();
        while (true) {
            try {
                long num = in.nextLong();
                if (less.isEmpty()) {
                    less.add(num);
                    out.println(num);
                } else {
                    if (less.size() == more.size()) {
                        long ri = more.peek();
                        if (num <= ri) {
                            less.add(num);
                        } else {
                            more.poll();
                            more.add(num);
                            less.add(ri);
                        }
                    } else {
                        long lf = less.peek();
                        if (num >= lf) {
                            more.add(num);
                        } else {
                            less.poll();
                            more.add(lf);
                            less.add(num);
                        }
                    }
                    if (less.size() == more.size()) {
                        out.println(getAvg(less.peek(), more.peek()));
                    } else {
                        out.println(less.peek());
                    }
                }
                ut.printDebug(less, "less", more, "more", num, "num");
            } catch (Exception e) {
                break;
            }
        }
    }

    private long getAvg(long a, long b) {
        return a + (b-a)/2;
    }
}
