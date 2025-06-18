package org.example.atlassian.ratelimiter.algorithm;

public class CreditBasedTokenBucket extends TokenBucket{
    private final long maxCredits;
    private long currentCredits;
    public CreditBasedTokenBucket(long maxBucketSize, long windowTimeInSeconds, long maxCredits) {
        super(maxBucketSize, windowTimeInSeconds);
        this.maxCredits = maxCredits;
        this.currentCredits = maxCredits; // Initialize with max credits
    }

    @Override
    public synchronized boolean allowRequest() {
        boolean allowed = super.allowRequest();
        if (allowed)
            return true;
        // refill the credits
        refill();
        if (currentCredits > 0) {
            currentCredits--;
            return true;
        }
        return false;
    }

    void refill(){
        double unusedCapacity = getCurrenBucketSize();
        if (unusedCapacity>0){
            currentCredits = Math.min(maxCredits, currentCredits + (long) unusedCapacity);
        }
    }
}
