package com.supinfo.todo;

import javax.swing.*;
import java.awt.*;

/**
 * Created by l3ops on 21/05/2015.
 */
public class EmployeeForm {
    private JTabbedPane tabs;
    private JPanel root;

    public EmployeeForm() {
        int index = 0;
        for(Todo todo : Database.getTodos()) {
            if(todo.comment == null) {
                int id = index++;
                TodoTab tab = new TodoTab(todo);
                tabs.add(tab.getRoot(), id);
                tabs.setTitleAt(id, "Task #" + todo.id);
                tab.addRemoveListener(() -> tabs.remove(id));
            }
        }
    }

    public static void open() {
        JFrame frame = new JFrame("EmployeeForm");
        frame.setContentPane(new EmployeeForm().root);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(800, 600));
    }
}
