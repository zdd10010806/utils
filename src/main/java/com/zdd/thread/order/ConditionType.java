package com.zdd.thread.order;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.*;
import org.joda.time.DateTime;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

/**
 * Created by ronghua.zhao on 15-12-16.
 * 线程1调用reentrantLock.lock时，线程被加入到AQS的等待队列中。
 * 线程1调用await方法被调用时，该线程从AQS中移除，对应操作是锁的释放。
 * 接着马上被加入到Condition的等待队列中，以为着该线程需要signal信号。
 * 线程2，因为线程1释放锁的关系，被唤醒，并判断可以获取锁，于是线程2获取锁，并被加入到AQS的等待队列中。
 * 线程2调用signal方法，这个时候Condition的等待队列中只有线程1一个节点，于是它被取出来，并被加入到AQS的等待队列中。 注意，这个时候，线程1 并没有被唤醒。
 * signal方法执行完毕，线程2调用reentrantLock.unLock()方法，释放锁。这个时候因为AQS中只有线程1，于是，AQS释放锁后按从头到尾的顺序唤醒线程时，线程1被唤醒，于是线程1回复执行。
 * 直到释放所整个过程执行完毕。
 * 可以看到，整个协作过程是靠结点在AQS的等待队列和Condition的等待队列中来回移动实现的，Condition作为一个条件类，很好的自己维护了一个等待信号的队列，并在适时的时候将结点加入到AQS的等待队列中来实现的唤醒操作。
 */
public class ConditionType {
    private  Lock lock = new ReentrantLock();
    private  Condition conditionA = lock.newCondition();
    private  Condition conditionB = lock.newCondition();
    private  Condition conditionC = lock.newCondition();
    private  String type = "A";


    public  void A() {
        lock.lock();
        try {
            while (!type.equals("A")) {
                try {
                    conditionA.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " 正在打印A");
            type = "B";
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public  void B() {
        lock.lock();
        try {
            while (!type.equals("B")) {
                try {
                    conditionB.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " 正在打印B");
            type = "C";
            conditionC.signal();
        } finally {
            lock.unlock();
        }
    }

    public  void C() {
        lock.lock();
        try {
            while (!type.equals("C")) {
                try {
                    conditionC.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " 正在打印C");
            type = "A";
            conditionA.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        final ConditionType conditionType = new ConditionType();
        ListenableFuture futureA = service.submit(new Callable<String>() {
            public String call() throws InterruptedException {
                Thread.sleep(1000);
                conditionType.A();
                return "A";
            }
        });


        ListenableFuture futureB = service.submit(new Callable<String>() {
            public String call() throws InterruptedException {
                Thread.sleep(1000);
                conditionType.B();
                return "B";
            }
        });

        ListenableFuture futureC = service.submit(new Callable<String>() {
            public String call() throws InterruptedException {
                Thread.sleep(1000);
                conditionType.C();
                return "C";
            }
        });

        final ListenableFuture allFutures = Futures.allAsList(futureA, futureB, futureC);



        System.out.println( allFutures.get());
        service.shutdown();

    }



}
