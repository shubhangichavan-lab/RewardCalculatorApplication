package com.retail.reward.service;

import com.retail.reward.model.Rewards;
import org.springframework.stereotype.Service;


public interface RewardService {
    public Rewards getRewardProgramByCustomerId(Long customerId);

}
