import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.Set;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.List;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.Comparator;
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
        GHC21PracticeProblem solver = new GHC21PracticeProblem();
        solver.solve(1, in, out);
        out.close();
    }

    static class GHC21PracticeProblem {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int m = in.nextInt();
            int nt = in.nextInt();
            int nh = in.nextInt();
            int nf = in.nextInt();
            List<GHC21PracticeProblem.Pizza> pizzas = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                int ni = in.nextInt();
                GHC21PracticeProblem.Pizza pizza = new GHC21PracticeProblem.Pizza();
                pizza.id = i;
                for (int j = 0; j < ni; j++) {
                    String ing = in.nextString();
                    pizza.ingredients.add(ing);
                }
                pizzas.add(pizza);
            }
            pizzas.sort(Comparator.comparingInt((GHC21PracticeProblem.Pizza p) -> p.ingredients.size()).reversed());
            List<GHC21PracticeProblem.Delivery> deliveries = new ArrayList<>();
            int pi = 0;
            for (int i = 0; i < nf && (pi <= (m - 4)); i++) {
                GHC21PracticeProblem.Delivery delivery = new GHC21PracticeProblem.Delivery();
                delivery.people = 4;
                for (int j = 0; j < 4; j++) {
                    delivery.pizzas.add(pizzas.get(pi));
                    pi++;
                }
                deliveries.add(delivery);
            }
            for (int i = 0; i < nh && (pi <= (m - 3)); i++) {
                GHC21PracticeProblem.Delivery delivery = new GHC21PracticeProblem.Delivery();
                delivery.people = 3;
                for (int j = 0; j < 3; j++) {
                    delivery.pizzas.add(pizzas.get(pi));
                    pi++;
                }
                deliveries.add(delivery);
            }
            for (int i = 0; i < nt && (pi <= (m - 2)); i++) {
                GHC21PracticeProblem.Delivery delivery = new GHC21PracticeProblem.Delivery();
                delivery.people = 2;
                for (int j = 0; j < 2; j++) {
                    delivery.pizzas.add(pizzas.get(pi));
                    pi++;
                }
                deliveries.add(delivery);
            }

            out.println(deliveries.size());
            for (GHC21PracticeProblem.Delivery delivery : deliveries) {
                out.print(delivery.people);
                for (GHC21PracticeProblem.Pizza pizza : delivery.pizzas) {
                    out.printf(" %d", pizza.id);
                }
                out.println();
            }
        }

        private static class Pizza {
            public int id;
            public Set<String> ingredients;

            public Pizza() {
                this.ingredients = new TreeSet<>();
            }

        }

        private static class Delivery {
            public int people;
            public List<GHC21PracticeProblem.Pizza> pizzas;

            public Delivery() {
                this.pizzas = new ArrayList<>();
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

        public void println() {
            writer.println();
        }

        public void printf(String format, Object... objects) {
            writer.printf(format, objects);
        }

        public void close() {
            writer.close();
        }

        public void print(int i) {
            writer.print(i);
        }

        public void println(int i) {
            writer.println(i);
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

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }
}

