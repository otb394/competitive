package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import algorithms.BinarySearch;
import datastructure.Pair;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class GuessingGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int q = 120;
        List<Range> rangeList = new ArrayList<>();
        rangeList.add(new Range(1, n));
        Ranges ranges = new Ranges(rangeList, n);
        while (q > 0) {
//            MiscUtility.printDebug(ranges, "ranges");
            if (ranges.total <= q) {
                for (int i = 0; i < ranges.total; i++) {
                    int value = getValue(ranges, i);
//                    if (q == 0) throw new RuntimeException("Queries are exhausted");
                    Response res = query(value, in, out);
                    q--;
                    if (res == Response.EQUAL) return;
                }
                throw new RuntimeException("Answer not found");
            } else {
                Pair<Integer, Integer> midValues = getMidVals(ranges);
                int a = midValues.getLeft();
                int b = midValues.getRight();
                Response reso = query(a, in, out);
                q--;
                if (reso == Response.EQUAL) return;
                Response rest = query(b, in, out);
                q--;
                if (rest == Response.EQUAL) return;
                int end = ranges.getEnd();
                int start = ranges.getStart();
                if (reso == Response.LESSER && rest == Response.LESSER) {
                    ranges.remove(b, end);
                } else if (reso == Response.GREATER && rest == Response.GREATER) {
                    ranges.remove(start, a);
                } else if (reso == Response.LESSER && rest == Response.GREATER) {
                    ranges.remove(a, b);
                } else {
                    Response so = query(b, in ,out);
                    q--;
                    if (so == Response.EQUAL) return;
                    if (so == Response.LESSER) {
                        ranges.remove(b, end);
                    } else {
                        Response st = query(a, in, out);
                        q--;
                        if (st == Response.EQUAL) return;
                        if (st == Response.LESSER) {
                            ranges.remove(a, b);
                        } else {
                            ranges.remove(start, a);
                        }
                    }
                }
            }
        }
        MiscUtility.assertion(ranges.total == 1, String.format("Total [%d] is not 1", ranges.total));
        query(ranges.getStart(), in, out);
