package com.retail.reward.controller;

import com.retail.reward.entity.Customer;
import com.retail.reward.exception.NoSuchCustomerExistsException;
import com.retail.reward.model.ErrorResponse;
import com.retail.reward.model.Rewards;
import com.retail.reward.repository.CustomerRepository;
import com.retail.reward.repository.TransactionRepository;
import com.retail.reward.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class RewardsController {
    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    TransactionRepository transactionRepo;

    @Autowired
    RewardService rewardService;


    @GetMapping(value ="/{customerId}/rewards", produces = "application/json")
    public ResponseEntity<Rewards> getRewardProgramByCustomerId(@PathVariable Long customerId){
        Optional<Customer> customer = customerRepo.findByCustomerId(customerId);
        if(customer.isEmpty())
        {
            throw new NoSuchCustomerExistsException("Customer Id is not valid");
        }
        Rewards rewards=rewardService.getRewardProgramByCustomerId(customerId);
        return new ResponseEntity<Rewards>(rewards, HttpStatus.OK);
    }


}
