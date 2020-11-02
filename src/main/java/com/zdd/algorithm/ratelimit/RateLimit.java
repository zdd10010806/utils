package com.zdd.algorithm.ratelimit;

public interface RateLimit {

    /**
     * can pass
     */
    boolean canPass() throws BlockException;
}
