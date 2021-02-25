package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class E1ReadingBooksEasyVersion {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] t = new int[n];
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            t[i] = in.nextInt();
            a[i] = in.nextInt();
            b[i] = in.nextInt();
        }
        int ans = 0;
        List<Integer> alike = new ArrayList<>();
        List<Integer> blike = new ArrayList<>();
        List<Integer> like = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (a[i] == 1 && b[i] == 1) {
                like.add(i);
            } else if (a[i] == 1) {
                alike.add(i);
            } else if (b[i] == 1) {
                blike.add(i);
            }
        }
        alike.sort(Comparator.comparing(i -> t[i]));
        blike.sort(Comparator.comparing(i -> t[i]));
        like.sort(Comparator.comparing(i -> t[i]));
        int ai = 0;
        int bi = 0;
        int ind = 0;
        int an = alike.size();
        int bn = blike.size();
        int cn = like.size();
        ut.printDebug(an, "an", bn, "bn", cn, "cn");
        int arem = k;
        int brem = k;
        while (ai < an && bi < bn && ind < cn && arem > 0 && brem > 0) {
            int at = t[alike.get(ai)];
            int bt = t[blike.get(bi)];
            int ct = t[like.get(ind)];
            if (at + bt < ct) {
                ans += at + bt;
                ai++;
                bi++;
            } else {
                ans += ct;
                ind++;
            }
            arem--;
            brem--;
        }
        if (arem == 0) {
            while (brem > 0 && ind < cn && bi < bn) {
                int bt = t[blike.get(bi)];
                int ct = t[like.get(ind)];
                if (bt < ct) {
                    ans += bt;
                    bi++;
                    brem--;
                } else {
                    ans += ct;
                    ind++;
                    brem--;
                }
            }
            while (brem > 0 && ind < cn) {
                ans += t[like.get(ind)];
                ind++;
                brem--;
            }
            while (brem > 0 && bi < bn) {
                ans += t[blike.get(bi)];
                bi++;
                brem--;
            }
            if (brem != 0) {
                out.println(-1);
                return;
            } else {
                out.println(ans);
                return;
            }
        } else if (brem == 0) {
            while (arem > 0 && ind < cn && ai < an) {
                int at = t[alike.get(ai)];
                int ct = t[like.get(ind)];
                if (at < ct) {
                    ans += at;
                    ai++;
                    arem--;
                } else {
                    ans += ct;
                    ind++;
                    arem--;
                }
            }
            while (arem > 0 && ind < cn) {
                int ct = t[like.get(ind)];
                ans += ct;
                arem--;
                ind++;
            }

            while(arem > 0 && ai < an) {
                int at = t[alike.get(ai)];
                ans += at;
                arem--;
                ai++;
            }

            if (arem != 0) {
                out.println(-1);
                return;
            } else {
                out.println(ans);
                return;
            }
        } else {
            if (ai == an) {
                while (arem > 0 && ind < cn) {
                    int ct = t[like.get(ind)];
                    ans += ct;
                    arem--;
                    brem--;
                    ind++;
                }
                if (arem != 0) {
                    out.println(-1);return;
                }

                while (brem > 0 && ind < cn && bi < bn) {
                    int bt = t[blike.get(bi)];
                    int ct = t[like.get(ind)];
                    if (bt < ct) {
                        ans += bt;
                        bi++;
                        brem--;
                    } else {
                        ans += ct;
                        ind++;
                        brem--;
                    }
                }
                while (brem > 0 && ind < cn) {
                    ans += t[like.get(ind)];
                    ind++;
                    brem--;
                }
                while (brem > 0 && bi < bn) {
                    ans += t[blike.get(bi)];
                    bi++;
                    brem--;
                }
                if (brem != 0) {
                    out.println(-1);
                    return;
                } else {
                    out.println(ans);
                    return;
                }
            } else if (bi == bn) {
                while (brem > 0 && ind < cn) {
                    int ct = t[like.get(ind)];
                    ans += ct;
                    brem--;
                    arem--;
                    ind++;
                }
                if (brem != 0) {
                    out.println(-1);
                    return;
                }

                while (arem > 0 && ind < cn && ai < an) {
                    int at = t[alike.get(ai)];
                    int ct = t[like.get(ind)];
                    if (at < ct) {
                        ans += at;
                        ai++;
                        arem--;
                    } else {
                        ans += ct;
                        ind++;
                        arem--;
                    }
                }
                while (arem > 0 && ind < cn) {
                    int ct = t[like.get(ind)];
                    ans += ct;
                    arem--;
                    ind++;
                }

                while(arem > 0 && ai < an) {
                    int at = t[alike.get(ai)];
                    ans += at;
                    arem--;
                    ai++;
                }

                if (arem != 0) {
                    out.println(-1);
                    return;
                } else {
                    out.println(ans);
                    return;
                }
            } else {
                MiscUtility.assertion(ind == cn, String.format("ind [%d] is not cn [%d]", ind, cn));
                while (arem > 0 && ai < an) {
                    int at = t[alike.get(ai)];
                    ans += at;
                    arem--;
                    ai++;
                }
                if (arem != 0) {
                    out.println(-1);return;
                }
                while (brem > 0 && bi < bn) {
                    int bt = t[blike.get(bi)];
                    ans += bt;
                    brem--;
                    bi++;
                }
                if (brem != 0) {
                    out.println(-1);return;
                }
                out.println(ans);
            }
        }
    }
}
