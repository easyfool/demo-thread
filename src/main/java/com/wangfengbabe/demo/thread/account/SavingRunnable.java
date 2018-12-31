package com.wangfengbabe.demo.thread.account;

public class SavingRunnable implements Runnable {

    private String name;
    private double money;
    private Account account;

    public SavingRunnable(String name, double money,
            Account account) {
        this.name = name;
        this.money = money;
        this.account = account;
    }

    @Override
    public void run() {
        account.saving(money, name);
    }
}
