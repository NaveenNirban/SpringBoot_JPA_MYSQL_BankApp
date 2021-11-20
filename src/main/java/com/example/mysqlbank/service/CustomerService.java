package com.example.mysqlbank.service;

import com.example.mysqlbank.model.Customer;
import com.example.mysqlbank.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepo repo;

    public List<Customer> listAll() {
        return (List<Customer>) repo.findAll();
    }

    public String save(Customer customer) {
        repo.save(customer);
        return "success";
    }

    public Customer get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
    public Customer findById(long id){
        return repo.findById(id);
    }
}
