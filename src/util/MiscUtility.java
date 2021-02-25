package util;

import datastructure.Pair;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MiscUtility {
    public boolean debug;

    public MiscUtility() {
        debug = System.getProperty("ONLINE_JUDGE") == null;
    }

    public MiscUtility(boolean debug) {
        this.debug = debug;
    }

    public static void assertion(boolean condition) {
        if (!condition) {
            throw new RuntimeException("Assertion failed");
        }
    }

    public static void assertion(boolean condition, String message) {
        if (!condition) {
            throw new RuntimeException("Assertion failed. " + message);
        }
    }

    public void printDebug(Pair<Object, String> ...objects) {
        if (debug) {
            for (Pair<Object, String> object : objects) {
                System.out.print(String.format("[%s: %s] ", object.getRight(), object.getLeft().toString()));
            }
            System.out.println();
        }
    }

    /**
     * Prints values of objects with alternating object1, name1, object2, name2... pattern
     */
    public void printDebug(Object ...objects) {
        if (debug) {
            boolean isName = false;
            Object prev = null;
            for (Object object : objects) {
                if (isName) {
                    System.out.printf("[%s: %s] ", object.toString(), prev.toString());
                } else {
                    if (object instanceof Supplier) {
                        object = ((Supplier)object).get();
                    } else if (object instanceof int[]) {
                        object = Arrays.stream((int[])object).boxed().collect(Collectors.toList());
                    } else if (object instanceof long[]) {
                        object = Arrays.stream((long[]) object).boxed().collect(Collectors.toList());
                    } else if (object instanceof int[][]) {
                        object = Arrays.stream((int[][]) object)
                                .map(y -> Arrays.stream(y).boxed().collect(Collectors.toList()))
                                .collect(Collectors.toList());
                    } else if (object instanceof long[][]) {
                        object = Arrays.stream((long[][]) object)
                                .map(y -> Arrays.stream(y).boxed().collect(Collectors.toList()))
                                .collect(Collectors.toList());
                    }
                    prev = object;
                }
                isName = !isName;
            }
            System.out.println();
        }
    }
}
