package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class BNewTheatreSquare {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        String[] map = new String[n];
        for (int i = 0; i < n; i++) {
            map[i] = in.nextString();
        }
        int ans = 0;
        if (y >= 2*x) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i].charAt(j) == '.') cnt++;
                }
            }
            ans = cnt * x;
        } else {
            for (int i = 0; i < n; i++) {
                int j = 0;
                while(j < m) {
                    if (map[i].charAt(j) != '.') {
                        j++;
                    } else {
                        if (j!=(m-1) && (map[i].charAt(j+1) == '.')) {
                            ans+=y;
                            j+=2;
                        } else {
                            ans+=x;
                            j++;
                        }
                    }
                }
            }
        }
        out.println(ans);
    }
}
