package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class WeaponValue {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextString();
        }
        int ans = 0;
        int sz = arr[0].length();
        for (int i = 0; i < sz; i++) {
            int temp = 0;
            for (int j = 0; j < n; j++) {
                temp += getVal(arr[j].charAt(i));
            }
            if ((temp&1) != 0) ans++;
        }
        out.println(ans);
    }

    private int getVal(char c) {
        if (c=='1') return 1;
        return 0;
    }
}
