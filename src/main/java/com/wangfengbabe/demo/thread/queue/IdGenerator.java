package com.wangfengbabe.demo.thread.queue;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.Data;

@Data
public class IdGenerator {

    private volatile AtomicInteger serial = new AtomicInteger(0);

    public int getSerial() {
        return serial.incrementAndGet();
    }

}
