package util;

import java.util.function.Supplier;

public class Suppliers {
    public static <T> Supplier<T> of(T object) {
        return () -> object;
    }
}
