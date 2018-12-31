package com.wangfengbabe.demo.thread.queue;

public class Window {

    private int windowNo;

    private IdGenerator generator;


    public Window(int windowNo, IdGenerator generator) {
        this.windowNo = windowNo;
        this.generator = generator;
    }

    public void call() {
        System.out.println("window No. " + windowNo + ", call No. " + generator.getSerial());
    }
}
