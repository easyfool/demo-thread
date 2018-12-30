package com.wangfengbabe.demo.thread.interrupt;

import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {
        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("我要休息一会儿...");
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    System.out.println("我被终止了...");
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.interrupted());//会重置interrupt标志位
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().isInterrupted());
                    System.out.println(Thread.currentThread().getState());
                }

            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //TODO 这里不好，正确的封装原则是：除非拥有某个线程，否则不能对该线程进行操控。
                //线程由Thread对象表示，并且像其他对象一样可以自由共享
                //线程有一个相应的所有者：即创建该线程的类
                t1.interrupt();
            }

        });
        t1.start();
        t2.start();

    }

}
