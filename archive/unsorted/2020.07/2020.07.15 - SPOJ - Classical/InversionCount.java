package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

public class InversionCount {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        long ans = mergeSort(a, 0, n-1);
        out.println(ans);
    }

    private long mergeSort(int[] a, int start, int end) {
        if (start == end) return 0;
        int mid = start + (end - start) / 2;
        long f = mergeSort(a, start, mid);
        long s = mergeSort(a, mid + 1, end);
        return f + s + merge(a, start, mid, end);
    }

    private long merge(int[] a, int start, int mid, int end) {
        int n = end - start + 1;
        int[] t = new int[n];
        int i = start;
        int j = mid + 1;
        int k = 0;
        long ret = 0L;
        while (i <= mid && j <= end) {
            if (a[j] < a[i]) {
                t[k] = a[j];
                k++;
                j++;
                ret += mid - i + 1;
            } else {
                t[k] = a[i];
                i++;k++;
            }
        }
        while (i <= mid) {
            t[k++] = a[i++];
        }
        while (j <= end) {
            t[k++] = a[j++];
        }
        if (n > 0) System.arraycopy(t, 0, a, start, n);
        return ret;
    }
}
