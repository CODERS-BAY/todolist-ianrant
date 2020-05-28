package com.company;

import java.util.ArrayList;

public class ToDo {

    private String todoName;
    private int toDoId;
    private ArrayList<Task> taskCollection = new ArrayList<>();

    public ToDo(String todoName, int toDoId, ArrayList<Task> taskCollection) {
        this.todoName = todoName;
        toDoId = toDoId;
        this.taskCollection = taskCollection;
    }

    public ToDo() {
    }

    public String getTodoName() {
        return todoName;
    }

    public void setTodoName(String todoName) {
        this.todoName = todoName;
    }

    public int gettoDoId() {
        return toDoId;
    }

    public ArrayList<Task> getTaskCollection() {
        return taskCollection;
    }

    public void setTaskCollection(ArrayList<Task> taskCollection) {
        this.taskCollection = taskCollection;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "todoName='" + todoName + '\'' +
                ", toDoId=" + toDoId +
                ", taskCollection=" + taskCollection +
                '}';
    }
}



