package com.lolly.model;

import javax.xml.crypto.dsig.TransformService;
import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

    private int id;
    private Date date;
    private BigDecimal amount;
    private String vendor;
    private String description;
    private String receiptUrl;
    private int accountId;
    private int categoryId;
    private int userId;

    public Transaction() {}

    public Transaction(int id, Date date, BigDecimal amount, String vendor, String description,
                       String receiptUrl, int accountId, int categoryId, int userId) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.vendor = vendor;
        this.description = description;
        this.receiptUrl = receiptUrl;
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

    public String getReceiptUrl() { return receiptUrl; }

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

    public void setReceiptUrl(String receiptUrl) { this.receiptUrl = receiptUrl; }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setUserId(int userId) { this.userId =  userId; }

    // Methods
    // Not to sure if I'll need this at all, but I'm leaving it here as a template.
    public String jsonBuilder() {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("\"transaction\": [");
        jsonBuilder.append("\"id\": \"").append(id).append("\",");
        jsonBuilder.append("\"amount\": \"").append(date).append("\",");
        jsonBuilder.append("\"amount\": \"").append(amount).append("\",");
        jsonBuilder.append("\"vendor\": \"").append(vendor).append("\",");
        jsonBuilder.append("\"description\": \"").append(description).append("\",");
        jsonBuilder.append("\"accountId\": \"").append(accountId).append("\",");
        jsonBuilder.append("\"categoryId\": \"").append(categoryId).append("\",");
        jsonBuilder.append("\"userId\": \"").append(userId).append("\",");

        return jsonBuilder.toString();
    }
}
