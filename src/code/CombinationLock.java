package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import datastructure.Tree;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class CombinationLock {
    MiscUtility ut = new MiscUtility(false);
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int w = in.nextInt();
        int n = in.nextInt();
        int[] a = in.nextIntArray(w);
        long ans = Long.MAX_VALUE;
        Map<Integer, List<Pair<Integer, LABEL>>> pts = new HashMap<>();
        Set<Integer> ptsSet = new TreeSet<>();
        for (int i = 0; i < w; i++) {
            List<Pair<Integer, LABEL>> lpts = getPts(a[i], n);
            for (Pair<Integer, LABEL> pr : lpts) {
                List<Pair<Integer, LABEL>> tlist = Optional.ofNullable(pts.get(pr.getLeft())).orElseGet(ArrayList::new);
                tlist.add(Pair.of(i, pr.getRight()));
                ptsSet.add(pr.getLeft());
                pts.put(pr.getLeft(), tlist);
            }
        }

        List<Integer> ptsList = new ArrayList<>(ptsSet);
        ptsList.sort(Integer::compareTo);
        ut.printDebug(ptsList, "ptsList", pts, "pts");
        int npt = ptsList.size();
        int cl = getLabel(ptsList.get(npt-1), n, w, a);
//        int cl = 0;
        //long cv = getAns(1, a, w, n);
        long cv = getAns(ptsList.get(0), a, w, n);
        ans = cv;

        for (int j = 0; j < npt; j++) {
            int pt = ptsList.get(j);
            List<Pair<Integer, LABEL>> difs = pts.get(pt);
            int tdif = 0;
            for (Pair<Integer, LABEL> pr : difs) {
                if (pr.getRight() == LABEL.INC) {
                    tdif += 2;
                } else if (pr.getRight() == LABEL.DEC) {
//                    tdif-=2;
                    tdif--;
                } else {
                    tdif--;
                }
            }
            cl += tdif;
            ans = Math.min(ans, cv);
            ut.printDebug(pt, "pt", cl, "cl", cv, "cv", j, "j");
            if (j < (npt-1)) {
                cv = cv + cl * (ptsList.get(j + 1) - pt);
            }
        }
//        for (int i = 0; i < n; i++) {
//            ans = Math.min(ans, getAns(i, a, w, n));
//        }
        out.printf("Case #%d: %d\n", testNumber, ans);
    }

    private enum LABEL {
        INC, DEC, SAME
    }

    private List<Pair<Integer, LABEL>> getPts(int x, int n) {
        List<Pair<Integer, LABEL>> ret = new ArrayList<>();
        ret.add(Pair.of(x, LABEL.INC));
        if (n % 2 == 0) {
            int opp = x + (n / 2);
            if (opp > n) opp -= n;
            ret.add(Pair.of(opp, LABEL.DEC));
        } else {
            int oppf = x + (n / 2);
            if (oppf > n) oppf -= n;
            ret.add(Pair.of(oppf, LABEL.SAME));
            oppf++;
            if (oppf > n) oppf -= n;
            ret.add(Pair.of(oppf, LABEL.DEC));
        }
        return ret;
    }

    private int getLabel(int tar, int n, int w, int[] a) {
        int ret = 0;
        int nxt = tar + 1;
        if (nxt > n) nxt -= n;
        for (int i = 0; i < w; i++) {
            int os = getSteps(a[i], n, tar);
            int ts = getSteps(a[i], n, nxt);
            if (os < ts) {
                ret++;
            } else if (os > ts) {
                ret--;
            }
        }
        return ret;
    }

    private int getSteps(int x, int n, int tar) {
        return Math.min(Math.abs(tar - x), n - Math.abs(tar - x));
    }

    private long getAns(int tar, int[] a, int w, int n) {
        long ret = 0L;
        for (int i = 0; i < w; i++) {
            ret += Math.min(Math.abs(tar - a[i]), n - Math.abs(tar - a[i]));
        }
        return ret;
    }
}
