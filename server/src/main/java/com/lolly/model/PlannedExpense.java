package com.lolly.model;

import java.math.BigDecimal;

public class PlannedExpense {

    private int id;
    private int frequency;
    private int timeSpanId;
    private BigDecimal singleAmount;
    private int categoryId;
    private boolean active;
    private String description;
    private int userId;


    public PlannedExpense() {};

    public PlannedExpense(int id, int frequency, int timeSpanId, BigDecimal singleAmount,
                          int categoryId, boolean active, String description, int userId) {
        this.id = id;
        this.frequency = frequency;
        this.timeSpanId = timeSpanId;
        this.singleAmount = singleAmount;
        this.categoryId = categoryId;
        this.active = active;
        this.description = description;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getTimeSpanId() {
        return timeSpanId;
    }

    public BigDecimal getSingleAmount() {
        return singleAmount;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public boolean isActive() {
        return active;
    }

    public String getDescription() {
        return description;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setTimeSpanId(int timeSpanId) {
        this.timeSpanId = timeSpanId;
    }

    public void setSingleAmount(BigDecimal singleAmount) {
        this.singleAmount = singleAmount;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
