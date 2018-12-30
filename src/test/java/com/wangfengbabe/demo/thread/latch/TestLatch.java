package com.wangfengbabe.demo.thread.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestLatch {

    private static class MyRunnable implements Runnable {

        private CountDownLatch latch;

        public MyRunnable(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                //模拟执行任务
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程" + Thread.currentThread().getName() + "执行完成.");
            latch.countDown();
        }
    }

    public static void main(String[] args) {
        final int THREAD_NUM = 5;
        final CountDownLatch latch = new CountDownLatch(THREAD_NUM);
        Runnable runnable = new MyRunnable(latch);
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(runnable).start();
        }
        try {
            System.out.println("等待" + THREAD_NUM + "个子线程执行完成...");
            latch.await();
            System.out.println(THREAD_NUM + "个子线程已执行完毕.");
            System.out.println("继续执行主线程.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
