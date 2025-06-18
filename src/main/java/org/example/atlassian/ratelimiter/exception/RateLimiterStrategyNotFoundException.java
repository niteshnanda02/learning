package org.example.atlassian.ratelimiter.exception;

public class RateLimiterStrategyNotFoundException extends RuntimeException {
    public RateLimiterStrategyNotFoundException(String name) {
        super("No implementation found "+name);
    }
}
