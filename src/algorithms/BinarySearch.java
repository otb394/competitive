package code.algorithms;

import java.util.function.Function;

public class BinarySearch {

    /**
     * Binary search implementation which searches for leftmost 1 in a [0,0,...,1,1,..1] kind of sequence.
     * The implementation does not cache the function calls and may call it multiple times.
     *
     * @param start Beginning of sequence, inclusive
     * @param end End of sequence, exclusive
     * @param valueFunc Function which returns boolean value for each index of sequence
     * @return index of leftmost 1, if found. Returns end, otherwise.
     */
    public static int searchFirstOne(int start, int end, Function<Integer, Boolean> valueFunc) {
        int low = start;
        int high = end-1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (valueFunc.apply(mid)) {
                high = mid;
            } else {
                low = mid+1;
            }
        }

        if (valueFunc.apply(low)) {
            return low;
        } else {
            return end;
        }
    }

    /**
     * Binary search implementation which searches for rightmost 0 in a [0,0,...,1,1,..1] kind of sequence.
     * The implementation does not cache the function calls and may call it multiple times.
     *
     * @param start Beginning of sequence, inclusive
     * @param end End of sequence, exclusive
     * @param valueFunc Function which returns boolean value for each index of sequence
     * @return index of rightmost 0, if found. Returns start-1, otherwise.
     */
    public static int searchLastZero(int start, int end, Function<Integer, Boolean> valueFunc) {
        int low = start;
        int high = end-1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (valueFunc.apply(mid+1)) {
                high = mid;
            } else {
                low = mid+1;
            }
        }

        if (!valueFunc.apply(low)) {
            return low;
        } else {
            return start-1;
        }
    }
}
