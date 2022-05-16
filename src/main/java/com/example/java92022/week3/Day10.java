package com.example.java92022.week3;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *  equals vs hashcode ?
 *      only override equals() =>
 */

class Day10HashMapTest {
    private static class Day10Student {
        int id;
        String name;

        public Day10Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Day10Student that = (Day10Student) o;
            return id == that.id && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return "Day10Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    public static void main(String[] args) {
        Day10Student s1 = new Day10Student(2, "Tom");
        Day10Student s2 = new Day10Student(1, "Tom");
        Map<Day10Student, Integer> map = new HashMap<>();
        map.put(s1, 1);
        s1.id = 1;
        System.out.println(map.get(s2)); // 1 ?  ,  null ?

        String[] arr = new String[Integer.MAX_VALUE]; // Integer.MAX_VALUE ? ,  Long.MAX_VALUE ?
    }
}
/**
 *  fail-fast => ConcurrentModificationException
 */

class Day10FailFastTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        for(int v: list) {
            list.add(5);
        }
    }
}


/**
 * ConcurrentHashmap
 *      put: synchronized + CAS
 *      get: volatile
 *
 *
 * volatile
 *      1. visibility
 *      2. re-ordering
 *      3. happen - before
 *         read1 read2 write1  read3  read4
 *                 ---------> timeline
 *
 */

class Day10VolatileTest {
    private static volatile boolean flag;

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread());
            while(!flag) {

            }
            System.out.println("we break the while loop");
        });
        t1.start();
        Thread.sleep(500);
        Thread t2 = new Thread(() -> {
            flag = true;
            System.out.println("changed flag from false to : " + flag);
        });
        t2.start();
    }
}


/**
 * stream api
 *
 * for loop vs stream api
 *
 * for(int i = 0; i < list.size(); i++) {
 *     ..list
 *     ..
 *     ..
 * }
 *
 *
 */

class StreamAPITest {
    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(2);
        l.add(2);
        Map<Integer, Long> count = l.stream().collect(
                HashMap::new,
                (map, val) -> map.put(val, map.getOrDefault(val, 0l) + 1),
                (finalMapResult, tmpMapResult) -> {}
        );

        Map<Integer, Long> count2 = l.stream().collect(
                Collectors.groupingBy(x -> x, Collectors.counting())
        );
        System.out.println(count2);
    }
}

/**
 *  input [1, 2, 1, 2, 3, 3, 3, 4, 2, 2]
 *  return 2nd highest frequency element
 *   2 - 4
 *   3 - 3
 *   1 - 2
 *   4 - 1
 *
 *
 *  TDD
 *   1. if input is null => exception
 *   2. if input is empty / input.size() == 0 => null
 *   3. if [1, 1, 2, 2]  => 1
 *   ...
 *
 *
 *   PriorityQueue
 *
 *  public int secondHighestFreqNum(List<Integer> input) {
 *      if(input == null) {
 *          throw new IllegalArgumentException("..");
 *      }
 *      Map<Integer,Integer> map = new HashMap<>();
 *
 *      for(int i : input) map.put(i,map.getOrDefault(i,0)+1);
 *
 *      if(map.size() < 2) {
 *          throw new IllegalArgumentException("..");
 *      }
 *
 *      PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b)-> {
 *          Integer v1 = map.get(a);
 *          Integer v2 = map.get(b);
 *          if(v1 == v2) {
 *              return a - b;
 *          }
 *          return v1 - v2;
 *      });
 *      for(int i : map.keySet()){
 * 	        pq.add(i); //pq.offer(i);
 * 	        if(pq.size>2) pq.poll();
 *      }
 *      return pq.poll();
 *  }
 *
 *
 *  private int KthHighestFrequencyElement(List<Integer> input, int k) {
 *
 *  }
 *
 */
class Test {
    public static void main(String[] args) {
        Integer v1 = 1;
        Integer v2 = 1;
        Integer v3 = 128;
        Integer v4 = 128;
        System.out.println(v1.equals(v2));
        System.out.println(v3.equals(v4));
    }
}

/**
 *  homework:
 *      upload to git , send to me before tomorrow 10am CDT
 */

