package util;

import algorithms.BinarySearch;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

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
        while (b > 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
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

    /**
     * Finds highest common factor of the array of numbers
     *
     * @param arr Array of numbers
     * @param start Starting index, inclusive
     * @param end Ending index, exclusive
     * @return Highest common factor
     */
    public static int hcf(int[] arr, int start, int end) {
        int result = arr[start];
        for(int i=start+1;i<end;i++) {
            result=hcf(arr[i],result);
        }
        return result;
    }

    public static long hcf(long a, long b) {
        while (b > 0L) {
            long temp = a;
            a = b;
            b = temp%b;
        }
        return a;
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
        if (num % denom == 0) return num/denom;
        return num/denom + 1;
    }

    /**
     * Calculates the ceiling function when dividing num with denom
     *
     * @param num Numerator
     * @param denom Denominator
     * @return ceiling of num/denom
     */
    public static long ceil(long num, long denom) {
        if (num % denom == 0L) return num/denom;
        return num/denom + 1L;
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

    /**
     * Modifies the input array p into the next lexicographically greater permutation, if possible.
     *
     * @param p Input permutation
     * @param start starting index in the array, inclusive
     * @param end ending index in the array, exclusive
     * @return If next permutation exists, returns true, else returns false
     */
    public static boolean next_permutation(int[] p, int start, int end) {
        for (int i = end-2; i >= start; i--) {
            if (p[i] < p[i+1]) {
                int finalI = i;
                Function<Integer, Boolean> func = ind -> p[ind] <= p[finalI];
                int next = BinarySearch.searchLastZero(i+1, end, func);
                int temp = p[i];
                p[i] = p[next];
                p[next] = temp;
                int count = end-1-i;
                int mx = i + count/2;
                for (int j = i+1; j <= mx; j++) {
                    int tt = p[j];
                    p[j] = p[end-j+i];
                    p[end-j+i] = tt;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Modifies the input array p into the next lexicographically greater permutation, if possible.
     *
     * @param p Input permutation
     * @param start starting index in the array, inclusive
     * @param end ending index in the array, exclusive
     * @param comparator Comparator to compare elements of the array
     * @param <T> Type of the array elements
     * @return If next permutation exists, returns true, else returns false
     */
    public static <T> boolean next_permutation(T[] p, int start, int end, Comparator<T> comparator) {
        for (int i = end-2; i >= start; i--) {
            if (comparator.compare(p[i], p[i+1]) < 0) {
                int finalI = i;
                Function<Integer, Boolean> func = ind -> comparator.compare(p[ind], p[finalI]) <= 0;
                int next = BinarySearch.searchLastZero(i+1, end, func);
                swap(p, i, next);
                int l = i + 1;
                int r = end - 1;
                while (l < r) {
                    swap(p, l, r);
                    l++;
                    r--;
                }
                return true;
            }
        }
        return false;
    }

    private static <T> void swap(T[] p, int i, int j) {
        T temp = p[i];
        p[i] = p[j];
        p[j] = temp;
    }
}
