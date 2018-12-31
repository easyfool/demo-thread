package com.wangfengbabe.demo.thread.interview;

class MyTest1 {

    private volatile boolean runned = false;

    private void loop(int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(Thread.currentThread().getName() + "第 " + i + " 次loop ");
        }

    }

    public synchronized void loop10() {
        if (runned) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        loop(10);
        runned = true;
        notifyAll();
    }

    public synchronized void loop20() {
        if (!runned) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        loop(20);
        runned = false;
    }
}

class MyRunnable1 implements Runnable {

    private MyTest1 myTest1;

    public MyRunnable1(MyTest1 myTest1) {
        this.myTest1 = myTest1;
    }

    @Override
    public void run() {
        myTest1.loop10();
    }
}


class MyRunnable2 implements Runnable {

    private MyTest1 myTest1;

    public MyRunnable2(MyTest1 myTest1) {
        this.myTest1 = myTest1;
    }

    @Override
    public void run() {
        myTest1.loop20();
    }
}

public class ThreaTest1 {

    public static void main(String[] args) {
        final int LOOP_COUNT = 50;
        MyTest1 myTest1 = new MyTest1();
        for (int i = 0; i < LOOP_COUNT; i++) {
            MyRunnable1 runnable1 = new MyRunnable1(myTest1);
            MyRunnable2 runnable2 = new MyRunnable2(myTest1);
            new Thread(runnable1, "10次循环 ").start();
            new Thread(runnable2, "20次循环 ").start();
        }
    }

}
