package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

public class CGoodString {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.nextString();
        int M = 10;
        int[] counts = new int[M];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int ind = get(s.charAt(i));
            counts[ind]++;
        }
        int ans = n-2;
        for (int i = 0; i < M; i++) {
            ans = Math.min(ans, n - counts[i]);
        }
        ut.printDebug(ans, "ans");
        for (int i = 0; i < M; i++) {
            if (counts[i] > 0) {
                for (int j = 0; j < M; j++) {
                    if (i == j) continue;
                    if (counts[j] > 0) {
                        int tans = getAns(s, i, j);
                        if (tans % 2 != 0) {
                            tans--;
                        }
                        ans = Math.min(ans, n - tans);
//                        if (check(s, i, j)) {
//                            ans = Math.min(ans, n - (counts[i] + counts[j]));
//                            ut.printDebug(i, "ti", j, "tj", ans, "ans");
//                        }
                    }
                }
            }
        }
        out.println(ans);
    }

    private int getAns(String s, int p, int q) {
        int i = 0;
        int n = s.length();
        while (i < n) {
            int ind = get(s.charAt(i));
            if (ind != p && ind != q) {
                i++;
            } else {
                break;
            }
        }
        int ret = 0;
        int ind = get(s.charAt(i));
        boolean first;
        if (ind == p) first = false;
        else first = true;
        ret++;
        i++;
        while (i < n) {
            while (i < n) {
                int id = get(s.charAt(i));
                if (first) {
                    if (id != p) {
                        i++;
                    } else {
                        break;
                    }
                } else {
                    if (id != q) {
                        i++;
                    } else {
                        break;
                    }
                }
            }
            if (i < n) {
                ret++;
                i++;
                first = !first;
            }
        }
        ut.printDebug(p, "p", q, "q", ret, "ret", n, "n");
        return ret;
    }

    private boolean check(String s, int p, int q) {
        ut.printDebug(s, "s", p, "p", q, "q");
        boolean first = true;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int ind = get(s.charAt(i));
            if (ind != p && ind != q) continue;
            if (first) {
                if (ind != p) return false;
            } else {
                if (ind != q) return false;
            }
            first = !first;
        }
        return true;
    }

    private int get(char c) {
        return c - '0';
    }
}
