package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class SavingTheUniverseAgain {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long d = in.nextLong();
        String p = in.nextString();
        int ans = solve(d, p);
        if (ans == -1) {
            out.println("Case #" + testNumber + ": IMPOSSIBLE");
        } else {
            out.println("Case #" + testNumber + ": " + ans);
        }
    }

    private int solve(long d, String p) {
        char[] arr = p.toCharArray();
        int n = p.length();
        long[] b = getB(p, n);
        long curr = getCurr(b, p, n);
//        System.out.println("curr = " + curr);
        int i = n-1;
        int steps = 0;
//        System.out.println("n = " + n);
//        for (int j = 0; j < n; j++) {
//            System.out.print(b[j] + " ");
//        }
//        System.out.println();
        while(i > 0 && curr > d) {
//            System.err.println("i = " + i);
//            System.out.println(curr);
            int j = i;
            while(j >= 0 && arr[j] != 'C') {
                j--;
            }
            while(j>=0 && j<i) {
                swap(j, j+1, arr);
                curr -= b[j];
                b[j+1] = b[j];
                j++;
                steps++;
            }
            i--;
        }
        if (curr > d) {
            return -1;
        } else {
            return steps;
        }
    }

    private void swap(int j, int i, char[] arr) {
        char temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    private long getCurr(long[] pRef, String p, int n) {
        long curr = 0L;
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) == 'S') {
                curr+=pRef[i];
            }
        }
        return curr;
    }

    private long[] getB(String p, int n) {
        long[] ret = new long[n];
        ret[0] = 1L;
        for (int i = 1; i < n; i++) {
            if (p.charAt(i-1) == 'C') {
                ret[i] = ret[i-1]<<1L;
            } else {
                ret[i] = ret[i-1];
            }
        }
        return ret;
    }
}
