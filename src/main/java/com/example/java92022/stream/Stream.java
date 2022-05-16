package com.example.java92022.stream;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Stream<T> {
    static <T> Stream<T> of(Collection<T> collection) {
        return new AbstractPipeline<T, T>(null, () -> collection.iterator()) {
            @Override
            public Sink<T> onWrap(Sink<T> sink) {
                return new SinkImpl<>() {
                    @Override
                    public void next() {
                        sink.accept(obj);
                    }
                };
            }
        };
    }


    <R> Stream<R> map(Function<T, R> function);


    <R> R collect(Supplier<R> supplier, BiConsumer<R, T> biConsumer);
}
