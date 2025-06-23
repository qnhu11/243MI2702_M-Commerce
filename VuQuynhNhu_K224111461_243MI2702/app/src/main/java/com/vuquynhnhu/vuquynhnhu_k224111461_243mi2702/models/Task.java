package com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.models;

public class Task {
    private int id;
    private int accountId;
    private String taskTitle;
    private String dateAssigned;
    private int isCompleted;

    // Constructors
    public Task() {}

    public Task(int id, int accountId, String taskTitle, String dateAssigned, int isCompleted) {
        this.id = id;
        this.accountId = accountId;
        this.taskTitle = taskTitle;
        this.dateAssigned = dateAssigned;
        this.isCompleted = isCompleted;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public String getTaskTitle() { return taskTitle; }
    public void setTaskTitle(String taskTitle) { this.taskTitle = taskTitle; }

    public String getDateAssigned() { return dateAssigned; }
    public void setDateAssigned(String dateAssigned) { this.dateAssigned = dateAssigned; }

    public int getIsCompleted() { return isCompleted; }
    public void setIsCompleted(int isCompleted) { this.isCompleted = isCompleted; }
}