package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        String s = in.nextString();
        int ans = solve(n, a, b, s);
        out.print(ans);
    }

    private int solve(int n, int a, int b, String s) {
        int[] aggList = getAggList(s);
        int size = aggList.length;
        int oddCount = 0;
        for (int i = 0; i < size; i++) {
            if ((aggList[i] & 1) != 0) {
                oddCount++;
            }
        }
        int minCap = 0;
        for (int i = 0; i < size; i++) {
            minCap += aggList[i]>>1;
        }
        int total = a+b;
        a-= Math.min(a, minCap);
        b-= Math.min(b, minCap);
        int rem = a+b;
        rem -= Math.min(rem, oddCount);
        return total - rem;
    }

    private int[] getAggList(String s) {
        String[] components = s.split("\\*");
        int size = components.length;
        int[] aggList = new int[size];
        for (int i = 0; i < size; i++) {
            aggList[i] = components[i].length();
        }
        return aggList;
    }
}
