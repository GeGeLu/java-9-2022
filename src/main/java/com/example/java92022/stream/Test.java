package com.example.java92022.stream;

import java.util.ArrayList;
import java.util.List;

class Test {
    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        Stream<Integer> stream = Stream.of(l);
        l.add(1);
        l.add(2);
        List<String> ans = stream.map(v -> String.valueOf(v))
                .map(s -> s + "--")
                .map(s -> s + "//")
                .collect(ArrayList::new, (list, s) -> list.add(s));
        System.out.println(ans);
    }
}