//        int x = getFirstTask(n, in, out);
    }

    private Pair<Integer,Integer> getMidVals(Ranges ranges) {
        Pair<Integer,Integer> midIndexes = getMidInds(ranges.total);
        return Pair.of(getValue(ranges, midIndexes.getLeft()), getValue(ranges, midIndexes.getRight()));
    }

    private int getValue(Ranges ranges, int index) {
        int sum = 0;
        for (Range range : ranges.rangeList) {
            int count = range.getCount();
            if (count + sum > index) {
                int rId = index-sum;
                return range.l + rId;
            }
            sum+=count;
        }
        throw new RuntimeException(String.format("Ranges[%s], index [%s]", ranges, index));
    }

    private Pair<Integer,Integer> getMidInds(int total) {
        if (total <= 4) {
            throw new RuntimeException(String.format("Total [%s] is not large enough", total));
        }
        int s = (total-2)/3;
        int rem = (total - 2) % 3;
        if (rem == 0) {
            return Pair.of(s, 2*s+1);
        } else if (rem == 1) {
            return Pair.of(s+1, 2*s+2);
        } else {
            return Pair.of(s+1, 2*s+3);
        }
//        int s = total/3;
//        return Pair.of(s, 2*s);
    }

    private int getFirstTask(int n, Reference<Integer> q, InputReader in, OutputWriter out) {
        int x = getFirstTaskEven(n, q, in, out);
        if (x == -1) {
            x = getFirstTaskOdd(n, q, in, out);
        }
//        if (x == -1) throw new RuntimeException("Algorithm failed");
        return x;
    }

    private int getFirstTaskOdd(int n, Reference<Integer> q, InputReader in, OutputWriter out) {
        Function<Integer, Boolean> func = y -> {
            Response res = query(y, in, out);
            q.update(q.value-1);
            if (res == Response.EQUAL) throw new AnswerFound(y);
//            if (res == Response.EQUAL) return y;
            Response tres = query(y, in, out);
            q.update(q.value-1);
            if (tres == Response.EQUAL) throw new AnswerFound(y);
//            if (tres == Response.EQUAL) return y;
            return res == Response.LESSER;
        };
        try {
            int x = BinarySearch.searchLastZero(1, n + 1, func);
            query(x, in, out);
            q.update(q.value-1);
            if (query(x, in, out) == Response.EQUAL) {
                q.update(q.value-1);
                return x;
            } else {
                return -1;
            }
        } catch (AnswerFound e) {
            return e.answer;
        }
    }

    private int getFirstTaskEven(int n, Reference<Integer> q, InputReader in, OutputWriter out) {
        Function<Integer, Boolean> func = y -> {
//            MiscUtility.printDebug(y, "y first");
            Response tres = query(y, in, out);
            q.update(q.value-1);
            if (tres == Response.EQUAL) throw new AnswerFound(y);
//            if (tres == Response.EQUAL) return y;
//            MiscUtility.printDebug(y, "y second");
            Response res = query(y, in, out);
            q.update(q.value-1);
            if (res == Response.EQUAL) throw new AnswerFound(y);
//            if (res == Response.EQUAL) return y;
            return res == Response.LESSER;
        };
        try {
            int x = BinarySearch.searchLastZero(1, n + 1, func);
            query(x, in, out);
            q.update(q.value-1);
            if (query(x, in, out) == Response.EQUAL) {
                q.update(q.value-1);
                return x;
            } else {
                return -1;
            }
        } catch (AnswerFound e) {
            return e.answer;
        }
    }

    private Response query(int y, InputReader in, OutputWriter out) {
        out.println(y);
        out.flush();

        char response = in.nextCharacter();
        return Response.from(response);
    }

    private enum Response {
        LESSER, GREATER, EQUAL;

        public static Response from(char ch) {
            if (ch == 'G') return GREATER;
            if (ch == 'L') return LESSER;
            if (ch == 'E') return EQUAL;
            throw new RuntimeException("Unexpected input");
        }
    }

    private class AnswerFound extends RuntimeException {
        public int answer;

        public AnswerFound(int answer) {
            this.answer = answer;
        }
    }

    private class Range {
        public int l;
        public int r;

        public Range(int l, int r) {
            this.l = l;
            this.r = r;
        }

        public int getCount() {
            return r-l+1;
        }

        @Override
        public String toString() {
            return "Range[" + l + "," + r + "]";
        }
    }

    private class Ranges {
        public List<Range> rangeList;
        public int total;
        public int rangeCount;

        public Ranges(List<Range> rangeList, int total) {
            this.rangeList = rangeList;
            this.total = total;
            this.rangeCount = rangeList.size();
        }

        public void remove(int l, int r) {
            List<Range> newRangeList = new ArrayList<>();
            int newTotal = 0;
            for (Range range : rangeList) {
                if (range.r < l || range.l > r) {
                    newRangeList.add(range);
                    newTotal+= range.getCount();
                } else {
                    int ol = Math.max(l, range.l);
                    int or = Math.min(r, range.r);
                    if (range.l < ol) {
                        Range newR = new Range(range.l, ol-1);
                        newRangeList.add(newR);
                        newTotal += newR.getCount();
                    }
                    if (range.r > or) {
                        Range newR = new Range(or+1, range.r);
                        newRangeList.add(newR);
                        newTotal += newR.getCount();
                    }
                }
            }
            this.rangeList = newRangeList;
            this.total = newTotal;
            this.rangeCount = newRangeList.size();
        }

        public int getStart() {
            return rangeList.get(0).l;
        }

        public int getEnd() {
            return rangeList.get(rangeCount-1).r;
        }

        @Override
        public String toString() {
            return "Ranges{" +
                    "rangeList=" + rangeList +
                    ", total=" + total +
                    '}';
        }
    }

    private class Reference<T> {
        public T value;

        public void update(T value) {
            this.value = value;
        }

        public Reference(T value) {
            this.value = value;
        }
    }
}
