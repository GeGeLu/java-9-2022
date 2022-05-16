package com.example.java92022.stream;

public interface Sink<T> {
    void accept(T obj);
    void next();
}

