package com.wangfengbabe.demo.thread.interrupt;

import java.util.concurrent.TimeUnit;

public class TestWhenRunnable {

    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable());
        t.start();

        System.out.println(t.getState());

        t.interrupt();

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(t.isInterrupted());

        System.out.println(t.getState());

    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("interrupt and exit");
                    break;

                }

            }
        }
    }

}
