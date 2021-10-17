package com.ysj.concurrency.conc.homewordimpl;

public class NotifyImpl {
    private static final Object lock = new Object();

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        SonThread sonThread = new SonThread();

        sonThread.start();
        synchronized (lock) {
            lock.wait();
        }

        int result = sonThread.getResult();
        System.out.println("异步计算结果：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + "  ms");
    }

    static class SonThread extends Thread {

        private Integer result;

        public Integer getResult() {
            return result;
        }

        @Override
        public void run() {
            synchronized (lock) {
                result = sum();
                lock.notifyAll();
            }
        }
    }
}
