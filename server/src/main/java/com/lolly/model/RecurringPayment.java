package com.lolly.model;

import java.math.BigDecimal;
import java.util.Date;

public class RecurringPayment {

    private int id;
    private int dayOfMonthDue;
    private int accountId;
    private BigDecimal amount;
    private int categoryId;
    private int paymentTypeId;
    private boolean active;
    private String description;
    private String link;
    private int userId;

    public RecurringPayment() {};

    public RecurringPayment(int id, int dayOfMonthDue, int accountId, BigDecimal amount, int categoryId,
                            int paymentTypeId, boolean active, String description, String link, int userId) {
        this.id = id;
        this.dayOfMonthDue = dayOfMonthDue;
        this.accountId = accountId;
        this.amount = amount;
        this.categoryId = categoryId;
        this.paymentTypeId = paymentTypeId;
        this.active = active;
        this.description = description;
        this.link = link;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public int getDayOfMonthDue() {
        return dayOfMonthDue;
    }

    public int getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public boolean isActive() {
        return active;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDayOfMonthDue(int dayOfMonthDue) {
        this.dayOfMonthDue = dayOfMonthDue;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
