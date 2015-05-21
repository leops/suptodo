package com.supinfo.todo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by l3ops on 21/05/2015.
 */
public class Todo {
    public int id;
    public Date date;
    public String info;
    public String comment;

    public static DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public Todo(int id, Date date, String info, String comment) {
        this.id = id;
        this.date = date;
        this.info = info;
        this.comment = comment;
    }

    public String getInfo() {
        return "<html>" + info.replaceAll("\n", "<br>") + "</html>";
    }

    public String getComment() {
        if(comment == null) {
            return "<html><span style=\"color: red;\">En cours</span></html>";
        } else {
            return comment;
        }
    }

    public String getDate() {
        return format.format(date);
    }
}
