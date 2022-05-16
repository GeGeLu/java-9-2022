package com.example.java92022.stream;

abstract class SinkImpl<T> implements Sink<T> {

    T obj;

    @Override
    public void accept(T obj) {
        this.obj = obj;
    }

    @Override
    public abstract void next();
}
