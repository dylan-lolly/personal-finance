package com.lolly.model;

public class Category {

    private int id;
    private String name;
    private boolean essential;
    private int userId;


    public Category() {};

    public Category(int id, String name, boolean essential, int userId) {
        this.id = id;
        this.name = name;
        this.essential = essential;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isEssential() {
        return essential;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEssential(boolean essential) {
        this.essential = essential;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
