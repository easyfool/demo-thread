package com.wangfengbabe.demo.thread.philosopher;

public class Test {

    public static void main(String[] args) {
        Fork fork = new Fork();
        for (int i = 0; i < 5; i++) {
            new Thread(new Philosopher(i, fork)).start();
        }
    }

}
