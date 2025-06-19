package org.example.atlassian.ratelimiter.factory;

import org.example.atlassian.ratelimiter.algorithm.*;
import org.example.atlassian.ratelimiter.enums.RateLimiterEnum;
import org.example.atlassian.ratelimiter.exception.RateLimiterStrategyNotFoundException;

public class RateLimiterFactory {
    public static RateLimiter getRateLimiter(RateLimiterEnum rateLimiterEnum, long maxRequest, long windowSizeInSeconds){
        return switch (rateLimiterEnum){
            case TOKEN -> new TokenBucket(maxRequest, windowSizeInSeconds);
            case SLIDING_WINDOW_LOG -> new SlidingWindowLogCounter(maxRequest, windowSizeInSeconds);
            case FIXED -> new FixedWindowCounter(maxRequest, windowSizeInSeconds);
            case CREDIT_TOKEN_BUCKET -> new CreditBasedTokenBucket(maxRequest, windowSizeInSeconds, maxRequest/2);
            case LEAK -> new LeakyBucket(maxRequest, maxRequest/windowSizeInSeconds);
            default -> throw new RateLimiterStrategyNotFoundException(rateLimiterEnum.name());
        };
    }
}
