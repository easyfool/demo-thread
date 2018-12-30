package com.wangfengbabe.demo.thread.cancel;

import java.util.concurrent.TimeUnit;

public class TestCancel {

    public static void main(String[] args) {
        MsgGenerator generator = new MsgGenerator();
        new Thread(generator).start();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("interruped");
        } finally {
            generator.cancel();
        }

    }

    static class MsgGenerator implements Runnable {

        private volatile boolean canceled;


        @Override
        public void run() {
            while (!canceled) {
                System.out.println("hello");
            }

        }

        public void cancel() {
            this.canceled = true;
        }
    }

}
