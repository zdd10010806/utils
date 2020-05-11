package com.zdd.jol;
import org.openjdk.jmh.annotations.*;
import sun.misc.Contended;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Fork(value = 1, jvmArgsAppend = "-XX:-RestrictContended")
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Threads(5)
@Measurement(iterations = 15)
@SuppressWarnings("WeakerAccess")
public class FalseSharingTest {

    private static final int NUMBER_OF_THREADS = 5;

    private static long getInitLong() {
        return ThreadLocalRandom.current().nextLong(1000);
    }

    @Benchmark
    public long testFalseSharing(VolatileLongArray array, ThreadHelper helper) {
        VolatileLong vl = array.v1[helper.threadNumber];
        return vl.v++;
    }

    @Benchmark
    public long testPadded(VolatileLongArray array, ThreadHelper helper) {

        PaddedVolatileLong vl = array.v2[helper.threadNumber];
        return vl.v++;
    }

    @Benchmark
    public long testContended(VolatileLongArray array, ThreadHelper helper) {

        ContendedVolatileLong vl = array.v3[helper.threadNumber];
        return vl.v++;
    }

    private static final class VolatileLong {
        private volatile long v = getInitLong();
    }

    private static final class PaddedVolatileLong {

        private volatile long v = getInitLong();
        private volatile long p1, p2, p3, p4, p5, p6;
    }

    @Contended
    private static final class ContendedVolatileLong {
        private volatile long v = getInitLong();
    }

    @State(Scope.Benchmark)
    public static class VolatileLongArray {

        private final VolatileLong[] v1;
        private final PaddedVolatileLong[] v2;
        private final ContendedVolatileLong[] v3;

        {
            VolatileLong[] v1 = new VolatileLong[NUMBER_OF_THREADS];
            PaddedVolatileLong[] v2 = new PaddedVolatileLong[NUMBER_OF_THREADS];
            ContendedVolatileLong[] v3 = new ContendedVolatileLong[NUMBER_OF_THREADS];

            // 用三个循环来分别对三个数组赋值，一个循环有可能分配的不是连续内存
            for (int i = 0; i < NUMBER_OF_THREADS; i++) {
                v1[i] = new VolatileLong();
            }
            for (int i = 0; i < NUMBER_OF_THREADS; i++) {
                v2[i] = new PaddedVolatileLong();
            }
            for (int i = 0; i < NUMBER_OF_THREADS; i++) {
                v3[i] = new ContendedVolatileLong();
            }

            this.v1 = v1;
            this.v2 = v2;
            this.v3 = v3;
        }
    }

    @State(Scope.Thread)
    @Contended
    public static class ThreadHelper {

        private static final AtomicInteger threadCounter = new AtomicInteger(0);

        // 线程的序列号
        private final int threadNumber = threadCounter.getAndIncrement();
    }
}
