package com.lolly.service;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDto {

    private int id;
    private Date date;
    private BigDecimal amount;
    private String vendor;
    private String description;
    private int accountId;
    private int categoryId;
    private int userId;

    public TransactionDto() {}

    public TransactionDto(Date date, BigDecimal amount, String vendor, String description,
                          int accountId, int categoryId, int userId) {
        this.date = date;
        this.amount = amount;
        this.vendor = vendor;
        this.description = description;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public TransactionDto(int id, Date date, BigDecimal amount, String vendor, String description,
                       int accountId, int categoryId, int userId) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.vendor = vendor;
        this.description = description;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getVendor() {
        return vendor;
    }

    public String getDescription() {
        return description;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getUserId() { return userId; }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setUserId(int userId) { this.userId =  userId; }
}
