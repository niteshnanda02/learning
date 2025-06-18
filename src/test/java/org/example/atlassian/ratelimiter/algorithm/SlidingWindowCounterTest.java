package org.example.atlassian.ratelimiter.algorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SlidingWindowCounterTest {
    private SlidingWindowCounter slidingWindowCounter;
    private static final long MAX_REQUESTS = 10;
    private static final long MAX_WINDOW_SIZE_SECONDS = 10; // 10 seconds

    @BeforeEach
    public void setUp(){
        slidingWindowCounter = new SlidingWindowCounter(MAX_REQUESTS, MAX_WINDOW_SIZE_SECONDS);
    }

    @Test
    @DisplayName("Test initial request allowance")
    public void testInitialAllowRequest() {
        // Initial request should be allowed
        assertTrue(slidingWindowCounter.allowRequest());
    }

    @Test
    @DisplayName("Test max requests within window")
    public void testMaxRequestsWithInWindow(){
        // Allow requests up to the max limit
        for (int i = 0; i < MAX_REQUESTS; i++) {
            assertTrue(slidingWindowCounter.allowRequest());
        }
        // Next request should be denied
        assertFalse(slidingWindowCounter.allowRequest());
    }

    @Test
    @DisplayName("Test request allowance after window reset")
    public void testRequestAllowedAfterWindowReset() throws InterruptedException {
        for(int i=0;i<MAX_REQUESTS;i++){
            assertTrue(slidingWindowCounter.allowRequest());
        }
        assertFalse(slidingWindowCounter.allowRequest());

        // Simulate waiting for the window to reset
        Thread.sleep((MAX_WINDOW_SIZE_SECONDS+1) * 1000); // Convert seconds to milliseconds

        // After the window resets, requests should be allowed again
        assertTrue(slidingWindowCounter.allowRequest());

    }




}