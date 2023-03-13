package com.adimessi30.elevatorsystem.helpers;

import jakarta.inject.Provider;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public final class CollectionFactory {

    public static <T> List<T> generateCollectionFromProvider(Provider<T> provider, int size) {
        return generateCollectionFromProvider(provider, size, (a, b) -> {
        });
    }

    @SneakyThrows
    public static <T> List<T> generateCollectionFromProvider(Provider<T> provider, int size,
                                                             BiConsumer<T, Integer> hook) {
        List<T> collection = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            T t = provider.get();
            hook.accept(t, i);
            collection.add(t);
        }
        return collection;
    }

}
