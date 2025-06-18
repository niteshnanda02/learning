package org.example.atlassian.ratelimiter.factory;


import org.example.atlassian.ratelimiter.algorithm.FixedWindowCounter;
import org.example.atlassian.ratelimiter.algorithm.RateLimiter;
import org.example.atlassian.ratelimiter.enums.RateLimiterEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RateLimiterFactoryTest {
    private RateLimiterFactory rateLimiterFactory;
    // Add test methods here to test the RateLimiterFactory functionality

    @Test
    public void testCreateRateLimiter() {
        // Example test case for creating a rate limiter
        // You can implement specific tests for each type of rate limiter
        // For example, create a FixedWindowRateLimiter and check its properties
         RateLimiter rateLimiter = RateLimiterFactory.getRateLimiter(RateLimiterEnum.FIXED, 100, 60000);
         assertNotNull(rateLimiter);
         assertTrue(rateLimiter instanceof FixedWindowCounter);
    }

}