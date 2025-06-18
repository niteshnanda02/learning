package org.example.atlassian.ratelimiter.algorithm;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowLogCounter implements RateLimiter{
    private final long maxRequests;
    private final long windowSizeInSeconds;
    private final Queue<Long> queue;

    public SlidingWindowLogCounter(long maxRequests, long windowSizeInSeconds) {
        this.maxRequests = maxRequests;
        this.windowSizeInSeconds = windowSizeInSeconds;
        this.queue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public synchronized boolean allowRequest() {
        System.out.println(queue.size() + " " + maxRequests + " " + windowSizeInSeconds);
        long currentTime = System.currentTimeMillis(); // Convert to seconds
        // Remove timestamps outside the sliding window
        checkAndRemoveOldRequests(currentTime);
        if (queue.size() < maxRequests) {
            queue.add(currentTime);
            return true;
        }
        return false;
    }

    private void checkAndRemoveOldRequests(long currentTime) {
        if (queue.isEmpty())
            return;
        long time = (currentTime - queue.peek())/1000;
        while(!queue.isEmpty() && time>=windowSizeInSeconds){
            queue.poll();
            time = (currentTime - queue.peek())/1000;
        }
    }
}
