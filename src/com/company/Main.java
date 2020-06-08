package com.company;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        // write your code here

        ToDoDAO toDoDAO = new ToDoDAO();
        TaskDAO taskDAO = new TaskDAO();

        try {
            toDoDAO.insertToDo("Cleaning");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Integer todoId = toDoDAO.retrieveToDoIdFromDB("Cleaning");
        System.out.println(todoId);

        taskDAO.insertTask("Windows", todoId);
        taskDAO.insertTask("Floor", todoId);
        taskDAO.insertTask("Laundry", todoId);
        System.out.println();

        ToDo cleaning = toDoDAO.selectSingleToDoWithTasks("Cleaning");
        cleaning.printSingleTodoWithTasks();

        System.out.println();
        toDoDAO.selectAllToDoNoTasks();

        System.out.println();
        toDoDAO.deleteToDo("Cleaning");


        toDoDAO.closeDBConnection();
        taskDAO.closeDBConnection();
    }

}
