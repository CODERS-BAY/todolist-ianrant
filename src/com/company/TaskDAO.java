package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskDAO {

    private static final String CONNECTION_STRING = "jdbc:derby:/Users/hexes/Software Development/IntelliJ/ToDo List/db;create=true";
    private Connection conn;

    public TaskDAO() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);

            // DELETING ALL CONTENT FROM TABLE "TODO"
            PreparedStatement ps = conn.prepareStatement("DELETE FROM task");
            ps.execute();

            //PreparedStatement ps = conn.prepareStatement("delete from contact");
            //ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertTask(String taskName, Integer todoId) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("insert into task(task_name, todo_id) values (?, ?)");
        ps.setString(1, taskName);
        ps.setInt(2, todoId);
        ps.execute();
        System.out.println("TASK entry successful: " + taskName);
    }


    public void deleteTask(String taskName, Integer todoId) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("DELETE FROM task WHERE ?, ?)");
        ps.setString(1, taskName);
        ps.setInt(2, todoId);
        ps.execute();
        System.out.println("TASK entry successful: " + taskName);
    }


    public void updateTask(String oldTaskName, String newTaskName) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("UPDATE task SET task_name = ? WHERE task_name = ?)");
        ps.setString(1, oldTaskName);
        ps.setString(2, newTaskName);
        ps.execute();
        System.out.println("\nEntry successful - Task name changed from " + oldTaskName + " to " + newTaskName + "\n");
    }


    public void closeDBConnection() throws SQLException {
        conn.close();
    }


}
