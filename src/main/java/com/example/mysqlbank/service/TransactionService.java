package com.example.mysqlbank.service;

import com.example.mysqlbank.model.Customer;
import com.example.mysqlbank.model.Transaction;
import com.example.mysqlbank.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepo repo;

    public List<Transaction> listAll() {
        return (List<Transaction>) repo.findAll();
    }

    public void save(Transaction transaction) {
        repo.save(transaction);
    }

    public Transaction get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}
