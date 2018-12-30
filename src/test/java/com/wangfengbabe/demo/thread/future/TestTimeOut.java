package com.wangfengbabe.demo.thread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestTimeOut {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Object> future = executor.submit(new Callable<Object>() {

            @Override
            public Object call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(10);
                System.out.println("执行完成.");
                return null;
            }
        });

        executor.shutdown();
        try {
            Object o = future.get(5, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
//            e.printStackTrace();
            System.out.println("执行任务，time out,cancel task");
            future.cancel(true);
        }


    }

}
