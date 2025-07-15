package com.lolly.model;

import java.math.BigDecimal;
import java.util.Date;

public class Account {

    private int id;
    private String accountNumber;
    private String routingNumber;
    private String name;
    private BigDecimal dueBalance;
    private BigDecimal total_balance;
    private double apr;
    private double apy;
    private Date paymentDueDate;
    private int accountTypeId;
    private int bankId;
    private int userId;


    public Account() {};

    public Account(int id, String accountNumber, String routingNumber, String name,
                   BigDecimal dueBalance, BigDecimal total_balance, double apr, double apy,
                   Date paymentDueDate, int accountTypeId, int bankId, int userId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
        this.name = name;
        this.dueBalance = dueBalance;
        this.total_balance = total_balance;
        this.apr = apr;
        this.apy = apy;
        this.paymentDueDate = paymentDueDate;
        this.accountTypeId = accountTypeId;
        this.bankId = bankId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getDueBalance() {
        return dueBalance;
    }

    public BigDecimal getTotal_balance() {
        return total_balance;
    }

    public double getApr() {
        return apr;
    }

    public double getApy() {
        return apy;
    }

    public Date getPaymentDueDate() {
        return paymentDueDate;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public int getBankId() {
        return bankId;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDueBalance(BigDecimal dueBalance) {
        this.dueBalance = dueBalance;
    }

    public void setTotal_balance(BigDecimal total_balance) {
        this.total_balance = total_balance;
    }

    public void setApr(double apr) {
        this.apr = apr;
    }

    public void setApy(double apy) {
        this.apy = apy;
    }

    public void setPaymentDueDate(Date paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
