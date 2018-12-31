package com.wangfengbabe.demo.thread.account;

public class DrawingRunnable implements Runnable {

    private String name;
    private double money;
    private Account account;

    public DrawingRunnable(String name, double money,
            Account account) {
        this.name = name;
        this.money = money;
        this.account = account;
    }

    @Override
    public void run() {
        account.drawing(money, name);
    }

}
