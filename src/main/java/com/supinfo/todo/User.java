package com.supinfo.todo;

/**
 * Created by l3ops on 21/05/2015.
 */
public class User {
    public static int EMPLOYEE = 0;
    public static int MANAGER = 1;

    public String username;
    public int role;

    public User(String name, int role) {
        this.username = name;
        this.role = role;
    }
}
