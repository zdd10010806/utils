package com.zdd.thread.order;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTransferQueue {
    public static void main(String[] args) {
        char[] digitals = "1234567".toCharArray();
        char[] letters = "ABCDEFG".toCharArray();

        LinkedTransferQueue<Character> queue = new LinkedTransferQueue<>();
        new Thread(()->{
            try{
                for (char c : digitals){
                    queue.transfer(c);
//                    TimeUnit.MILLISECONDS.sleep(20);
                    System.out.print(queue.take());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(()->{
            try{
                for (char c : letters){
//                    TimeUnit.MILLISECONDS.sleep(20);
                    System.out.print( queue.take());
                    queue.transfer(c);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        },"t2").start();

    }
}
