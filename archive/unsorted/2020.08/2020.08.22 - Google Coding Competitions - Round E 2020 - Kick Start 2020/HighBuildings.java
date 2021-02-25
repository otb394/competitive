package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class HighBuildings {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int[] ans = new int[n];
        if (n == 1) {
            out.printf("Case #%d: 1\n", testNumber);
            return;
        }
        if (n == 2) {
            if (a == 1 && b == 1) {
                out.printf("Case #%d: IMPOSSIBLE\n", testNumber);
            } else if (a == 2 && b == 1) {
                out.printf("Case #%d: 1 2\n", testNumber);
            } else if (a == 1 && b == 2) {
                out.printf("Case #%d: 2 1\n", testNumber);
            } else {
                if (c == 2) {
                    out.printf("Case #%d: 2 2\n", testNumber);
                } else {
                    out.printf("Case #%d: IMPOSSIBLE\n", testNumber);
                }
            }
            return;
        }
        if (c == 1) {
            if (a == 1 && b == 1) {
                out.printf("Case #%d: IMPOSSIBLE\n", testNumber);
                return;
            } else {
                int ex = n - (a+b-c);
                if (ex < 0) {
                    out.printf("Case #%d: IMPOSSIBLE\n", testNumber);
                    return;
                }
                if (a > c) {
                    int z = 0;
                    for (int i = 0; i < (a - c); i++) {
                        ans[z] = 2;
                        z++;
                    }
                    for (int i = 0; i < ex; i++) {
                        ans[z] = 1;
                        z++;
                    }
                    for (int i = 0; i < c; i++) {
                        ans[z] = 3;
                        z++;
                    }
                    for (int i = 0; i < (b - c); i++) {
                        ans[z] = 2;
                        z++;
                    }
                } else {
                    int z = 0;
                    for (int i = 0; i < (a - c); i++) {
                        ans[z] = 2;
                        z++;
                    }
                    for (int i = 0; i < c; i++) {
                        ans[z] = 3;
                        z++;
                    }
                    for (int i = 0; i < ex; i++) {
                        ans[z] = 1;
                        z++;
                    }
                    for (int i = 0; i < (b - c); i++) {
                        ans[z] = 2;
                        z++;
                    }
                }
                out.printf("Case #%d: ", testNumber);
                for (int i = 0; i < n; i++) {
                    out.print(ans[i] + " ");
                }
                out.println();
            }
        } else {
            if ((a + b - c) <= n) {
                int ex = n - (a+b-c);
                int z = 0;
                for (int i = 0; i < (a-c); i++) {
                    ans[z] = 2;
                    z++;
                }
                ans[z] = 3;
                z++;
                for (int i = 0; i < ex; i++) {
                    ans[z] = 1;
                    z++;
                }
                for (int i = 0; i < (c-1); i++) {
                    ans[z] = 3;
                    z++;
                }
                for (int i = 0; i < (b-c); i++) {
                    ans[z] = 2;
                    z++;
                }
                out.printf("Case #%d: ", testNumber);
                for (int i = 0; i < n; i++) {
                    out.print(ans[i] + " ");
                }
                out.println();
            } else {
                out.printf("Case #%d: IMPOSSIBLE\n", testNumber);
                return;
            }
        }
    }
}
