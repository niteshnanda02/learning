package org.example.atlassian.ratelimiter;

import org.example.atlassian.ratelimiter.algorithm.RateLimiter;
import org.example.atlassian.ratelimiter.enums.RateLimiterEnum;
import org.example.atlassian.ratelimiter.factory.RateLimiterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterService {
    Map<String, RateLimiter> rateLimiterMap;
    ThrotleRuleService throtleRuleService;
    private final RateLimiterEnum rateLimiterEnum;

    public RateLimiterService(RateLimiterEnum rateLimiterEnum) {
        this.rateLimiterEnum = rateLimiterEnum;
        rateLimiterMap = new ConcurrentHashMap<>();
        throtleRuleService = ThrotleRuleService.getInstance();
    }

    public boolean isRateLimitedUserRequest(String userId){
        createUserIfNotThere(userId);
        return rateLimiterMap.get(userId).allowRequest();
    }

    private void createUserIfNotThere(String userId) {
        if (!rateLimiterMap.containsKey(userId)){
            ThrotleRule throtleRule = throtleRuleService.getRule(userId)
                    .orElse(new ThrotleRule());

            RateLimiter rateLimiter = RateLimiterFactory.getRateLimiter(rateLimiterEnum, throtleRule.getMaxRequests(), throtleRule.getTimeWindow());
            rateLimiterMap.put(userId, rateLimiter);

        }
    }
}
