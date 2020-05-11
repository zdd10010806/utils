package com.zdd.thread.order;

public class ThreadWait {
    public static void main(String[] args) {
        final Object o = new Object();
        char[] digitals = "1234567".toCharArray();
        char[] letters = "ABCDEFG".toCharArray();
        new Thread(()->{
            synchronized(o){
                for (char c : digitals){
                    System.out.print(c);
                    try {
                        o.notifyAll();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        },"t1").start();
        new Thread(()->{
            synchronized(o){
                for (char c : letters){
                    System.out.print(c);
                    try {
                        o.notifyAll();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        },"t2").start();
    }
}
