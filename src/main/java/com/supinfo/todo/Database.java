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
    private static Connection db = null; // Avoir une à la base de donnée
    private static Connection getConnection() { // Si il n'y a pas de connection ouverte ca va en créer une nouvelle
        if(db == null) {
            try {
                db = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/todo", "root", "");
            } catch (SQLException e) {
                e.printStackTrace(); // si ya une erreur , il affiche msg d'erreur
            }
        }

        return db; // sinon return db
    }

    public static User login(String username, String password) {
        try {
            PreparedStatement query = (PreparedStatement) getConnection().clientPrepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            query.setString(1, username);
            query.setString(2, Utils.sha1(password));
            ResultSet result = query.executeQuery();

            if(result.next()) {
                return new User(result.getString("username"), result.getInt("role"));
            } // return l'utilisateur qu'il a crée ou nul si l'utilisateur n'existe pas

        } catch (SQLException e) {
            e.printStackTrace(); // si il y a une erreur
        }

        return null;
    }

    public static List<Todo> getTodos() { // return une liste de todo
        List<Todo> list = new ArrayList<>();

        try {
            PreparedStatement query = (PreparedStatement) getConnection().clientPrepareStatement("SELECT * FROM tasks");
            ResultSet result = query.executeQuery(); // requete sur bdd

            while(result.next()) {
                list.add(new Todo(result.getInt("id"), result.getDate("date"), result.getString("info"), result.getString("comment")));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // afficher si il y a une erreur
        }

        return list;
    }

    public static int addTodo(Todo task) { //permet d'ajouter une tache a la bdd
        try {
            PreparedStatement query = (PreparedStatement) getConnection().clientPrepareStatement("INSERT INTO tasks (info) VALUES (?)");
            query.setString(1, task.info);
            query.execute();
            return (int) query.getLastInsertID();
        } catch (SQLException e) { //afficher erreur
            e.printStackTrace();
        }

        return -1;
    }

    public static void setTodo(String comment) {  //marque les taches comme etant terminer
        try {
            PreparedStatement query = (PreparedStatement) getConnection().clientPrepareStatement("UPDATE tasks SET comment = ?");
            query.setString(1, comment);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
