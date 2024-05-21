package com.javarush.springbootdouble;

public class Example {
    public static void main(String[] args) throws InterruptedException {

//        HashMap<Integer, Integer> map = new HashMap<>();
//
//        for (int i = 0; i < 9; i++) {
//            map.put(16 * i + 1, null);
//        }

//        A a = new A();
//        Thread thread = new Thread(a);
//        thread.start();
//        thread.join();
//
//        B b = new B();
//        b.start();
//        b.join();
//
//        new Thread(() -> {
//            while (true) {
//                System.out.println("hello");
//            }
//        }).start();

//        /ExecutorService executorService = Executors.newFixedThreadPool(2);

//        try {
//            Long[] longs = new Long[Integer.MAX_VALUE];
//        } catch (Error error) {
//            System.out.println(error);
//        }
        Thread thread = new Thread();
        thread.stop();
    }


    public static void f(){
        f();
    }
}

class B extends Thread {
    @Override
    public void run() {
    }
}

class A implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("hello");
        }
    }
}

class A1 extends B1 implements AI {

    @Override
    public void method() {
        AI.super.method();
    }
}

abstract class B1 implements AI{
    public abstract void method();
}

interface AI{
    default void method(){}
}


