package com.wangfengbabe.demo.thread.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestCountDownLatch {

    /**
     * 每个工作线程需要在启动门上等待，确保所有线程都就绪后才开始执行。
     * 每个线程将调用结束门的countDown方法减1，使主线程高效的等待直到所有工作线程都执行完成。
     */

    public static long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
            t.start();

        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;

    }


    public static void main(String[] args) throws InterruptedException {
        long time = timeTasks(5, new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        System.out.println("耗时" + time);
    }

}
