package com.example.java92022.week3;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 *  Template
 */

/**
 *  Composition
 */
class Day13Node {
    Day13Node left;
    Day13Node right;
}
class Day13Employee {
    List<Day13Employee> employees;
}
/**
 *  Dynamic proxy (JDK Proxy / Cglib Proxy)
 */
class Day13StudentProxyTest {
    public static void main(String[] args) {
        Day13StudentImpl1 studentImpl1 = new Day13StudentImpl1();
        Day13Student student = (Day13Student) Proxy.newProxyInstance(
                Day13StudentProxyTest.class.getClassLoader(),
                new Class[]{Day13Student.class},
                new Day13StudentInvocationHandler(studentImpl1)
        );
        student.get1();
        student.get2();
        student.get3();
        student.get4();
        student.get5();
    }
}
class Day13StudentInvocationHandler implements InvocationHandler {
    private final Day13Student day13Student;

    public Day13StudentInvocationHandler(Day13Student day13Student) {
        this.day13Student = day13Student;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Annotation[] annotations = method.getDeclaredAnnotations();
        for(Annotation annotation: annotations) {
            if(annotation.annotationType() == Before.class) {
                System.out.print("this is invoke, before");
            } else if(annotation.annotationType() == After.class) {
                System.out.print("this is invoke, after");
            }
        }
        Object res = method.invoke(day13Student, args);
        return res;
    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Before {}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface After {}

interface Day13Student {
    @Before
    void get1();
    @Before
    void get2();
    @Before
    void get3();
    @After
    void get4();
    @After
    void get5();
}

class Day13StudentImpl1 implements Day13Student{
    public void get1() {
        //before
        System.out.println("this is student get 1");
        //after
    }

    public void get2() {
        //..
        System.out.println("this is student get 2");
        //..
    }
    public void get3() {
        //..
        System.out.println("this is student get 3");
        //..
    }

    @Override
    public void get4() {
        System.out.println("this is student get 4");
    }

    @Override
    public void get5() {
        System.out.println("this is student get 5");
    }
}
class Day13StudentImpl1Sub extends Day13StudentImpl1 {

}

class Day13Department {

    private Day13Employee2 employee2;

    @Override
    public String toString() {
        return "Day13Department{" +
                "employee2=" + employee2 +
                '}';
    }
}
class Day13Employee2 {
    @Override
    public String toString() {
        return "Day13Employee2{}";
    }
}
class Day13ReflectionTest {

    public static void main(String[] args) throws Exception{
        Day13Department day13Department = new Day13Department();
        Class<?> clazz = day13Department.getClass();
        Field[] fields = clazz.getDeclaredFields();
        fields[0].setAccessible(true);
        fields[0].set(day13Department, new Day13Employee2());
        System.out.println(day13Department);
    }
}