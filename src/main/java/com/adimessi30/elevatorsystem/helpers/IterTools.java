package com.adimessi30.elevatorsystem.helpers;

import java.util.function.BiConsumer;

public final class IterTools {
    public static <T> void forEachWithCounter(Iterable<T> source, BiConsumer<Integer, T> consumer) {
        int i = 0;
        for (T item : source) {
            consumer.accept(i, item);
            i++;
        }
    }
}
