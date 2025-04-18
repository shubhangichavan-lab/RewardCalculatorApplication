package com.retail.reward.service;

import com.retail.reward.constants.constant;
import com.retail.reward.entity.Transaction;
import com.retail.reward.model.Rewards;
import com.retail.reward.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RewardServiceImpl implements RewardService{
    @Autowired
    TransactionRepository transactionRepo;
    @Override
    public Rewards getRewardProgramByCustomerId(Long customerId) {
        Timestamp lastMonthTimestamp = getDateBasedOnOffSetDays(constant.daysInMonths);
        Timestamp lastSecondMonthTimestamp = getDateBasedOnOffSetDays(2*constant.daysInMonths);
        Timestamp lastThirdMonthTimestamp = getDateBasedOnOffSetDays(3*constant.daysInMonths);

        List<Transaction> lastMonthTransactions = transactionRepo.findAllByCustomerIdAndTransactionDateBetween(
                customerId, lastMonthTimestamp, Timestamp.from(Instant.now()));
        List<Transaction> lastSecondMonthTransactions = transactionRepo
                .findAllByCustomerIdAndTransactionDateBetween(customerId, lastSecondMonthTimestamp, lastMonthTimestamp);
        List<Transaction> lastThirdMonthTransactions = transactionRepo
                .findAllByCustomerIdAndTransactionDateBetween(customerId, lastThirdMonthTimestamp,
                        lastSecondMonthTimestamp);

        Long lastMonthRewardPoints = getRewardsPerMonth(lastMonthTransactions);
        Long lastSecondMonthRewardPoints = getRewardsPerMonth(lastSecondMonthTransactions);
        Long lastThirdMonthRewardPoints = getRewardsPerMonth(lastThirdMonthTransactions);

        Rewards customerRewards = new Rewards();
        customerRewards.setCustomerId(customerId);
        customerRewards.setLastMonthRewardPoints(lastMonthRewardPoints);
        customerRewards.setLastSecondMonthRewardPoints(lastSecondMonthRewardPoints);
        customerRewards.setLastThirdMonthRewardPoints(lastThirdMonthRewardPoints);
        customerRewards.setTotalRewards(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);

        return customerRewards;

    }

    private Long getRewardsPerMonth(List<Transaction> transactions) {
        return transactions.stream().map(transaction -> calculateRewards(transaction))
                .mapToLong(Long::longValue).sum();
    }
    private Long calculateRewards(Transaction t) {
        if (t.getTransactionAmount() > constant.firstRewardLimit && t.getTransactionAmount() <= constant.secondRewardLimit) {
            return Math.round(t.getTransactionAmount() - constant.firstRewardLimit);
        } else if (t.getTransactionAmount() > constant.secondRewardLimit) {
            return Math.round(t.getTransactionAmount() - constant.secondRewardLimit) * 2
                    + (constant.secondRewardLimit - constant.firstRewardLimit);
        } else
            return 0L;

    }

    public Timestamp getDateBasedOnOffSetDays(int days) {
        return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
    }
}
