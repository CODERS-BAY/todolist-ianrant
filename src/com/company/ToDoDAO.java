package com.company;

import java.sql.*;

public class ToDoDAO {

    private static final String CONNECTION_STRING = "jdbc:derby:/Users/hexes/Software Development/IntelliJ/ToDo List/db;create=true";


    public ToDoDAO() {
        openConnection();
    }

    private Connection openConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    public void initialReset() throws SQLException {
        // DELETING ALL CONTENT FROM TABLE "TODO"
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE  FROM todo");
        ps.execute();
        conn.close();
    }


    public void insertToDo(String todoName) throws SQLException {
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO todo(todo_name) VALUES (?)");
        ps.setString(1, todoName);
        ps.execute();
        //System.out.println("TODO entry successful: " + todoName);
        conn.close();
    }


    public int retrieveToDoIdFromDB(String todoName) throws SQLException {
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT todo_id FROM todo WHERE todo_name = ?");
        // statt ps einen namen wählen der ausdrückt, was die variable beinhaltet ==> z.B. selectByName
        ps.setString(1, todoName);
        ResultSet rs = ps.executeQuery();
        Integer todoID = null;

        while (rs.next()) {
            todoID = rs.getInt("todo_id");
        }
        conn.close();
        return todoID;
    }


    public ToDo selectSingleToDoWithTasks(String todoName) throws SQLException {

        Integer todoId = retrieveToDoIdFromDB(todoName);

        ToDo currentToDo = new ToDo();
        currentToDo.setTodoName(todoName);

        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM task WHERE todo_id = ?");
        ps.setInt(1, todoId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Task task = new Task(rs.getString("task_name"), rs.getInt("task_id"), rs.getInt("todo_id"));
            currentToDo.addTaskToCollection(task);
        }
        conn.close();
        return currentToDo;
    }


    public void selectAllToDoNoTasks() throws SQLException {

        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM todo");
        ResultSet rs = ps.executeQuery();

        System.out.println("Your todo(s):");
        int counter = 1;
        while (rs.next()) {

            System.out.println("-" + counter + ". " + rs.getString("todo_name"));
            counter++;
        }
        conn.close();
    }


    public void deleteToDo(String todoName) throws SQLException {

        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM todo WHERE todo_name = ?");
        ps.setString(1, todoName);
        ps.execute();
        System.out.println("Deletion of " + todoName + " was successful");
        conn.close();
    }


    public void updateToDo(String oldToDoName, String newToDoName) throws SQLException {

        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE todo SET todo_name = ? WHERE todo_name = ?");
        ps.setString(1, newToDoName);
        ps.setString(2, oldToDoName);
        ps.execute();
        System.out.println("\nEntry successful - ToDO name changed from -" + oldToDoName + "-" + " to " + "-" + newToDoName + "-\n");
        conn.close();
    }


    public void closeDBConnection() throws SQLException {
        Connection conn = openConnection();
        conn.close();
    }


}
