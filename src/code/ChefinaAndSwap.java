package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import util.MiscUtility;

import java.util.function.Predicate;

public class ChefinaAndSwap {
    MiscUtility ut = new MiscUtility();
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = 100;
        int[] ans = new int[n];
        Predicate<Integer> debug = y -> false;
        for (int i = 0; i < n; i++) {
            int[] arr = new int[i];
            for (int j = 0; j < i; j++) {
                arr[j] = j + 1;
            }
            int tans = 0;
            if (debug.test(i)) {
                ut.printDebug(i, "i");
            }
            for (int j = 0; j < (i-1); j++) {
                if (debug.test(i)) {
                    ut.printDebug(j, "j");
                }
                for (int k = j+1; k < i; k++) {
                    if (debug.test(i)) {
                        ut.printDebug(k, "k");
                    }
                    swap(arr, j, k);
                    if (isNiceSwap(arr, i)) {
                        tans++;
                    }
                    swap(arr, k, j);
                }
            }
            ans[i] = tans;
        }
        for (int i = 0; i < n; i++) {
            System.out.printf("ans[%d] : %d\n", i, ans[i]);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private boolean isNiceSwap(int[] arr, int n) {
        long sum = 0L;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }
        if (sum % 2L != 0L) return false;
        sum >>= 1L;
        long tsum = 0L;
        for (int i = 0; i < n; i++) {
            tsum += arr[i];
            if (tsum == sum) return true;
        }
        return false;
    }
}
