package com.supinfo.todo;

import java.util.Date;

/**
 * Created by l3ops on 21/05/2015.
 */
public class Todo {
    public int id;
    public Date date;
    public String info;
    public String comment;

    public Todo(int id, Date date, String info, String comment) {
        this.id = id;
        this.date = date;
        this.info = info;
        this.comment = comment;
    }
}
