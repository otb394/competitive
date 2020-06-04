package util;

public class MiscUtility {

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
}
