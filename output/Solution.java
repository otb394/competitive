import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Objects;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author out_of_the_box
 */
public class Solution {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        Candies solver = new Candies();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class Candies {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int q = in.nextInt();
            int[] a = in.nextIntArray(n);

            int s = (int) Math.ceil(Math.sqrt(n));
            int nb = (int) Math.ceil(((double) n) / ((double) s));

            long[] n1 = new long[nb];
            long[] n2 = new long[nb];

            for (int i = 0; i < nb; i++) {
                Pair<Integer, Integer> range = getRange(i, s, n);
                int l = range.getLeft();
                int r = range.getRight();
                int c1 = 1;
                long c2 = 1L;
                long t1 = 0L;
                long t2 = 0L;
                for (int j = l; j <= r; j++) {
                    t1 += c1 * a[j];
                    t2 += c1 * c2 * a[j];
                    c1 *= -1;
                    c2++;
                }
                n1[i] = t1;
                n2[i] = t2;
            }

            long ans = 0L;
            for (int tt = 0; tt < q; tt++) {
                char c = in.nextCharacter();
                if (c == 'U') {
                    int x = in.nextInt();
                    x--;
                    int v = in.nextInt();
                    int bind = getBIndex(x, s, n);
                    Pair<Integer, Integer> ran = getRange(bind, s, n);
                    int l = ran.getLeft();
                    int c1 = ((x - l) % 2 == 0) ? (1) : (-1);
                    long c2 = x - l + 1L;
                    n1[bind] += c1 * (v - a[x]);
                    n2[bind] += c2 * c1 * (v - a[x]);
                    a[x] = v;
                } else if (c == 'Q') {
                    int l = in.nextInt();
                    l--;
                    int r = in.nextInt();
                    r--;
                    int lbind = getBIndex(l, s, n);
                    int rbind = getBIndex(r, s, n);
                    long bans = 0L;
                    if (lbind == rbind) {
                        int c1 = 1;
                        long c2 = 1L;
                        for (int i = l; i <= r; i++) {
                            bans += c2 * c1 * a[i];
                            c1 *= -1L;
                            c2++;
                        }
                    } else {
                        int cl = (l == lbind * s) ? (lbind) : (lbind + 1);
                        int cr = (r == (((rbind + 1) * s) - 1)) ? (rbind) : (rbind - 1);
                        for (int i = cl; i <= cr; i++) {
                            Pair<Integer, Integer> bran = getRange(i, s, n);
                            int bl = bran.getLeft();
                            int rank = bl - l;
                            bans += getN2(n2, i, rank, n1);
                        }
                        if (cl != lbind) {
                            int c1 = 1;
                            long c2 = 1L;
                            int cll = getRange(cl, s, n).getLeft();
                            for (int i = l; i < cll; i++) {
                                bans += c2 * c1 * a[i];
                                c1 *= -1;
                                c2++;
                            }
                        }
                        if (cr != rbind) {
                            int f = getRange(cr, s, n).getRight() + 1;
                            int c1 = ((f - l) % 2 == 0) ? (1) : (-1);
                            long c2 = f - l + 1L;
                            for (int i = f; i <= r; i++) {
                                bans += c2 * c1 * a[i];
                                c1 *= -1;
                                c2++;
                            }
                        }
                    }
                    ans += bans;
                }
            }

            out.println(String.format("Case #%d: %d", testNumber, ans));
        }

        private long getN2(long[] n2, int bind, long rank, long[] n1) {
            if (rank % 2L == 0L) {
                return (n2[bind] + rank * n1[bind]);
            } else {
                return -1L * (n2[bind] + rank * n1[bind]);
            }
        }

        private Pair<Integer, Integer> getRange(int bind, int s, int n) {
            return Pair.of(bind * s, Math.min((bind + 1) * s, n) - 1);
        }

        private int getBIndex(int index, int s, int n) {
            return index / s;
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

        public char nextCharacter() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            return (char) c;
        }

        public String next() {
            return nextString();
        }

        public int[] nextIntArray(int n) {
            int[] array = new int[n];
            for (int i = 0; i < n; ++i) array[i] = nextInt();
            return array;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

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

        public String toString() {
            return "Pair{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(left, pair.left) &&
                    Objects.equals(right, pair.right);
        }

        public int hashCode() {
            return Objects.hash(left, right);
        }

    }
}

