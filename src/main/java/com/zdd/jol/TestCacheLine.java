package com.zdd.jol;

import sun.misc.Contended;

import java.util.concurrent.ThreadLocalRandom;

public class TestCacheLine {

    private static long getInitLong() {
        return ThreadLocalRandom.current().nextLong(1000);
    }

    static final class VolatileLong {
        private volatile long v = getInitLong();
    }

    static final class PaddedVolatileLong {

        private volatile long v = getInitLong();
        private volatile long p1, p2, p3, p4, p5, p6;
    }

    @Contended
    static final class ContendedVolatileLong {
        private volatile long v = getInitLong();
    }
}
