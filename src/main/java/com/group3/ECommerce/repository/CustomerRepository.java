package com.group3.ECommerce.repository;

import com.group3.ECommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Additional query methods can be defined here if needed
}