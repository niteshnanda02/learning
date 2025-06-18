package org.example.atlassian.ratelimiter;

import org.example.atlassian.ratelimiter.enums.RateLimiterEnum;

import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThrotleRule rule = new ThrotleRule();
        ThrotleRuleService throtleRulesService = ThrotleRuleService.getInstance();
        throtleRulesService.createRule("client1", rule);
        RateLimiterService rateLimiterService = new RateLimiterService(RateLimiterEnum.CREDIT_TOKEN_BUCKET);


        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(5);

        Runnable r = () ->{
            System.out.println(" client1 "+Thread.currentThread().getName() + "--" + rateLimiterService.isRateLimitedUserRequest("user1")+" -- "+ Instant.now());
        };
        scheduledExecutor.scheduleAtFixedRate(r, 0, 50, TimeUnit.MILLISECONDS);
//        for (int i = 0; i < 20; i++) {
//            System.out.println((i+1)+" "+rateLimiterService.isRateLimitedUserRequest("user1"));
//            if (i==5)
//                Thread.sleep(1000);
//        }
    }
}
