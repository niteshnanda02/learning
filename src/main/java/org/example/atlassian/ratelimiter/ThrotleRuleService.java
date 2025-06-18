package org.example.atlassian.ratelimiter;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ThrotleRuleService {
    private final Map<String, ThrotleRule> clientThrottleRules;
    private static volatile ThrotleRuleService INSTANCE;

    public ThrotleRuleService() {
        clientThrottleRules = new ConcurrentHashMap<>();
    }

    public void createRule(String clientId, ThrotleRule throtleRule) {
        if (StringUtils.isBlank(clientId) || throtleRule == null) {
            throw new IllegalArgumentException("Client ID and Throttle Rule cannot be null");
        }
        clientThrottleRules.put(clientId, throtleRule);
    }

    public Optional<ThrotleRule> getRule(String clientId) {

        if (StringUtils.isBlank(clientId)) {
            throw new IllegalArgumentException("Client ID cannot be null");
        }
        if (!clientThrottleRules.containsKey(clientId))
            return Optional.empty();
        return Optional.of(clientThrottleRules.get(clientId));
    }

    public static ThrotleRuleService getInstance() {
        if (INSTANCE == null) {
            synchronized (ThrotleRuleService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ThrotleRuleService();
                }
            }
        }
        return INSTANCE;
    }


}
