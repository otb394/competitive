import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.function.Function;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.Collection;
import java.util.Set;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.List;
import java.util.stream.Stream;
import java.io.Writer;
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
        DGuessTheMaximums solver = new DGuessTheMaximums();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class DGuessTheMaximums {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int maxNodVal;
            List<List<Integer>> subs = new ArrayList<>();
            List<Integer> otherList = new ArrayList<>();
            boolean[] others = new boolean[n + 1];
            for (int i = 0; i < k; i++) {
                int c = in.nextInt();
                int[] tArr = in.nextIntArray(c);
                for (int val : tArr) {
                    others[val] = true;
                }
                subs.add(Arrays.stream(tArr).boxed().collect(Collectors.toList()));
            }
            for (int i = 1; i <= n; i++) {
                if (!others[i]) {
                    otherList.add(i);
                }
            }

            maxNodVal = query(subs.stream().flatMap(List::stream).collect(Collectors.toList()), in, out);
            int mxInd;
            Function<Integer, Boolean> func =
                    ind -> (query(subs.subList(0, ind + 1).stream().flatMap(List::stream)
                            .collect(Collectors.toList()), in, out) == maxNodVal);
            if (k > 1) {
                mxInd = BinarySearch.searchFirstOne(-1, k - 1, func);
            } else {
                mxInd = 0;
            }
            if (mxInd == k) {
                throw new RuntimeException("Unexpected state. max val node is not found in array");
            }
            int[] ans = new int[k];

            if (k != 1) {
                List<Integer> allOtherNodes = IntStream.range(0, k).filter(y -> (y != mxInd)).boxed().map(subs::get)
                        .flatMap(List::stream).collect(Collectors.toList());
                List<Integer> nq = Stream.concat(allOtherNodes.stream(), otherList.stream())
                        .collect(Collectors.toList());
                int otherAns = query(nq, in, out);
                if (otherAns > maxNodVal) {
                    for (int i = 0; i < k; i++) {
                        ans[i] = otherAns;
                    }
                } else {
                    for (int i = 0; i < k; i++) {
                        if (i != mxInd) {
                            ans[i] = maxNodVal;
                        }
                    }
                    ans[mxInd] = otherAns;
                }
            } else {
                int otherAns = query(otherList, in, out);
                ans[0] = otherAns;
            }
            output(ans, in, out);
        }

        private int query(List<Integer> sub, InputReader in, OutputWriter out) {
            Set<Integer> st = new HashSet<>(sub);
            int c = sub.size();
            out.print("? " + c);
            for (int val : sub) {
                out.print(" " + val);
            }
            out.println();
            out.println();
            out.flush();

            int x = in.nextInt();
            if (x == -1) {
                throw new RuntimeException("x == -1 received");
            }

            return x;
        }

        private void output(int[] password, InputReader in, OutputWriter out) {
            out.print("!");
            for (int val : password) {
                out.print(" " + val);
            }
            out.println();
            out.flush();

            String outcome = in.nextString();
            if (outcome.equals("Incorrect")) {
                throw new RuntimeException("Incorrect assertion");
            }
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

        public void println() {
            writer.println();
        }

        public void close() {
            writer.close();
        }

        public void flush() {
            writer.flush();
        }

    }

    static class BinarySearch {
        public static int searchFirstOne(int start, int end, Function<Integer, Boolean> valueFunc) {
            int low = start;
            int high = end;
            while ((high - low) > 1) {
                int mid = low + (high - low) / 2;
                if (valueFunc.apply(mid)) {
                    high = mid;
                } else {
                    low = mid;
                }
            }

            return high;
        }

    }
}

