package com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.models;

public class TaskDetails {
    private int id;
    private int taskId;
    private int customerId;
    private int isCalled;

    public TaskDetails() {}

    public TaskDetails(int id, int taskId, int customerId, int isCalled) {
        this.id = id;
        this.taskId = taskId;
        this.customerId = customerId;
        this.isCalled = isCalled;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getTaskId() { return taskId; }
    public void setTaskId(int taskId) { this.taskId = taskId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getIsCalled() { return isCalled; }
    public void setIsCalled(int isCalled) { this.isCalled = isCalled; }
}
