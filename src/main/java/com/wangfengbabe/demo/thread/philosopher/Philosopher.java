package com.wangfengbabe.demo.thread.philosopher;

import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {

    private int name;
    private Fork fork;

    public Philosopher(int name, Fork fork) {
        this.name = name;
        this.fork = fork;
    }

    @Override
    public void run() {
        while (true) {
            thinking();
            fork.takeFork(name);
            eating();
            fork.putFork(name);
        }

    }

    private void eating() {
        System.out.println(name + "eating...");
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private void thinking() {
        System.out.println(name + "thinking...");
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
