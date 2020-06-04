package algorithms;

import java.util.function.Function;

public class BinarySearch {

    /**
     * Binary search implementation which searches for leftmost 1 in a [0,0,...,1,1,..1] kind of sequence.
     *
     * @param start Beginning of sequence. Value of start assumed to be 0.
     * @param end End of sequence. Value of end is assumed to be 1.
     * @param valueFunc Function which returns boolean value for each index of sequence
     * @return index of leftmost 1.
     */
    public static int searchFirstOne(int start, int end, Function<Integer, Boolean> valueFunc) {
        int low = start;
        int high = end;
        while ((high - low) > 1) {
            int mid = low + (high - low) / 2;
            if (valueFunc.apply(mid)) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return high;
    }

    /**
     * Binary search implementation which searches for rightmost 0 in a [0,0,...,1,1,..1] kind of sequence.
     *
     * @param start Beginning of sequence. Value of start is assumed to be 0.
     * @param end End of sequence. Value of end is assumed to be 1.
     * @param valueFunc Function which returns boolean value for each index of sequence
     * @return index of rightmost 0.
     */
    public static int searchLastZero(int start, int end, Function<Integer, Boolean> valueFunc) {
        int low = start;
        int high = end;
        while ((high - low) > 1) {
            int mid = low + (high - low) / 2;
            if (valueFunc.apply(mid)) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return low;
    }

    /**
     * Binary search implementation which searches for leftmost 1 in a [0,0,...,1,1,..1] kind of sequence.
     *
     * @param start Beginning of sequence. Value of start is assumed to be 0.
     * @param end End of sequence. Value of end is assumed to be 1.
     * @param valueFunc Function which returns boolean value for each index of sequence
     * @return index of leftmost 1.
     */
    public static long searchFirstOne(long start, long end, Function<Long, Boolean> valueFunc) {
        long low = start;
        long high = end;
        while ((high - low) > 1L) {
            long mid = low + (high - low) / 2L;
            if (valueFunc.apply(mid)) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return high;
    }

    /**
     * Binary search implementation which searches for rightmost 0 in a [0,0,...,1,1,..1] kind of sequence.
     *
     * @param start Beginning of sequence. Value of start is assumed to be 0.
     * @param end End of sequence. Value of end is assumed to be 1.
     * @param valueFunc Function which returns boolean value for each index of sequence
     * @return index of rightmost 0.
     */
    public static long searchLastZero(long start, long end, Function<Long, Boolean> valueFunc) {
        long low = start;
        long high = end;
        while ((high - low) > 1L) {
            long mid = low + (high - low) / 2L;
            if (valueFunc.apply(mid)) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return low;
    }

    /**
     * Calculates a value for index such that function evaluates to true, or a large value in a [0,0,0...1,1..]
     * kind of sequence. To be used to calculate end term in binary search
     *
     * @param start Starting index. The index should not be 0, as we cannot double it.
     * @param valueFunc Function to determine true or false
     * @return Returns either a value which evaluates to true, or a large number
     */
    public static long getMax(long start, Function<Long, Boolean> valueFunc) {
        if (start == 0) {
            throw new RuntimeException("Start should not be 0 when calculating max");
        }
        long mx = (Long.MAX_VALUE>>1L);
        while (!valueFunc.apply(start) && start <= mx) {
            start<<=1L;
        }
        return start;
    }
}
