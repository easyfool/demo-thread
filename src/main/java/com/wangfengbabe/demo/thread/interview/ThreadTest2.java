package com.wangfengbabe.demo.thread.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class MyTest2 {

    private final ReentrantLock lock = new ReentrantLock();
    Condition run10 = lock.newCondition();
    Condition run20 = lock.newCondition();
    private volatile boolean hasRunned10 = false;

    public void run10() {
        lock.lock();
        try {
            while (hasRunned10) {
                run10.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " loop " + i);
            }
            hasRunned10 = true;
            run20.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void run20() {
        lock.lock();
        try {
            while (!hasRunned10) {
                run20.await();
            }
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + " loop " + i);
            }
            hasRunned10 = false;
            run10.signalAll();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


}

class Runnable10 implements Runnable {

    private MyTest2 myTest2;

    public Runnable10(MyTest2 myTest2) {
        this.myTest2 = myTest2;
    }

    @Override
    public void run() {
        while (true) {
            myTest2.run10();
        }
    }
}

class Runnable20 implements Runnable {

    private MyTest2 myTest2;

    public Runnable20(MyTest2 myTest2) {
        this.myTest2 = myTest2;
    }

    @Override
    public void run() {
        while (true) {
            myTest2.run20();
        }
    }
}


public class ThreadTest2 {

    public static void main(String[] args) {
        MyTest2 myTest2 = new MyTest2();
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable10(myTest2), "10次循环 ").start();
            new Thread(new Runnable20(myTest2), "20次循环 ").start();
        }
    }
}
