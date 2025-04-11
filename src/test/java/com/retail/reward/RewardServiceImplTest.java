package com.retail.reward;

import com.retail.reward.entity.Transaction;
import com.retail.reward.model.Rewards;
import com.retail.reward.repository.TransactionRepository;
import com.retail.reward.service.RewardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RewardServiceImplTest {
    @Mock
    TransactionRepository transactionRepo;
    @InjectMocks
    RewardServiceImpl rewardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRewardProgramByCustomerId() {
        Long customerId = 1L;
        List<Transaction> lastMonthTransactions = Arrays.asList(
                new Transaction(1L, customerId, new Timestamp(LocalDateTime.now().minusDays(30).toInstant(ZoneOffset.UTC).toEpochMilli()),120.0),
                new Transaction(2L, customerId,new Timestamp(LocalDateTime.now().minusDays(30).toInstant(ZoneOffset.UTC).toEpochMilli()), 80.0)
        );
        List<Transaction> lastSecondMonthTransactions = Arrays.asList(
                new Transaction(3L, customerId, new Timestamp(LocalDateTime.now().minusDays(60).toInstant(ZoneOffset.UTC).toEpochMilli()),150.0)
        );
        List<Transaction> lastThirdMonthTransactions = Collections.emptyList();

        when(transactionRepo.findAllByCustomerIdAndTransactionDateBetween(anyLong(), any(Timestamp.class), any(Timestamp.class)))
                .thenReturn(lastMonthTransactions, lastSecondMonthTransactions, lastThirdMonthTransactions);

        Rewards rewards = rewardService.getRewardProgramByCustomerId(customerId);

        verify(transactionRepo, times(3)).findAllByCustomerIdAndTransactionDateBetween(anyLong(), any(Timestamp.class), any(Timestamp.class));
        assertEquals(customerId, rewards.getCustomerId());
        assertEquals(120L, rewards.getLastMonthRewardPoints());
        assertEquals(150L, rewards.getLastSecondMonthRewardPoints());
        assertEquals(0L, rewards.getLastThirdMonthRewardPoints());
        assertEquals(270L, rewards.getTotalRewards());
    }
}
