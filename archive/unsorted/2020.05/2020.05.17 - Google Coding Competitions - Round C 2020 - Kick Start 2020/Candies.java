package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;

public class Candies {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();int q=in.nextInt();
        int[] a= in.nextIntArray(n);

        int s = (int)Math.ceil(Math.sqrt(n));
        int nb = (int)Math.ceil(((double)n)/((double)s));

        long[] n1 = new long[nb];
        long[] n2 = new long[nb];

        for (int i = 0; i < nb; i++) {
            Pair<Integer,Integer> range = getRange(i, s, n);
            int l=range.getLeft();
            int r = range.getRight();
            int c1 = 1;
            long c2 = 1L;
            long t1 = 0L;
            long t2 = 0L;
            for (int j = l; j <= r; j++) {
                t1 += c1*a[j];
                t2 += c1*c2*a[j];
                c1*=-1;
                c2++;
            }
            n1[i]=t1;
            n2[i]=t2;
        }

        long ans = 0L;
        for (int tt = 0; tt < q; tt++) {
            char c = in.nextCharacter();
            if (c == 'U') {
                int x = in.nextInt();x--;
                int v = in.nextInt();
                int bind = getBIndex(x,s,n);
                Pair<Integer,Integer> ran = getRange(bind, s, n);
                int l = ran.getLeft();
                int c1 = ((x-l)%2 == 0) ? (1) : (-1);
                long c2 = x-l+1L;
                n1[bind] += c1 * (v-a[x]);
                n2[bind] += c2 * c1 * (v-a[x]);
                a[x] = v;
            } else if (c == 'Q') {
                int l = in.nextInt();l--;
                int r = in.nextInt();r--;
                int lbind = getBIndex(l,s,n);
                int rbind = getBIndex(r,s,n);
                long bans = 0L;
                if (lbind == rbind) {
                    int c1=1;
                    long c2=1L;
                    for (int i = l; i <= r; i++) {
                        bans+=c2*c1*a[i];
                        c1*=-1L;
                        c2++;
                    }
                } else {
                    int cl = (l == lbind * s) ? (lbind) : (lbind + 1);
                    int cr = (r == (((rbind + 1) * s) - 1)) ? (rbind) : (rbind - 1);
                    for (int i = cl; i <= cr; i++) {
                        Pair<Integer, Integer> bran = getRange(i, s, n);
                        int bl = bran.getLeft();
                        int rank = bl - l;
                        bans += getN2(n2, i, rank, n1);
                    }
                    if (cl != lbind) {
                        int c1 = 1;
                        long c2 = 1L;
                        int cll = getRange(cl, s, n).getLeft();
                        for (int i = l; i < cll; i++) {
                            bans += c2 * c1 * a[i];
                            c1 *= -1;
                            c2++;
                        }
                    }
                    if (cr != rbind) {
                        int f = getRange(cr, s, n).getRight() + 1;
                        int c1 = ((f - l) % 2 == 0) ? (1) : (-1);
                        long c2 = f - l + 1L;
                        for (int i = f; i <= r; i++) {
                            bans += c2 * c1 * a[i];
                            c1 *= -1;
                            c2++;
                        }
                    }
                }
                ans+=bans;
            }
        }

        out.println(String.format("Case #%d: %d", testNumber, ans));
    }

    private long getN2(long[] n2, int bind, long rank, long[] n1) {
        if (rank%2L == 0L) {
            return (n2[bind] + rank * n1[bind]);
        } else {
            return -1L* (n2[bind] + rank * n1[bind]);
        }
    }

    private Pair<Integer,Integer> getRange(int bind, int s, int n) {
        return Pair.of(bind*s, Math.min((bind+1)*s, n)-1);
    }

    private int getBIndex(int index, int s, int n) {
        return index/s;
    }
}
