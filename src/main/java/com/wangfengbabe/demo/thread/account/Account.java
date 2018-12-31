package com.wangfengbabe.demo.thread.account;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private double balance;
    private ReentrantLock lock = new ReentrantLock(true);
    private Condition save = lock.newCondition();
    private Condition draw = lock.newCondition();

    public Account(double balance) {
        this.balance = balance;
    }

    public void saving(double money, String name) {
        lock.lock();
        try {
            if (money > 0) {
                this.balance += money;
                System.out.println(name + "存款" + money + ",当前余额为" + balance);
            }
            draw.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public void drawing(double money, String name) {
        lock.lock();
        try {
            if (this.balance - money < 0) {
                draw.await();
            }
            this.balance -= money;
            System.out.println(name + "取款" + money + ",当前余额为" + balance);
            save.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}
