package com.wangfengbabe.demo.thread.semaphore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class BoundedHashSet<T> {

    private final Set<T> set;
    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<>());
        sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if (!wasAdded) {
                sem.release();

            }
        }

    }

    public boolean remove(T o) {
        boolean wasRemoved = false;
        wasRemoved = set.remove(o);
        if (wasRemoved) {
            sem.release();
        }
        return wasRemoved;

    }

    public static void main(String[] args) {
        BoundedHashSet boundedHashSet = new BoundedHashSet(3);
        Thread addThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        System.out.println(
                                Thread.currentThread().getName() + "add:" + boundedHashSet.add(i));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "addThread");

        Thread removeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "remove:" + boundedHashSet
                            .remove(i));
                }
            }
        }, "removeThread");
        addThread.start();
        removeThread.start();

    }


}
