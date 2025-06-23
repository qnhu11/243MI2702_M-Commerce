package com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.models;
public class Account {
    private int id;
    private String username;
    private String password;
    private int type;

    public Account() {
    }

    public Account(int id, String username, String password, int type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return username; // dùng để hiển thị trong Spinner
    }
}
