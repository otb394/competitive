package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.time.LocalDate;

public class Y3KProblem {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (true) {
            int n = in.nextInt();
            int d = in.nextInt();
            int m = in.nextInt();
            int y = in.nextInt();
            if (n == 0 && d == 0 && m == 0 && y == 0) {
                break;
            }
            LocalDate date = LocalDate.of(y, m, d);
            date = date.plusDays(n);
            out.printf("%d %d %d\n", date.getDayOfMonth(), date.getMonth().getValue(), date.getYear());
        }
    }
}
