package com.example.java92022.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class AbstractPipeline<IN, OUT> implements Stream<OUT> {
    private AbstractPipeline prev;
    private Supplier<Iterator> iteratorSupplier;

    public AbstractPipeline(AbstractPipeline prev) {
        this.prev = prev;
        this.iteratorSupplier = prev.iteratorSupplier;
    }

    public AbstractPipeline(AbstractPipeline prev, Supplier<Iterator> iteratorSupplier) {
        this.prev = prev;
        this.iteratorSupplier = iteratorSupplier;
    }

    public abstract Sink<IN> onWrap(Sink<OUT> sink);

    @Override
    public <R> Stream<R> map(Function<OUT, R> function) {
        return new AbstractPipeline<OUT, R>(this) {
            @Override
            public Sink<OUT> onWrap(Sink<R> sink) {
                return new SinkImpl<>() {
                    @Override
                    public void next() {
                        sink.accept(function.apply(obj));
                        sink.next();
                    }
                };
            }
        };
    }

    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, OUT> biConsumer) {
        R res = supplier.get();
        Sink<OUT> finalSink = new SinkImpl<>() {
            @Override
            public void next() {
                biConsumer.accept(res, this.obj);
            }
        };
        Sink sink = getHeadSink(finalSink);
        Iterator itr = iteratorSupplier.get();
        while(itr.hasNext()) {
            sink.accept(itr.next());
            sink.next();
        }
        return res;
    }

    private Sink getHeadSink(Sink sink) {
        AbstractPipeline cur = this;
        while(cur.prev != null) {
            sink = cur.onWrap(sink);
            cur = cur.prev;
        }
        return sink;
    }
}

