package com.ysj.concurrency.conc.homewordimpl;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这
 * 个方法的返回值后，退出主线程?
 *
 * callable方式实现
 */
public class CallableImpl {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<Integer> task = () -> sum();
        Future<Integer> future = executorService.submit(task);
        System.out.println("异步计算结果为：" + future.get());
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + "  ms");
        executorService.shutdown();

    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }

}
