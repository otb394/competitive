import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author out_of_the_box
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        HighwayCrossing solver = new HighwayCrossing();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class HighwayCrossing {
        private static final double MIN_DIS = 1E-6;

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            long s = in.nextLong();
            long y = in.nextLong();
            long[] v = new long[n];
            int[] d = new int[n];
            long[] p = new long[n];
            long[] c = new long[n];
            for (int i = 0; i < n; i++) {
                v[i] = in.nextLong();
            }
            for (int i = 0; i < n; i++) {
                d[i] = in.nextInt();
            }
            for (int i = 0; i < n; i++) {
                p[i] = in.nextLong();
            }
            for (int i = 0; i < n; i++) {
                c[i] = in.nextLong();
            }
//        out.printf("%.6f\n", solve(n, s, y, v, d, p, c));
            out.println(solve(n, s, y, v, d, p, c));
        }

        private double solve(int n, long s, long y, long[] v, int[] d, long[] p, long[] c) {
            double currT = 0.0;
            double T = ((double) y) / ((double) s);
            for (int i = 0; i < n; i++) {
                double vel = (d[i] == 1) ? (v[i]) : (-v[i]);
                Pair<Double, Double> carTimes = getCarTimes(vel, d[i], p[i], c[i]);
                currT = getCrossTime(currT, carTimes.getLeft(), carTimes.getRight(), T);
            }
            return currT;
        }

        private double getCrossTime(double t, double T1, double T2, double T) {
            double bestCrossTime = t + T;
            if (Double.compare(bestCrossTime, T1) == -1 || Double.compare(t, T2) == 1) {
                return bestCrossTime;
            }
            return T2 + T;
        }

        private Pair<Double, Double> getCarTimes(double v, int d, double p, double c) {
            double ep = MIN_DIS / v;
            double frontEarliestPosToHit, backLatestPosToHit;
            if (d == 1) {
                frontEarliestPosToHit = 0.0 - ep;
                backLatestPosToHit = 0.0 + ep;
            } else {
                frontEarliestPosToHit = 0.0 - ep;
                backLatestPosToHit = 0.0 - ep;
            }
            double b = getBackSidePos(p, c, d);
            double t1 = calcTimeFromPos(p, frontEarliestPosToHit, v);
            double t2 = calcTimeFromPos(b, backLatestPosToHit, v);
            return Pair.of(t1, t2);
        }

        private double calcTimeFromPos(double x1, double x2, double v) {
            return (x2 - x1) / v;
        }

        private double getBackSidePos(double x, double len, int direction) {
            if (direction == 1) {
                return x - len;
            } else {
                return x + len;
            }
        }

    }

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(objects[i]);
            }
        }

        public void println(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }

    }

    static class Pair<L, R> {
        private L left;
        private R right;

        private Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }

        public L getLeft() {
            return left;
        }

        public R getRight() {
            return right;
        }

        public static <A, B> Pair<A, B> of(A a, B b) {
            return new Pair<>(a, b);
        }

    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private InputReader.SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String nextString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                if (Character.isValidCodePoint(c)) {
                    res.appendCodePoint(c);
                }
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return isWhitespace(c);
        }

        public static boolean isWhitespace(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next() {
            return nextString();
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }
}

