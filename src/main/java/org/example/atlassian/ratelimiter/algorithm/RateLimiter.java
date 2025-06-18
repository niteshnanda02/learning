package org.example.atlassian.ratelimiter.algorithm;

public interface RateLimiter {
    boolean allowRequest();
}
