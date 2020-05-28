package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ToDoDAO {

    private static final String CONNECTION_STRING = "jdbc:derby:/Users/hexes/Software Development/IntelliJ/ToDo List/db;create=true";
    private Connection conn;

    public ToDoDAO() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);

            //PreparedStatement ps = conn.prepareStatement("delete from contact");
            //ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
