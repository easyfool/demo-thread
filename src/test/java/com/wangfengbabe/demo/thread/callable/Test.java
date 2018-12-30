package com.wangfengbabe.demo.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        Future<String> future = exec.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(1);
                return "done";
            }
        });

        exec.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("等待future返回时，执行另外一个任务.");
            }
        });
        try {
            String s = future.get();
            System.out.println("执行结果:" + s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        exec.shutdown();


    }

}
