package com.company;

import java.util.ArrayList;

public class ToDo {

    // attributes
    private String todoName;
    private int toDoId;
    private ArrayList<Task> taskCollection = new ArrayList<>();

    // constructor
    public ToDo(String todoName, int toDoId, ArrayList<Task> taskCollection) {
        this.todoName = todoName;
        toDoId = toDoId;
        this.taskCollection = taskCollection;
    }

    // constructor
    public ToDo() {
    }

    public ToDo(String todoName) {
        this.todoName = todoName;
    }

    // getters/setters
    public String getTodoName() {
        return todoName;
    }

    public void setTodoName(String todoName) {
        this.todoName = todoName;
    }

    public void setToDoId(int toDoId) {
        this.toDoId = toDoId;
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

    public void addTaskToCollection(Task task) {
        this.taskCollection.add(task);
    }

    // to-string method
    @Override
    public String toString() {
        return "ToDo{" +
                "todoName='" + todoName + '\'' +
                ", toDoId=" + toDoId +
                ", taskCollection=" + taskCollection +
                '}';
    }

    public void printSingleTodoWithTasks() {

        System.out.println("Todo: " + todoName);
        System.out.println("- - - - - -");

        for (Task task : taskCollection) {
            int counter = 1;
            System.out.println("Task #" + counter + ": " + task.getTaskName());
            counter++;
        }
    }
}



