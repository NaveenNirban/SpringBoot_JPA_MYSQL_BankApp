package com.example.mysqlbank.repository;

import com.example.mysqlbank.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, Long> {
    Customer findById(long id);
}
