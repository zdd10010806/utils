package com.zdd.jvm;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

public class DirectBufferOOM {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1024; i++) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1204);
            System.out.println(i);
            byteBuffer = null;
            System.gc();
        }
        TimeUnit.MINUTES.sleep(10);
    }
}
