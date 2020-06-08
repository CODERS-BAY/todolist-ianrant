package com.company;

public class Task {

    //attributes
    private String taskName;
    private int taskId;
    private int todoID;

    // constructor: standard
    public Task(String taskName, int taskId, int todoID) {
        this.taskName = taskName;
        this.taskId = taskId;
        this.todoID = todoID;
    }

    // constructor: empty
    public Task() {
    }

    // getters/setters
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTodoID() {
        return todoID;
    }

    // to-string method
    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", taskId=" + taskId +
                ", todoID=" + todoID +
                '}';
    }
}