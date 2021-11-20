package com.example.mysqlbank.controller;

import com.example.mysqlbank.model.Customer;
import com.example.mysqlbank.model.Transaction;
import com.example.mysqlbank.service.CustomerService;
import com.example.mysqlbank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Controller // This means that this class is a Controller
/*@RequestMapping(path="/api")*/
public class controller
{

    // Autowiring multiple Services
    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;



    // 1. Adding new customer
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser (
            @ModelAttribute Customer customer
    ) {
        String result="";
        //Customer customer = new Customer();
        try{

        Transaction transaction = new Transaction();
        customer.setC_NAME(customer.getC_NAME());
        customer.setC_BALANCE(customer.getC_BALANCE());
        result =  customerService.save(customer);
        if(result.equalsIgnoreCase("success"))
            transaction.setC_ID(customer.getC_Id());
            transaction.setT_AMOUNT(customer.getC_BALANCE());
            transaction.setT_DATE_TIME();
            transactionService.save(transaction);
        }catch(Exception e){
            return "An error occurred "+e.toString();
        }
        return "New Customer added with id - "+customer.getC_Id()+", name "+customer.getC_NAME()+ " with balance "+customer.getC_BALANCE();
    }

    // 2. Balance deposit
    @PostMapping(path="/deposit") // Map ONLY POST Requests
    public @ResponseBody
    String deposit (
            @ModelAttribute Customer customer
    ) {
        Customer myCustomer = customerService.findById(customer.getC_Id());
        Transaction myTransaction = new Transaction();
        try{
            if(myCustomer!=null){
                myCustomer.deposit(customer.getC_BALANCE());
                customerService.save(myCustomer);
                myTransaction.setC_ID(customer.getC_Id());
                myTransaction.setT_AMOUNT(customer.getC_BALANCE());
                myTransaction.setT_DATE_TIME();
                transactionService.save(myTransaction);
                customerService.save(myCustomer);
            }else{
                return "User not found with ID "+ customer.getC_Id();
            }
        }catch (Exception e){
            return "An error Occurred :"+e.toString();
        }
        return "Updated balance of "+myCustomer.getC_Id()+" is "+myCustomer.getC_BALANCE();
    }

    // 3. Withdraw
    @PostMapping(path="/withdraw") // Map ONLY POST Requests
    public @ResponseBody
    String withdraw (
            @ModelAttribute Customer customer

    ) {
        Customer myCustomer = customerService.findById(customer.getC_Id());
        Transaction myTransaction = new Transaction();
        boolean isSuccess = false;
        try{
            if(myCustomer!=null){
                isSuccess = myCustomer.withdraw(customer.getC_BALANCE());
                if(isSuccess){
                    customerService.save(myCustomer);
                    myTransaction.setC_ID(customer.getC_Id());
                    myTransaction.setT_AMOUNT(customer.getC_BALANCE());
                    myTransaction.setT_DATE_TIME();
                    transactionService.save(myTransaction);
                    customerService.save(myCustomer);
                }else{
                    return "Not enough balance";
                }
            }else{
                return "User not found with ID "+ customer.getC_Id();
            }
        }catch (Exception e){
            return "An error Occurred :"+e.toString();
        }
        return "Updated balance after withdrawal is "+myCustomer.getC_BALANCE();
    }

    // 4. Show balance
    @PostMapping(path="/balance") // Map ONLY POST Requests
    public @ResponseBody
    String showBalance (
            @ModelAttribute Customer customer
    ) {
        Customer myCustomer = customerService.findById(customer.getC_Id());
        Long balance;
        try{
            if(myCustomer!=null){
                balance = myCustomer.getC_BALANCE();
            }else{
                return "User not found with ID "+ customer.getC_Id();
            }
        }catch (Exception e){
            return "An error Occurred :"+e.toString();
        }
        return "Balance of "+myCustomer.getC_Id()+" is "+ balance;
    }

    // 5. Show transactions
    @PostMapping(path="/transactions") // Map ONLY POST Requests
    public @ResponseBody
    List<Transaction> transactions (
    ) {
       return transactionService.listAll();
    }


    // Webpages


    // 6. Add Customer page
    @GetMapping("/addCustomer")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("addCustomer");
        Customer customer = new Customer();
        mav.addObject("customer", customer);
        return mav;
    }

    // 7. Transaction page
    @GetMapping("/showTransaction")
    public ModelAndView showTransaction() {
        ModelAndView mav = new ModelAndView("transactions");
        mav.addObject("transactions", transactionService.listAll());
        return mav;
    }

    // 8. DepositMoney
    @GetMapping("/depositMoney")
    public ModelAndView depositMoney() {
        ModelAndView mav = new ModelAndView("depositMoney");
        Customer customer = new Customer();
        mav.addObject("customer", customer);
        return mav;
    }

    // 9. Withdraw Money
    @GetMapping("/withdrawMoney")
    public ModelAndView withdrawMoney() {
        ModelAndView mav = new ModelAndView("withdrawMoney");
        Customer customer = new Customer();
        mav.addObject("customer", customer);
        return mav;
    }

    // 10. Show Balance
    @GetMapping("/showBalance")
    public ModelAndView showBalance() {
        ModelAndView mav = new ModelAndView("showBalance");
        Customer customer = new Customer();
        mav.addObject("customer", customer);
        return mav;
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }


}