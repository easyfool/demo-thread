package com.wangfengbabe.demo.thread.account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        List<Runnable> list = new ArrayList<>();
        Account account = new Account(1000);
        for (int i = 0; i < 10; i++) {
            list.add(new SavingRunnable("save user " + i, 50, account));
            list.add(new DrawingRunnable("draw user " + i, 100, account));
        }
        Collections.shuffle(list);
        for (Runnable runnable : list) {
            pool.submit(runnable);
        }

        pool.shutdown();

    }

}
