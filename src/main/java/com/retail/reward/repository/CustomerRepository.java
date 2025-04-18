package com.retail.reward.repository;

import com.retail.reward.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {
public Optional<Customer> findByCustomerId(Long customerId);
}
