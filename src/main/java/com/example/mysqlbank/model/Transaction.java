package com.example.mysqlbank.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long T_ID;
    private Timestamp T_DATE_TIME;
    private Long T_AMOUNT;
    private Long C_ID;




    public Long getC_ID() {
        return C_ID;
    }

    public void setC_ID(Long c_ID) {
        C_ID = c_ID;
    }

    public Long getT_ID() {
        return T_ID;
    }

    public void setT_ID(Long t_ID) {
        T_ID = t_ID;
    }

    public Timestamp getT_DATE_TIME() {
        return T_DATE_TIME;
    }

    public void setT_DATE_TIME() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        T_DATE_TIME = timestamp;
    }

    public Long getT_AMOUNT() {
        return T_AMOUNT;
    }

    public void setT_AMOUNT(Long t_AMOUNT) {
        T_AMOUNT = t_AMOUNT;
    }


}
