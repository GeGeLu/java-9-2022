package com.example.java92022.week3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  SOLID
 *      Single Responsibility
 *          class StudentServiceImpl1 implements StudentService {
 *              //..student methods
 *          }
 *      Open Close
 *          class StudentServiceImpl1 implements StudentService {
 *
 *          }
 *          class XXXStudentServiceImpl1 extends StudentServiceImpl1 implements .. {
 *
 *          }
 *      Liskov Substitution
 *          class Pizza {
 *
 *          }
 *
 *          class HawaiiPizza extends Pizza {
 *
 *          }
 *      Interface Segregation
 *          interface StudentService {
 *              //100 methods
 *          }
 *
 *          interface Sub1 extends StudentService {
 *
 *          }
 *      Dependency Inversion
 *          class A {
 *              private B b;
 *              //setter / constructor
 *              public void get() {
 *                  b.xx();
 *              }
 *          }
 *
 *          interface B {}
 *          class B1 implements B {}
 *          class B2 implements B {}
 *
 *
 *  Design Pattern
 *      Singleton *
 *      Factory
 *          class A {
 *              private B b = BFactory.getB();
 *          }
 *          class .. {
 *              private B b = BFactory.getB();
 *          }
 *
 *          interface B {}
 *          class B1 implements B {}
 *          class B2 implements B {}
 *
 *          class BFactory {
 *              public static B getB() {
 *                  return new B1("", "", "", "");
 *              }
 *          }
 *
 *    Builder
 */
class Day12BuilderStudent {
    private String FN;
    private String LN;
    //....

    public Day12BuilderStudent() {}

    public Day12BuilderStudent(String FN, String LN) {
        this.FN = FN;
        this.LN = LN;
    }

    public String getFN() {
        return FN;
    }

    public Day12BuilderStudent setFN(String FN) {
        this.FN = FN;
        return this;
    }

    public String getLN() {
        return LN;
    }

    public Day12BuilderStudent setLN(String LN) {
        this.LN = LN;
        return this;
    }

    public static void main(String[] args) {
        Day12BuilderStudent student1 = new Day12BuilderStudent()
                .setFN("xx")
                .setLN("xx");
        Day12BuilderStudent student2 = new Day12BuilderStudentBuilder()
                .setFN("xx")
                .setLN("xx")
                .build();
    }
}
class Day12BuilderStudentBuilder {
    private String FN;
    private String LN;

    public Day12BuilderStudentBuilder setLN(String LN) {
        this.LN = LN;
        return this;
    }

    public Day12BuilderStudentBuilder setFN(String FN) {
        this.FN = FN;
        return this;
    }

    public Day12BuilderStudent build() {
        return new Day12BuilderStudent(FN, LN);
    }
}
/**
 *  prototype : copy
 *
 *    shallow copy vs deep copy ?
 */

class Day12ShallowVsDeepCopy {
    private Day12BuilderStudent student1;
    private String field1;
}
//Day12BuilderStudent student1{FN, LN}
//Day12ShallowVsDeepCopy instance1 {student1{FN, LN},  field1}
//Day12ShallowVsDeepCopy instance2 {student2{FN, LN},  field1}
//immutable
final class Day12ImmutableExample {
    private final String s1;
    private final List<Day12BuilderStudent> students;

    public Day12ImmutableExample(String s1, List<Day12BuilderStudent> students) {
        this.s1 = s1;
        this.students = new ArrayList<>();
        for(int i = 0; i < students.size(); i++) {
            //this.students.add(deep copy students.get(i))
        }
    }

    public String getS1() {
        return s1;
    }

    public List<Day12BuilderStudent> getStudents() {
        //create new list
        //deep copy
        return null;
    }
}

/**
 *  strategy
 */
//Calculator => sum, multiply, divide, minus
//interface Calculate {
//    float calculate(float a, float b);
//}
//class Sum implements Calculate {
//    public float calculate(float a, float b){
//        return a + b;
//    }
//}
//class Multiply implements Calculate{
//    public float calculate(float a, float b){
//        return a * b;
//    }
//}
//------------------
//interface Calculator {
//    int sum(int a, int b);
//    int multiply(int a, int b);
//}
//------------------
@FunctionalInterface
interface Calculate<T> {
    T calculate(T a, T b);
}
class Calculator<T> {
    public T compute(Calculate<T> c, T a, T b) {
        return c.calculate(a, b);
    }

    public static void main(String[] args) {
        Calculator<Integer> calculator = new Calculator<>();
        int res = calculator.compute((v1, v2) -> v1 + v2, 5, 6);
        System.out.println(res);
    }
}
/**
 *  bridge
 *
 *   *          class A {
 *  *              private B b;
 *  *              //setter / constructor
 *  *
 *
 *  *          }
 *
 */

/**
 *  observer
 */
class Topic {
    private List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void send(String msg) {
        for(Observer obs: observers) {
            obs.receive(msg);
        }
    }
}
class Observer {
    public void receive(String msg) {

    }
}

/**
 *  facade
 *              request / messages
 *                |
 *              facade pattern
 *              /   |     \
 *          class1  2     3
 *
 *
 *          switch(String input) {
 *              case "A" :
 *                  ..
 *              case "B" :
 *                  ..
 *          }
 */


/**
 *  adapter
 */
interface Robot {}
interface Human {}
class RadioAdapter {
    public void send(Human human, String msg) {

    }
}
class HumanAdapter implements Human {
    private final Robot robot;

    public HumanAdapter(Robot robot) {
        this.robot = robot;
    }
}

/**
 *  proxy (static proxy / wrapper == decorator)
 */
class RadioProxy {
    public void send(Human human, String msg) {

    }
}
class HumanProxy implements Human {
    private final Human human;

    public HumanProxy(Human human) {
        this.human = human;
    }
}

/**
 * dynamic proxy
 */

@FunctionalInterface
interface Day12ParentClass {
    void get();
}

//@MyAnnotation
class Day12Annotations implements Day12ParentClass {
//    @MyAnnotation
    private String name;

    @Override
    @MyAnnotation
    public void get() {

    }
}
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation{}


/**
 *  1. download database
 *      sql server
 *      mysql
 *      oracle
 *      postgresql
 *      sqlite
 *      derby / h2
 *      ..
 *  2. include spring data jpa + database connection dependencies into pom.xml
 *  3. CRUD
 */