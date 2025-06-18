package org.example.atlassian.ratelimiter.algorithm;

public class SlidingWindowCounter implements RateLimiter{
    private final long maxRequest;
    private final long windowSizeInSeconds;
    private long currentWindowStart;
    private long previousWindowCount;
    private long currentWindowCount;

    public SlidingWindowCounter(long maxRequest, long windowSizeInSeconds) {
        this.maxRequest = maxRequest;
        this.windowSizeInSeconds = windowSizeInSeconds;
        this.currentWindowStart = System.currentTimeMillis() / 1000; // Convert to seconds
        this.previousWindowCount = 0;
        this.currentWindowCount = 0;

    }

    @Override
    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis() / 1000; // Convert to seconds
        long timePassedInWindow = currentTime-currentWindowStart;

        if (timePassedInWindow>=windowSizeInSeconds){
            if (timePassedInWindow>=windowSizeInSeconds+1)
                previousWindowCount=0;
            else
                previousWindowCount = currentWindowCount;
            currentWindowCount = 0;
            currentWindowStart = currentTime;
            timePassedInWindow = 0;
        }

        // calculate the weight count of request
        double weightCount = previousWindowCount*((double) (windowSizeInSeconds - timePassedInWindow) /windowSizeInSeconds)+currentWindowCount;
        if (weightCount<maxRequest){
            currentWindowCount++;
            return true;
        }
        return false;
    }
}
