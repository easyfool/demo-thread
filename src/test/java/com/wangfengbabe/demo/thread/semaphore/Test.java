package com.wangfengbabe.demo.thread.semaphore;


import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import org.junit.internal.runners.statements.RunAfters;

public class Test {

    public static void main(String[] args) {
        int workersCount = 8;
        int machineCount = 5;
        Semaphore semaphore = new Semaphore(machineCount);
        for (int i = 0; i < workersCount; i++) {
            Runnable worker = new Worker(i, semaphore);
            new Thread(worker).start();
        }
    }

    static class Worker implements Runnable {

        private int id;

        private Semaphore semaphore;

        public Worker(int id, Semaphore semaphore) {
            this.id = id;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人 " + id + " 获取机器.");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("获取发生异常");
            }
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                semaphore.release();
            }
            System.out.println("工人" + id + "释放机器.");
            semaphore.release();


        }
    }

}
