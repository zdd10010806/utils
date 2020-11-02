package com.zdd.algorithm.ratelimit;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.*;


@Slf4j
public class TokenBucketRateLimit implements RateLimit, Runnable {

    /**
     * token 生成 速率 （每秒）
     */
    private Integer tokenLimitSecond;

    /**
     * 令牌桶队列
     */
    private BlockingQueue<String /* token */> tokenBucket;

    private static final String TOKEN = "__token__";

    private ScheduledExecutorService scheduledExecutorService;

    public TokenBucketRateLimit(Integer bucketSize, Integer tokenLimitSecond) {
        this.tokenLimitSecond = tokenLimitSecond;
        this.tokenBucket = new LinkedBlockingDeque<>(bucketSize);

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        long interval = (1000 * 1000 * 1000) / tokenLimitSecond;
        scheduledExecutorService.scheduleAtFixedRate(this, 0, interval, TimeUnit.NANOSECONDS);
    }

    @Override
    public boolean canPass() throws BlockException {
        String token = tokenBucket.poll();
        if (StringUtils.isEmpty(token)) {
            throw new BlockException();
        }
        return true;
    }

    @Override
    public void run() {
        if (tokenBucket.remainingCapacity() == 0) {
            return;
        }
        tokenBucket.offer(TOKEN);
    }
}
