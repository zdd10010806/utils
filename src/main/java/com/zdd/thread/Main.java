package com.zdd.thread;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class Main {
    public static void main(String[] args) throws InterruptedException {
        String prefix = "test";
//        ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder().setNameFormat(prefix + "%d").setUncaughtExceptionHandler((thread, throwable) -> log.error("ThreadPool {} got  exception", thread, throwable));
        ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder().setNameFormat(prefix + "%d");
        ExecutorService threadPool = Executors.newFixedThreadPool(1, threadFactoryBuilder.build());
        //提交10个任务到线程池处理，第5个任务会抛出运行时异常
//        IntStream.rangeClosed(1, 10).forEach(i -> threadPool.execute(() -> {
//            if (i == 5)
//                throw new RuntimeException("error");
//            log.info("I'm done : {}", i);
//        }));

        IntStream.rangeClosed(1, 10).forEach(i -> threadPool.submit(() -> {
            if (i == 5)
                throw new RuntimeException("error");
            log.info("I'm done : {}", i);
        }));


        List<Future> tasks = IntStream.rangeClosed(1, 10).mapToObj(i -> threadPool.submit(() -> {
            if (i == 5)
                throw new RuntimeException("error");
            log.info("I'm done : {}", i);
        })).collect(Collectors.toList());

        tasks.forEach(task -> {
            try {
                task.get();
            } catch (Exception e) {
                log.error("Got exception", e);
            }
        });

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);


    }

    public static void sayHi(String name) {
        String x = "[ thread name " + Thread.currentThread().getName() + " method " + name;
        System.out.println(x);
//        throw new RuntimeException( x +" exception");
    }


}
