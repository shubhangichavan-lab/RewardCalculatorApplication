package com.retail.reward.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
public class Rewards {
    private long customerId;
    private long lastMonthRewardPoints;
    private long lastSecondMonthRewardPoints;
    private long lastThirdMonthRewardPoints;
    private long totalRewards;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getLastMonthRewardPoints() {
        return lastMonthRewardPoints;
    }

    public void setLastMonthRewardPoints(long lastMonthRewardPoints) {
        this.lastMonthRewardPoints = lastMonthRewardPoints;
    }

    public long getLastSecondMonthRewardPoints() {
        return lastSecondMonthRewardPoints;
    }

    public void setLastSecondMonthRewardPoints(long lastSecondMonthRewardPoints) {
        this.lastSecondMonthRewardPoints = lastSecondMonthRewardPoints;
    }

    public long getLastThirdMonthRewardPoints() {
        return lastThirdMonthRewardPoints;
    }

    public void setLastThirdMonthRewardPoints(long lastThirdMonthRewardPoints) {
        this.lastThirdMonthRewardPoints = lastThirdMonthRewardPoints;
    }

    public long getTotalRewards() {
        return totalRewards;
    }

    public void setTotalRewards(long totalRewards) {
        this.totalRewards = totalRewards;
    }
}
