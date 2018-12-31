package com.wangfengbabe.demo.thread.queue;

public class Test {

    public static void main(String[] args) {
        IdGenerator generator = new IdGenerator();
        final int windowCount = 3;
        for (int i = 0; i < windowCount; i++) {
            new Thread(new WindowRunnable(new Window(i, generator))).start();

        }

    }

}
