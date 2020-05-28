package com.company;

public class Task {
∆
    private String taskName;
    private int taskId;
    private int todoID;

    public Task(String taskName, int taskId, int todoID) {
        this.taskName = taskName;
        this.taskId = taskId;
        this.todoID = todoID;
    }

    public Task() {
    }

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


    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", taskId=" + taskId +
                ", todoID=" + todoID +
                '}';
    }
}