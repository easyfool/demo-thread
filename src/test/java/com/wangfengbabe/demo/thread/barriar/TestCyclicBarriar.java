package com.wangfengbabe.demo.thread.barriar;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;


public class TestCyclicBarriar {

    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(4, new Runnable() {
            @Override
            public void run() {
                System.out.println("do another");
            }
        });
        for (int i = 0; i < N; i++) {
            Thread t = new Thread(new Writer(barrier));
            t.start();
        }
    }

    static class Writer implements Runnable {

        private CyclicBarrier barrier;

        public Writer(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据.");
            try {
                //以休眠来模拟写入数据操作
                TimeUnit.MILLISECONDS.sleep(10);
                System.out.println("线程" + Thread.currentThread().getName() + "完成写入数据.");
                barrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入数据完成，准备处理其他事情.");

        }
    }
}
