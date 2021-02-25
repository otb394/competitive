package code;

import FastIO.InputReader;
import FastIO.OutputWriter;
import datastructure.Pair;
import util.MiscUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OperationsOnATuple {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long[] f = new long[3];
        long[] s = new long[3];
        for (int i = 0; i < 3; i++) {
            f[i] = in.nextLong();
        }
        for (int i = 0; i < 3; i++) {
            s[i] = in.nextLong();
        }

        if (inZero(f,s)) {
            out.println(0);
        }
        else if (inOne(f,s)) {
            out.println(1);
        } else if (inTwo(f,s)) {
            out.println(2);
        } else {
            out.println(3);
        }
    }

    private boolean inZero(long[] f, long[]s) {
        for (int i = 0; i < 3; i++) {
            if (f[i] != s[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean inOne(long[] f, long[] s) {
        List<Pair<Long, Long>> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (f[i] != s[i]) {
                list.add(Pair.of(f[i], s[i]));
            }
        }
        if (list.size() == 1) {
            return true;
        }
        long dsize = list.stream().map(pr -> pr.getRight() - pr.getLeft()).distinct().count();
        if (dsize == 1) {
            return true;
        }
        List<Long> mults = new ArrayList<>();
        for (Pair<Long,Long> pr : list) {
            long num = pr.getRight();
            long denom = pr.getLeft();
            if (denom == 0 || num%denom != 0) {
                return false;
            }
            mults.add(num / denom);
        }
        long un = mults.stream().distinct().count();
        return (un == 1L);
    }

    private boolean inTwo(long[] f, long[] s) {
        for (int i = 0; i < 3; i++) {
            if (f[i] == s[i]) return true;
        }
        List<Long> diffs = IntStream.range(0, 3).boxed().map(i -> s[i] - f[i]).sorted().distinct()
                .collect(Collectors.toList());
        if (diffs.size() == 2) {
            return true;
        }
        MiscUtility.assertion(diffs.size() == 3, String.format("Diffs size [%d] is not 3", diffs.size()));
        for (int i = 0; i < 3; i++) {
            int on = (i+1)%3;
            int onn = (i+2)%3;
            if (diffs.get(i) == diffs.get(on) + diffs.get(onn)) return true;
        }
        if (checkMulMul(f,s)) return true;
        if (checkAddMul(f,s)) return true;
        return checkMulAdd(f,s);
    }

    private boolean checkMulMul(long[] f, long[] s) {
        for (int i = 0; i < 3; i++) {
            if (f[i] == 0L || s[i]%f[i] != 0L) {
                return false;
            }
        }
        List<Long> quots = IntStream.range(0, 3).boxed().map(i -> s[i] / f[i]).sorted().distinct()
                .collect(Collectors.toList());
        if (quots.size() == 2) return true;
        MiscUtility.assertion(quots.size() == 3, String.format("Quotients size [%d] is not 3", quots.size()));
        for (int i = 0; i < 3; i++) {
            int on = (i+1)%3;
            int onn = (i+2)%3;
            if (quots.get(on) * quots.get(onn) == quots.get(i)) return true;
        }
        return false;
    }

    private boolean checkAddMul(long[] f, long[] s) {
        for (int i = 0; i < 3; i++) {
            int on = (i+1)%3;
            int onn = (i+2)%3;
            if (f[on]!= 0L && f[onn]!=0L && (s[on]%f[on]) == 0 && (s[onn]%f[onn] == 0)
                    && (s[on]/f[on] == s[onn]/f[onn])) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (f[i] != 0L && s[i]%f[i] == 0L) {
                long y = s[i] / f[i];
                int on = (i+1)%3;
                int onn = (i+2)%3;
                long x = s[on]-f[on];
                if (s[onn] == ((f[onn] + x) * y)) return true;
                x = s[onn]-f[onn];
                if (s[on] == ((f[on] + x) * y)) return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            long x = s[i]-f[i];
            int on = (i+1)%3;
            int onn = (i+2)%3;
            long fon = f[on] + x;
            long fonn = f[onn] + x;
            if (fon != 0L && fonn != 0L && s[on]%fon == 0L && s[onn]%fonn == 0L && (s[on]/fon == s[onn]/fonn)) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (f[i] != 0L && s[i]%f[i] == 0L) {
                long y = s[i]/f[i];
                int on = (i+1)%3;
                int onn = (i+2)%3;
                if (y != 0L && s[on]%y == 0L && s[onn]%y == 0L && ((s[on]/y - f[on]) == (s[onn]/y - f[onn]))) {
                    return true;
                }
            }
        }
        return checkAllAddMul(f,s);
    }

    private boolean checkAllAddMul(long[] f, long[] s) {
        long ynum = s[1] - s[0];
        long ydenom = f[1]-f[0];
        if (ydenom != 0L && ynum%ydenom == 0L) {
            long y = ynum/ydenom;
            if (y != 0L) {
                List<Long> vals = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    if (s[i]%y != 0L) return false;
                    vals.add(s[i]/y - f[i]);
                }
                long disCount = vals.stream().distinct().count();
                return disCount == 1L;
            }
        }
        return false;
    }

    private boolean checkMulAdd(long[] f, long[] s) {
        for (int i = 0; i < 3; i++) {
            if (f[i] != 0L && s[i]%f[i] == 0L) {
                long y = s[i]/f[i];
                int on = (i+1)%3;
                int onn = (i+2)%3;
                long x = s[on]-f[on];
                if ((f[onn] * y + x) == s[onn]) return true;
                x = s[onn]-f[onn];
                if ((f[on] * y + x) == s[on]) return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            long x = s[i] - f[i];
            int on = (i+1)%3;
            int onn = (i+2)%3;
            long fon = s[on]-x;
            long fonn = s[onn]-x;
            if (f[on] != 0L && f[onn]!= 0L && fon%f[on] == 0L && fonn%f[onn] == 0L && (fon/f[on] == fonn/f[onn])) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (f[i] != 0 && s[i]%f[i] == 0L) {
                long y = s[i]/f[i];
                int on = (i+1)%3;
                int onn = (i+2)%3;
                if ((s[on] - f[on]*y) == (s[onn] - f[onn] * y)) return true;
            }
        }
        return checkAllMulAdd(f,s);
    }

    private boolean checkAllMulAdd(long[] f, long[] s) {
        long ynum = s[1] - s[0];
        long ydenom = f[1]-f[0];
        if (ydenom != 0L && ynum%ydenom == 0L) {
            long y = ynum/ydenom;
            List<Long> vals = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                vals.add(s[i] - f[i]*y);
            }
            long disCount = vals.stream().distinct().count();
            return disCount == 1L;
        }
        return false;
    }
}
