package com.zdd.thread.order;

import java.util.concurrent.locks.LockSupport;

public class ThreadLockSupport {
    static Thread t1 = null;
    static Thread t2 = null;

    public static void main(String[] args) {
        char[] digitals = "1234567".toCharArray();
        char[] letters = "ABCDEFG".toCharArray();

        t1 = new Thread(() -> {
            while (true) {
                for (char c : digitals) {
                    System.out.println(c);
                    LockSupport.unpark(t2);
                    LockSupport.park();//阻塞当前线程
                }
            }
        },"t1");

        t2 = new Thread(() -> {
            while (true) {
                for (char c : letters) {
                    LockSupport.park();
                    System.out.println(c);
                    LockSupport.unpark(t1);
                }
            }
        },"t2");

        t1.start();
        t2.start();
    }
}
