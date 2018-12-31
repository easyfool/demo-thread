package com.wangfengbabe.demo.thread.deadlock;

/**
 * 两个线程试图以不同顺序来获得相同的锁，操作是交错执行，那么会造成死锁
 */
public class LeftRightDeadLock {

    private final Object leftLock = new Object();
    private final Object rightLock = new Object();

    public void leftRight() {
        synchronized (leftLock) {
            synchronized (rightLock) {
//                doSomething();
            }
        }
    }

    public void rightLeft() {
        synchronized (rightLock) {
            synchronized (leftLock) {
//                doSomethingElse();
            }
        }
    }


}
