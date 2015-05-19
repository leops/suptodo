package com.supinfo.todo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by l3ops on 19/05/2015.
 */
public class Database {
    private static Connection db = null;
    private static Connection getConnection() {
        if(db == null) {
            try {
                db = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/todo", "root", "");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return db;
    }

    public static Object login(String username, String password) {
        try {
            PreparedStatement query = (PreparedStatement) getConnection().clientPrepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            query.setString(1, username);
            query.setString(2, Utils.sha1(password));
            ResultSet result = query.executeQuery();

            if(result.next()) {
                System.out.println("Role: " + result.getInt("role"));
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
