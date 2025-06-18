package org.example.atlassian.ratelimiter;


public class ThrotleRule {
    private long maxRequests;
    private long windowSizeInSeconds; // in milliseconds

    public ThrotleRule() {
        this.maxRequests=10;
        this.windowSizeInSeconds =10; // 1 second
    }

    public ThrotleRule(long maxRequests, long timeWindow) {
        this.maxRequests = maxRequests;
        this.windowSizeInSeconds = timeWindow;
    }

    public long getMaxRequests() {
        return maxRequests;
    }

    public long getTimeWindow() {
        return windowSizeInSeconds;
    }
}
