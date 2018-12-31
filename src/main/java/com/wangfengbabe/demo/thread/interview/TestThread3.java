package com.wangfengbabe.demo.thread.interview;

import lombok.Data;

@Data
class MyTest3 {

    private int number;

    public synchronized void increase() {
        number++;
    }

    public synchronized void decrease() {
        number--;

    }
}

class IncreaseRunnable implements Runnable {

    private MyTest3 myTest3;

    public IncreaseRunnable(MyTest3 myTest3) {
        this.myTest3 = myTest3;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + "  增加1.");
        myTest3.increase();
        System.out.println(
                currentThreadName + "，增加后，number=" + myTest3.getNumber());

    }
}

class DecreaseRunnable implements Runnable {

    private MyTest3 myTest3;

    public DecreaseRunnable(MyTest3 myTest3) {
        this.myTest3 = myTest3;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + "  减少1.");
        myTest3.decrease();
        System.out.println(
                currentThreadName + "，减少后，number=" + myTest3.getNumber());

    }
}

public class TestThread3 {

    public static void main(String[] args) {
        MyTest3 test3 = new MyTest3();
        new Thread(new IncreaseRunnable(test3)).start();
        new Thread(new IncreaseRunnable(test3)).start();
        new Thread(new DecreaseRunnable(test3)).start();
        new Thread(new DecreaseRunnable(test3)).start();

    }

}
