package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class AdaMatrix {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        List<Integer> lst = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            lst.add(((arr[i][i] == (arr[i][i-1] + 1)) ? (0) : (1)));
        }
        int i = n-2;
        while (i >= 0 && lst.get(i) == 0) {
            i--;
        }
        int ans = 0;
        while (i >= 0) {
            ans++;
            int j = i-1;
            while (j >= 0 && lst.get(j).equals(lst.get(i))) {
                j--;
            }
            i = j;
        }
        out.println(ans);
    }
}
