package org.example.atlassian.ratelimiter.algorithm;

public class FixedWindowCounter implements RateLimiter{
    private final long maxRequests;
    private final long windowSizeInSeconds;
    private long requestCount;
    private long currentWindowStart;

    public FixedWindowCounter(long maxRequests, long windowSizeInSeconds) {
        this.maxRequests = maxRequests;
        this.windowSizeInSeconds = windowSizeInSeconds;
        this.requestCount = 0;
        this.currentWindowStart = System.currentTimeMillis() / 1000; // Convert to seconds
    }



    @Override
    public boolean allowRequest() {
        System.out.println("Request Count: " + requestCount + ", Max Requests: " + maxRequests + ", Current Window Start: " + currentWindowStart);
    long currentTime = System.currentTimeMillis() / 1000; // Convert to seconds
        if (currentTime-currentWindowStart>= windowSizeInSeconds) {
            // Reset the counter and start a new window
            requestCount = 0;
            currentWindowStart = currentTime;
        }
        if (requestCount<maxRequests){
            requestCount++;
            return true;
        }
        return false;
    }
}
