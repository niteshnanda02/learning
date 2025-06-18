package org.example.atlassian.ratelimiter.algorithm;

public class TokenBucket implements RateLimiter {
    private final long maxBucketSize;
    private final double reffilRate;
    private double currenBucketSize;
    private long lastRefillTimeStamp;

    public TokenBucket(long maxBucketSize, long windowTimeInSeconds) {
        this.maxBucketSize = maxBucketSize;
        this.reffilRate = 1.0*maxBucketSize/windowTimeInSeconds;
        this.currenBucketSize = maxBucketSize;
        lastRefillTimeStamp = System.currentTimeMillis()/1000;
    }

    @Override
    public synchronized boolean allowRequest() {
        refill();
        if (currenBucketSize >=1) {
            currenBucketSize--;
            return true;
        }
        return false;

    }

    private synchronized void refill() {
        long currentTime = System.currentTimeMillis()/1000;
        // 1sec-> 10 tokens
        double tokenToAdd = (double) (currentTime - lastRefillTimeStamp) * reffilRate;
        System.out.println("Before refill : " + currenBucketSize+" "+reffilRate+" "+tokenToAdd);
        currenBucketSize = Math.min(maxBucketSize, currenBucketSize + tokenToAdd); // token shouldn't been more than that
        System.out.println("After refill : " + currenBucketSize);
        lastRefillTimeStamp = currentTime;
    }

    public long getMaxBucketSize() {
        return maxBucketSize;
    }

    public double getCurrenBucketSize() {
        return currenBucketSize;
    }
}
