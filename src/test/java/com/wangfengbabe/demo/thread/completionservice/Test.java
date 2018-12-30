package com.wangfengbabe.demo.thread.completionservice;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {

    public static void main(String[] args) {
        final int TASK_NUM = 5;
        ExecutorService executor = Executors.newFixedThreadPool(3);

        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);
        for (int i = 0; i < TASK_NUM; i++) {
            final int result = i;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return result;
                }
            });

        }
        executor.shutdown();

        for (int i = 0; i < TASK_NUM; i++) {
            Future<Integer> take = null;
            try {
                take = completionService.take();
                Integer integer = take.get();
                System.out.println(integer);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }

}
