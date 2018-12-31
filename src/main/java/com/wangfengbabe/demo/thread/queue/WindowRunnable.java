package com.wangfengbabe.demo.thread.queue;

import java.util.concurrent.TimeUnit;

public class WindowRunnable implements Runnable {

    private Window window;

    public WindowRunnable(Window window) {
        this.window = window;
    }

    @Override
    public void run() {
        while (true) {
            window.call();
            //模拟办事
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
