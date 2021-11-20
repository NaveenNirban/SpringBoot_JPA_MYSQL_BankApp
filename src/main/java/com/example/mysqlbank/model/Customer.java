package com.example.mysqlbank.model;


import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long C_Id;


    private String C_NAME;
    private Long C_BALANCE;





    public Long getC_Id() {
        return C_Id;
    }

    public void setC_Id(Long c_Id) {
        C_Id = c_Id;
    }

    public String getC_NAME() {
        return C_NAME;
    }

    public void setC_NAME(String c_NAME) {
        C_NAME = c_NAME;
    }

    public Long getC_BALANCE() {
        return C_BALANCE;
    }

    public void setC_BALANCE(Long c_BALANCE) {
        C_BALANCE = c_BALANCE;
    }

    public void deposit(Long amount){
       Long balance = this.getC_BALANCE();
       this.setC_BALANCE(balance+amount);
    }

    public boolean withdraw(Long amount){
        Long balance = this.getC_BALANCE();
        if(amount<balance){
            this.setC_BALANCE(balance-amount);
            return true;
        }else{
            return false;
        }
    }
}
