package com.wangfengbabe.demo.thread.philosopher;

public class Fork {

    /**
     * 5只筷子初始值都没有被用
     */
    private boolean[] used = {false, false, false, false, false};

    /**
     * 只有当左右手的筷子都未被使用时，才允许获取筷子，且必须同时获取左右手筷子
     */
    public synchronized void takeFork(int i) {
        while (used[i] || used[(i + 1) % 5]) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        used[i] = true;

        used[(i + 1) % 5] = true;
    }

    /**
     * 必须同时释放左右手的筷子
     */
    public synchronized void putFork(int i) {
        used[i] = false;
        used[(i + 1) % 5] = false;
        notifyAll();
    }

}
