package com.wangfengbabe.demo.thread.interrupt;

/**
 * 线程的new状态表示还未调用start方法，还未真正启动
 * 这个状态下调用中断方法来中断线程的时候，Java认为毫无意义，所以并不会设置线程的中断标识位，什么事也不会发生
 * 对于处于new和terminated状态的线程对于中断是屏蔽的，也就是说中断操作对这两种状态下的线程是无效的
 */
public class TestWhenThreadNew {

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        System.out.println(t.getState());
        t.interrupt();
        System.out.println(t.isInterrupted());
    }

}
