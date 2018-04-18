package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import static util.MathUtility.isSquare;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        out.print(solve(in.nextString()));
    }

    private int solve(String n) {
        int d = n.length();
        int mx = 1<<d;
        int mat = 1;
        int ans = Integer.MAX_VALUE;
        while(mat < mx) {
            String subNum = getSubNum(n, mat);
            if (subNum.charAt(0) != '0') {
                int num = Integer.parseInt(subNum);
                if (isSquare(num)) {
                    ans = Math.min(ans, n.length() - subNum.length());
                }
            }
            mat++;
        }
        if (ans == Integer.MAX_VALUE) {
            return -1;
        } else {
            return ans;
        }
    }

    private String getSubNum(String n, int mat) {
        int t = 1;
        int r = 0;
        char[] str = new char[n.length()];
        int z = 0;
        while(t <= mat) {
            if ((t & mat) != 0) {
                str[z++] = n.charAt(r);
            }
            t<<=1;
            r++;
        }
        return new String(str, 0, z);
    }
}
