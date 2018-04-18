import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.StringTokenizer;

public class GCJ2018A {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int T = in.nextInt();
            for(int i = 1; i <= T; i++) {
                long d = in.nextLong();
                String p = in.next();
                Optional<Integer> ans = solve(d, p);
                if (ans.isPresent()) {
                    out.println("Case #" + i + ": " + ans.get());
                } else {
                    out.println("Case #" + i + ": IMPOSSIBLE");
                }
            }
        }

        private Optional<Integer> solve(long d, String p) {
            long[] a = getA(p);
            int n = p.length();
//            System.out.println("n = " + n);
//            for (int i = 0; i < n; i++) {
//                System.out.println(a[i]);
//            }
            long current = 0L;
            for (int i = 0; i < n; i++) {
                if (p.charAt(i) == 'S') {
                    current += a[i];
                }
            }
            return executeLoop(d, p, a, current);
        }

        private long[] getA(String p) {
            int n = p.length();
            long[] ret = new long[n];
            ret[0] = 1;
            for (int i = 1; i < n; i++) {
                if (p.charAt(i-1) == 'C') {
                    ret[i] = ret[i-1]<<1L;
                } else {
                    ret[i] = ret[i-1];
                }
            }
            return ret;
        }

        private Optional<Integer> executeLoop(long d, String p, long[] a, long c) {
            boolean done = false;
            int n = p.length();
            long current = c;
            int actions = 0;
            char[] tempp = p.toCharArray();
            while(current>d && !done) {
//                System.out.println("current = " + current);
//                System.out.println("d = " + d);
                done = true;
                for (int i = n-2; i>=0; i--) {
                    if (tempp[i] == 'C' && tempp[i+1] == 'S') {
                        done = false;
                        tempp[i] = 'S';
                        tempp[i+1] = 'C';
                        current -= a[i];
                        a[i+1] = a[i+1]>>1L;
                        actions++;
                    }
                }
            }
            if (current > d) {
                return Optional.empty();
            } else {
                return Optional.of(actions);
            }
        }
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
