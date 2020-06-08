package com.company;

import java.sql.*;

public class ToDoDAO {

    private static final String CONNECTION_STRING = "jdbc:derby:/Users/hexes/Software Development/IntelliJ/ToDo List/db;create=true";
    private Connection conn;

    public ToDoDAO() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);

            // DELETING ALL CONTENT FROM TABLE "TODO"
            PreparedStatement ps = conn.prepareStatement("DELETE  FROM todo");
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void insertToDo(String todoName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO todo(todo_name) VALUES (?)");
        ps.setString(1, todoName);
        ps.execute();
        System.out.println("TODO entry successful: " + todoName);
    }


    public int retrieveToDoIdFromDB(String todoName) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("SELECT todo_id FROM todo WHERE todo_name = ?");
        // statt ps einen namen wählen der ausdrückt, was die variable beinhaltet ==> z.B. selectByName
        ps.setString(1, todoName);
        ResultSet rs = ps.executeQuery();
        Integer todoID = null;

        while (rs.next()) {
            todoID = rs.getInt("todo_id");
        }

        return todoID;
    }


    public ToDo selectSingleToDoWithTasks(String todoName) throws SQLException {

        Integer todoId = retrieveToDoIdFromDB(todoName);

        ToDo currentToDo = new ToDo();
        currentToDo.setTodoName(todoName);

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM task WHERE todo_id = ?");
        ps.setInt(1, todoId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Task task = new Task(rs.getString("task_name"), rs.getInt("task_id"), rs.getInt("todo_id"));
            currentToDo.addTaskToCollection(task);
        }

        return currentToDo;
    }


    public void selectAllToDoNoTasks() throws SQLException {

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM todo");
        ResultSet rs = ps.executeQuery();

        System.out.println("Your task(s):");
        while (rs.next()) {
            int counter = 1;
            System.out.println("- " + counter + ". " + rs.getString("todo_name"));
            counter++;
        }
    }


    public void deleteToDo(String todoName) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("DELETE FROM todo WHERE todo_name = ?");
        ps.setString(1, todoName);
        ps.execute();
        System.out.println("Deletion successful");
    }


    public void updateToDo(String oldToDoName, String newToDoName) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("UPDATE todo SET todo_name = ? WHERE todo_name = ?");
        ps.setString(1, oldToDoName);
        ps.setString(2, newToDoName);
        ps.execute();
        System.out.println("\nEntry successful - ToDO name changed from " + oldToDoName + " to " + newToDoName + "\n");
    }


    public void closeDBConnection() throws SQLException {
        conn.close();
    }


}
