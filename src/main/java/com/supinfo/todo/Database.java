package com.supinfo.todo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static User login(String username, String password) {
        try {
            PreparedStatement query = (PreparedStatement) getConnection().clientPrepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            query.setString(1, username);
            query.setString(2, Utils.sha1(password));
            ResultSet result = query.executeQuery();

            if(result.next()) {
                return new User(result.getString("username"), result.getInt("role"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Todo> getTodos() {
        List<Todo> list = new ArrayList<>();

        try {
            PreparedStatement query = (PreparedStatement) getConnection().clientPrepareStatement("SELECT * FROM tasks WHERE comment IS NULL");
            ResultSet result = query.executeQuery();

            while(result.next()) {
                list.add(new Todo(result.getInt("id"), result.getDate("date"), result.getString("info"), result.getString("comment")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void addTodo(Todo todo) {
        try {
            PreparedStatement query = (PreparedStatement) getConnection().clientPrepareStatement("INSERT INTO tasks (info) VALUES (?)");
            query.setString(1, todo.info);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setTodo(Todo todo) {
        try {
            PreparedStatement query = (PreparedStatement) getConnection().clientPrepareStatement("UPDATE tasks SET comment = ?");
            query.setString(1, todo.comment);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
