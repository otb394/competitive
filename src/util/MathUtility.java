package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MathUtility {
    public static int pow(int b, long p, int MOD) {
        int ret = 1;
        while(p>0L) {
            if ((p & 1L) == 1L) {
                ret = mult(ret, b, MOD);
            }
            p>>=1L;
            b = mult(b, b, MOD);
        }
        return ret;
    }

    public static int sub(int a, int b, int MOD) {
        return add(a, MOD-b, MOD);
    }

    public static int mult(int a, int b, int MOD) {
        long temp = ((long) a) * ((long)b);
        if (temp >= ((long)MOD)) {
            temp%=MOD;
        }
        return (int)temp;
    }

    public static int add(int a, int b, int MOD) {
        int res = a+b;
        if (res >= MOD) {
            res -= MOD;
        }
        return res;
    }

    public static boolean isSquare(int n) {
        int sq = (int) Math.sqrt(n);
        return ((sq * sq) == n);
    }

    public static int hcf(int a, int b) {
        if (a==0) {
            return b;
        }
        return hcf(b%a, a);
    }

    public static int hcf(int[] arr, int n) {
        int result = arr[0];
        for(int i=1;i<n;i++) {
            result=hcf(arr[i],result);
        }
        return result;
    }

    public static long hcf(long a, long b) {
        if (a==0L) {
            return b;
        }
        return hcf(b%a, a);
    }

    public static long hcf(long[] arr, int n) {
        long result = arr[0];
        for(int i=1;i<n;i++) {
            result=hcf(arr[i],result);
        }
        return result;
    }

    public static long lcm(long a, long b) {
        if (a>b) return lcm(b,a);
        long hcf = hcf(a,b);
        return (b/hcf)*a;
    }

    /**
     * Evaluates all the factors of a given number.
     *
     * No repetitions. Output is in no specific order.
     * Note: May need optimization.
     *
     * @param n Given number
     * @return All factors of n
     */
    public static int[] factorize(int n) {
        if (n==0) return new int[0];
        int s = (int) Math.floor(Math.sqrt(n));
        Set<Integer> factors = new HashSet<>();
        factors.add(1);
        factors.add(n);
        for (int i=2;i<=s;i++) {
            if ((n%i)==0) {
                factors.add(i);factors.add(n/i);
            }
        }
        return factors.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Checks whether the given number is a power of two or not.
     */
    public static boolean isPowerOfTwo(long n) {
        return n == (Long.highestOneBit(n));
    }
}
