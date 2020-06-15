package com.company;

import java.sql.*;

public class TaskDAO {

    private static final String CONNECTION_STRING = "jdbc:derby:/Users/hexes/Software Development/IntelliJ/ToDo List/db;create=true";
    private Connection conn;

    public TaskDAO() {
        try {
            openConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Connection openConnection() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    public void insertTask(String taskName, Integer todoId) throws SQLException {
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement("insert into task(task_name, todo_id) values (?, ?)");
        ps.setString(1, taskName);
        ps.setInt(2, todoId);
        ps.execute();
        System.out.println("Task entry successful: " + taskName);
    }


    public void deleteTask(String taskName, Integer todoId) throws SQLException {
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM task WHERE task_name = ? AND todo_id = ?");
        ps.setString(1, taskName);
        ps.setInt(2, todoId);
        ps.execute();
        System.out.println("Task deletion successful: " + taskName);
    }


    public void updateTask(String oldTaskName, String newTaskName) throws SQLException {
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE task SET task_name = ? WHERE task_name = ?");
        ps.setString(1, newTaskName);
        ps.setString(2, oldTaskName);
        ps.execute();
        System.out.println("\nUpdate successful - Task name changed from -" + oldTaskName + "-" + " to " + "-" + newTaskName + "-\n");
    }


    public Integer countAssociatedTasks(String todoName) throws SQLException {
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT count(task.TASK_ID) " +
                "AS task_count " +
                "FROM task, todo " +
                "WHERE task.todo_id = todo.TODO_ID " +
                "AND todo.TODO_NAME = ?");
        ps.setString(1, todoName);
        ResultSet rs = ps.executeQuery();
        Integer taskCount = null;
        while (rs.next()) {
            taskCount = rs.getInt("task_count");
        }
        return taskCount;
    }


    public void closeDBConnection() throws SQLException {
        Connection conn = openConnection();
        conn.close();
    }
}
