package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class E2ThreeBlocksPalindromeHardVersion {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();int[] a=in.nextIntArray(n);
        int MAXA = 200;
        List<List<Integer>> indexes = new ArrayList<>();
        for (int i = 0; i <= MAXA; i++) {
            indexes.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            int val = a[i];
            indexes.get(val).add(i);
        }

        int[][] pref = new int[MAXA+1][n];

        for (int i = 1; i <= MAXA; i++) {
            pref[i][0] = (a[0] == i) ? (1) : 0;
            for (int j = 1; j < n; j++) {
                pref[i][j] = pref[i][j-1];
                if (a[j] == i) {
                    pref[i][j]++;
                }
            }
        }

        int ans = 0;

        for (int i = 1; i <= MAXA; i++) {
            ans = Math.max(ans, indexes.get(i).size());
        }

        for (int i = 1; i <= MAXA; i++) {
            int l = 0;
            int h = indexes.get(i).size()-1;
            while (l < h) {
                int lr = indexes.get(i).get(l)+1;
                int rr = indexes.get(i).get(h)-1;
                for (int j = 1; j <= MAXA; j++) {
                    int count = getCount(lr, rr, j, pref);
                    ans = Math.max(ans, 2*(l+1) + count);
                }
                l++;
                h--;
            }
        }
        out.println(ans);
    }

    private int getCount(int i, int j, int val, int[][] pref) {
        if (i > j) {
            return 0;
        }
        return (i == 0) ? (pref[val][j]) : (pref[val][j] - pref[val][i-1]);
    }
}
