package util;

import java.util.HashSet;
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

    /**
     * Finds highest common factor of the array of numbers
     *
     * @param arr Array of numbers
     * @param n Number of numbers in the array
     * @return Highest common factor
     */
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

    /**
     * Finds highest common factor of the array of numbers
     *
     * @param arr Array of numbers
     * @param n Number of numbers in the array
     * @return Highest common factor
     */
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

    /**
     * Calculates the ceiling function when dividing num with denom
     *
     * @param num Numerator
     * @param denom Denominator
     * @return ceiling of num/denom
     */
    public static int ceil(int num, int denom) {
        return (int) Math.ceil(((double)num) / ((double)denom));
    }

    /**
     * Calculates the ceiling function when dividing num with denom
     *
     * @param num Numerator
     * @param denom Denominator
     * @return ceiling of num/denom
     */
    public static long ceil(long num, long denom) {
        return (long) Math.ceil(((double)num) / ((double)denom));
    }

    /**
     * Calculates the mod inverse of a (mod MOD), i.e. x such that (ax)%MOD = 1.
     *
     * @param a Number in [0,MOD-1], we want to take inverse of
     * @param MOD Number to take mod with. Should be a prime number.
     * @return x such that (a*x)%MOD = 1. x will be in [0, MOD-1]
     */
    public static int modInversePrime(int a, int MOD) {
        return pow(a, MOD-2, MOD);
    }

    /**
     * Calculates the mod inverse of a (mod MOD), i.e. x such that (ax)%MOD = 1.
     *
     * @param a Number we want to take inverse of
     * @param MOD Number to take mod with. a and MOD should be co primes.
     * @return x such that (a*x)%MOD = 1. x will be in [0, MOD-1]
     */
    public static int modInverseNonPrime(int a, int MOD) {
        ExtendedEuclideanAlgorithm.Output output = ExtendedEuclideanAlgorithm.execute(a, MOD);
        if (output.gcd != 1) {
            throw new RuntimeException(String.format("Illegal arguments passed to " +
                    "modInverseNonPrime. a[%d] and MOD[%d] should be co primes", a, MOD));
        }
        return ExtendedEuclideanAlgorithm.execute(a, MOD).x;
    }

    /**
     * Calculates (num/denom) % MOD
     *
     * @param num Should be in [0, MOD-1]
     * @param denom Should be in [0, MOD-1]
     * @param MOD Should be a prime number
     * @return returns the quotient mod value
     */
    public static int dividePrime(int num, int denom, int MOD) {
        int mul = modInversePrime(denom, MOD);
        return mult(num, mul, MOD);
    }

    /**
     * Calculates (num/denom) % MOD
     *
     * @param num Should be in [0, MOD-1]
     * @param denom Should be in [0, MOD-1]
     * @param MOD denom and MOD should be coprime
     * @return returns the quotient mod value
     */
    public static int divideNonPrime(int num, int denom, int MOD) {
        int mul = modInverseNonPrime(denom, MOD);
        return mult(num, mul, MOD);
    }

    /**
     * Executes the extended euclidean algorithm on a and b input to generate Bezout coefficients and gcd
     */
    public static class ExtendedEuclideanAlgorithm {
        public static Output execute(int a, int b) {
            //rz stands for r0 and ro stands for r1. Same for s and t series.
            int sz = 1;
            int so = 0;
            int tz = 0;
            int to = 1;
            int rz = a;
            int ro = b;
            while (ro > 0) {
                int q = rz/ro;
                int r = rz - q*ro;
                int s = sz - q*so;
                int t = tz - q*to;
                rz = ro;tz = to;sz = so;
                ro = r;to = t;so = s;
            }
            return new Output(sz, tz, rz);
        }

        public static class Output {
            /**
             * x is coefficient of a
             * ax + by = gcd
             */
            public int x;
            /**
             * y is coefficient of b
             * ax + by = gcd
             */
            public int y;
            /**
             * gcd of a and b
             */
            public int gcd;

            public Output(int x, int y, int gcd) {
                this.x = x;
                this.y = y;
                this.gcd = gcd;
            }

            @Override
            public String toString() {
                return "Output{" +
                        "x=" + x +
                        ", y=" + y +
                        ", gcd=" + gcd +
                        '}';
            }
        }
    }
}
