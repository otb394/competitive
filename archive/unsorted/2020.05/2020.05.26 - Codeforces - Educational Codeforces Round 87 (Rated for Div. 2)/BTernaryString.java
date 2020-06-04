package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class BTernaryString {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.nextString();
        int len = s.length();
        int[][] arr = new int[4][len];
        for (int i = 1; i <=3; i++) {
            int curr = -1;
            for (int j = 0; j < len; j++) {
                if (s.charAt(j) == ((char)(i+'0'))) {
                    curr = j;
                }
                arr[i][j] = curr;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            int tn = s.charAt(i) - '0';
            int on = tn%3 +1;
            int onn = (tn+1)%3 + 1;
            int ind = Math.min(arr[on][i], arr[onn][i]);
            if (ind != -1) {
                ans = Math.min(ans, i - ind + 1);
            }
        }
        if (ans == Integer.MAX_VALUE) ans = 0;
        out.println(ans);
    }
}
