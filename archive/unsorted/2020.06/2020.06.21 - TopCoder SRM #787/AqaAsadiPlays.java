package code;

import util.MathUtility;

import java.util.Arrays;

public class AqaAsadiPlays {
    public int getMin(int[] A) {
        int n = A.length;
        Arrays.sort(A);
        for (int i = 1; i < n; i++) {
            int gcd = MathUtility.hcf(A, i, n);
            if (gcd > A[i-1]) {
                return n-i;
            }
        }
        return -1;
    }
}
