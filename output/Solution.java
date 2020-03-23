import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.HashMap;
import java.util.function.Supplier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.stream.LongStream;
import java.io.BufferedWriter;
import java.util.Collection;
import java.util.Set;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import java.io.Writer;
import java.util.Optional;
import java.util.Queue;
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
        Bundling solver = new Bundling();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class Bundling {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            List<String> strings = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                strings.add(in.nextString());
            }
            long ans = solve(n, k, strings);
            out.println("Case #" + testNumber + ": " + ans);
        }

        private long solve(int n, int k, List<String> strings) {
            Trie<Bundling.Vertex, Character> trie = new Trie<>(Bundling.Vertex::new);

            for (String s : strings) {
                int len = s.length();
                Bundling.Vertex current = trie.getRoot();
                for (int i = 0; i < len; i++) {
                    Bundling.Vertex nd = Optional.ofNullable(current.get(s.charAt(i))).orElseGet(Bundling.Vertex::new);
                    nd.count++;
                    current.put(s.charAt(i), nd);
                    current = nd;
                }
            }
            return trie.stream().mapToLong(v -> v.count / k).sum();
        }

        public static class Vertex extends Trie.Node<Bundling.Vertex, Character> {
            public int count;

            public Vertex() {
                super();
                this.count = 0;
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

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }

    static class Trie<V extends Trie.Node<V, E>, E> {
        private V root;
        private Supplier<V> nodeSupplier;

        public Trie(Supplier<V> nodeSupplier) {
            this.nodeSupplier = nodeSupplier;
            this.root = nodeSupplier.get();
        }

        public V getRoot() {
            return root;
        }

        public Stream<V> stream() {
            Queue<V> queue = new LinkedList<>();
            Set<V> discovered = new HashSet<>();
            discovered.add(root);
            queue.add(root);
            List<V> ret = new ArrayList<>();
            while (!queue.isEmpty()) {
                V element = queue.poll();
                ret.add(element);
                for (V child : element.nextMap.values()) {
                    if (!discovered.contains(child)) {
                        discovered.add(child);
                        queue.add(child);
                    }
                }
            }
            return ret.stream();
        }

        public static class Node<V, E> {
            public Map<E, V> nextMap;

            public Node() {
                this.nextMap = new HashMap<>();
            }

            public void put(E edge, V node) {
                nextMap.put(edge, node);
            }

            public V get(E edge) {
                return nextMap.get(edge);
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
}

