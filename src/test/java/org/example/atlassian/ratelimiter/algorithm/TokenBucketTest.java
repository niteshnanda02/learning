package org.example.atlassian.ratelimiter.algorithm;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TokenBucketTest {
    private TokenBucket tokenBucket;
    private static final long MAX_BUCKET_SIZE = 10;
    private static final long WINDOW_TIME_MILLIS = 10; // 1 second

    @BeforeEach
    void setUp() {
        tokenBucket = new TokenBucket(MAX_BUCKET_SIZE, WINDOW_TIME_MILLIS);
    }

    @Test
    void testInitialBucketSize() {
        // Initial bucket should be full
        for (int i = 0; i < MAX_BUCKET_SIZE; i++) {
            assertTrue(tokenBucket.allowRequest());
        }
        // Next request should be rejected
        assertFalse(tokenBucket.allowRequest());
    }

    @Test
    void testRefillAfterWindow() throws InterruptedException {
        // Consume all tokens
        for (int i = 0; i < MAX_BUCKET_SIZE; i++) {
            assertTrue(tokenBucket.allowRequest());
        }
        assertFalse(tokenBucket.allowRequest());

        // Wait for window to pass
        Thread.sleep(WINDOW_TIME_MILLIS*1000);

        // Should be able to make requests again
        assertTrue(tokenBucket.allowRequest());
    }

    @Test
    void testPartialRefill() throws InterruptedException {
        // Consume half the tokens
        for (int i = 0; i < MAX_BUCKET_SIZE / 2; i++) {
            assertTrue(tokenBucket.allowRequest());
        }

        // Wait for half the window
        Thread.sleep(WINDOW_TIME_MILLIS / 2);

        // Should be able to make more requests
        assertTrue(tokenBucket.allowRequest());
    }

    @Test
    void testConcurrentRequests() throws InterruptedException {
        // Create multiple threads to test concurrent access
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    tokenBucket.allowRequest();
                }
            });
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }

        // Verify that the total number of successful requests doesn't exceed the bucket size
        int successfulRequests = 0;
        while (tokenBucket.allowRequest()) {
            successfulRequests++;
        }
        assertTrue(successfulRequests <= MAX_BUCKET_SIZE);
    }


}