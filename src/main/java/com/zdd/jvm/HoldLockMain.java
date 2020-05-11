package com.zdd.jvm;

import java.util.Random;

public class HoldLockMain {

    public static Object[] lock = new Object[10];
    public static Random random = new Random();

    static {
        for (int i = 0; i < lock.length; i++) {
            lock[i] = new Object();
        }
    }

    public static class HoldLockTask implements Runnable {
        private int i;

        public HoldLockTask(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                while (true){
                    synchronized (lock[i]){
                        if (i %2 ==0){
                            lock[i].wait(random.nextInt(10));
                        } else {
                            lock[i].notifyAll();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < lock.length * 2; i++) {
            new Thread(new HoldLockTask(i /2)).start();
        }
    }
}
