package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.List;

public class ESumOfDigits {
    MiscUtility ut;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        ut = new MiscUtility(false);
        ut.printDebug(testNumber, "testNumber");
        int n = in.nextInt();
        int k = in.nextInt();
        boolean found = false;
        long ans = Long.MAX_VALUE;

        //Case 0
        int val = (k * (k+1)) / 2;
        int rt = n - val;
        if ((rt >= 0) && (rt % (k+1)) == 0) {
            int s = rt / (k+1);
            found = true;
            ut.printDebug(s, "s", rt, "rt", k+1, "k+1");
            ans = Math.min(ans, getSmallest(s, true) * 10L);
        }

        for (int i = 1; i <= 9; i++) {
            int numS = Math.min(k+1, 10-i);
            int numSO = k+1-numS;
            int vall = 0;
            int curr = i;
            for (int j = 0; j <= k; j++) {
                vall += curr;
                curr = (curr + 1) % 10;
            }

            int rem = n-vall;
            if (rem >= 0) {
                List<Pair<Integer, Integer>> s = getS(rem, numS, numSO);
                ut.printDebug(i, "i", numS, "numS", numSO, "numSO", vall, "vall", rem, "rem", s, "s");
//            int s = getS(rem, numS, numSO);
                if (!s.isEmpty()) {
                    found = true;
                    for (Pair<Integer, Integer> ii : s) {
                        ans = Math.min(ans, (getSmallest(ii.getLeft(), ii.getRight())) * 10L + ((long) i));
                    }
                }
            }
        }

        if (!found) {
            out.println(-1);
        } else {
            out.println(ans);
        }
    }

    private long getSmallest(int sum, boolean ninesAllowed) {
        int q = sum / 9;
        int r = sum % 9;
        StringBuilder builder = new StringBuilder();
        if (ninesAllowed) {
            builder.append((char) (r + '0'));
            for (int i = 0; i < q; i++) {
                builder.append('9');
            }
        } else {
            if (r == 0) {
                if (q > 0) {
                    builder.append('1');
                } else {
                    builder.append('0');
                }
//                builder.append((char) (r + '0'));
                for (int i = 0; i < (q-1); i++) {
                    builder.append('9');
                }
                if (q > 0) {
                    builder.append("8");
                }
            } else {
                if (q == 0) {
                    builder.append((char) (r + '0'));
                } else {
                    builder.append((char) ((r+1) + '0'));
                    for (int i = 0; i < (q-1); i++) {
                        builder.append('9');
                    }
                    builder.append('8');
                }
            }
        }
        ut.printDebug(sum, "sum", q, "q", r, "r", ninesAllowed, "nineFlag", builder.toString(), "smallest");
        return Long.parseLong(builder.toString());
    }

    private long getSmallest(int sum, int nines) {
        MiscUtility.assertion(sum >= (9*nines), String.format("sum[%d], nines[%d]", sum, nines));
        long val = getSmallest(sum - 9*nines, false);
        for (int i = 0; i < nines; i++) {
            val = (val * 10L) + 9L;
        }
        ut.printDebug(sum, "sum", nines, "nines", val, "val");
        return val;
    }

    private List<Pair<Integer,Integer>> getS(int sum, int numS, int numSO) {
        List<Pair<Integer, Integer>> ret = new ArrayList<>();
        for (int i = 0; i <= sum; i++) {
            int nines = 0;
            while (nines * 9 <= i) {
                int j = i - 9*nines + 1;
                if (j < 0) break;
                if (sum == (numS * i + numSO * j)) {
                    ret.add(Pair.of(i, nines));
                }
                nines++;
            }
        }
        return ret;
    }
}
