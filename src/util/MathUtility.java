package util;

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
}
