package code;

public class BinaryDistance {
    public int maxDist(int n, int v) {
        int left = getLeft(n);
        int right = getRight(n);
        if (v == 1) {
            return Math.max(left, right);
        } else {
            int h = getHeight(v);
            if (isLeft(v)) {
                return h + right;
            } else {
                return h + left;
            }
        }
    }

    private int getLeft(int n) {
        return (int) (Math.log(n) / Math.log(2));
    }

    private int getRight(int n) {
        int h = getHeight(n);
        return isLeft(n) ? h-1 : h;
    }

    private int getHeight(int v) {
        return (int) (Math.log(v) / Math.log(2));
    }

    private boolean isLeft(int v) {
        int h = getHeight(v);
        return v < (3 * (1<<(h-1)));
    }
}

