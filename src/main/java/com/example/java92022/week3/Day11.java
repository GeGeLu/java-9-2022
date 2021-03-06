package com.example.java92022.week3;

import com.example.java92022.stream.Stream;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day11 {
    /**
     *  stream() -> pipeline instance 1
     *  map()   -> pipeline instance 2
     *  collect() -> pipeline instance 3
     *
     *  pipeline instance 1 <-> pipeline instance 2 <-> pipeline instance 3
     *      onWrap()     <-       onWrap()       <-     onWrap()
     *     Sink        ->          Sink           ->      Sink
     *
     */
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Integer> arr = new ArrayList<>();
        Stream<Integer> stream = Stream.of(arr);
        arr.add(1);
        arr.add(2);
        arr.add(3);
        System.out.println(arr);
        List<String> res = stream
                            .map(v -> String.valueOf(v))
                            .map(v -> v + v)
                            .map(v -> v + "/--")
                            .collect(ArrayList::new, List::add);
        System.out.println(res);

        Class<LazyLoadingSingleton> clazz = LazyLoadingSingleton.class;
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        System.out.println(constructor.newInstance());
    }
}


/**
 *  [][][][][][][][]
 *  [][][][][][][][]
 *  [][][][][][][][]
 *
 *  Singleton
 */

class EagerLoadingSingleton {
    private static final EagerLoadingSingleton obj = new EagerLoadingSingleton();
    private EagerLoadingSingleton() {}
    public static EagerLoadingSingleton getInstance() {
        return obj;
    }
}

class LazyLoadingSingleton implements Cloneable, Serializable {
    private static volatile LazyLoadingSingleton obj;
    private Map<Integer, Integer> map = new HashMap<>();
    private LazyLoadingSingleton() { }

    public static LazyLoadingSingleton getInstance() {
        if(obj == null) {
            synchronized (LazyLoadingSingleton.class) {
                if(obj == null) {
                    obj = new LazyLoadingSingleton();
                }
            }
        }
        return obj;
    }


    @Override
    public Object clone() {
        throw new RuntimeException("..");
    }
}

enum EnumSingleton {
    INSTANCE1;
}


/**
 *  SOLID principles
 *  design patterns
 *  factory
 *  builder
 *  template
 *  prototype
 *  observer
 *  adapter
 *  facade
 *  bridge
 *  strategy
 *  static proxy
 *  dynamic proxy
 */