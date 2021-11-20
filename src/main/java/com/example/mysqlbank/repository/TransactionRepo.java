package com.example.mysqlbank.repository;

import com.example.mysqlbank.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepo extends CrudRepository<Transaction, Long> {
}
