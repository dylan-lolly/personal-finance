package com.lolly.model;

public class Bank {

    private int id;
    private String name;
    private String nickname;
    private String userId;


    public Bank() {};

    public Bank(int id, String name, String nickname, String userId) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
