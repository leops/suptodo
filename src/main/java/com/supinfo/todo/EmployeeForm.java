package com.supinfo.todo;

import javax.swing.*;
import java.awt.*;

/**
 * Created by l3ops on 21/05/2015.
 */
public class EmployeeForm {
    private JTabbedPane tabs; //Gestionnaire d'onglet
    private JPanel root; //Fen�tre

    public EmployeeForm() {
        int index = 0;
        for(Todo todo : Database.getTodos()) {
            if(todo.comment == null) { // n'affiche que les taches non temrin�e
                int id = index++;// Po
                TodoTab tab = new TodoTab(todo); // Cr�er une nouvelle onglet
                tabs.add(tab.getRoot(), id);
                tabs.setTitleAt(id, "Task #" + todo.id); //d�finir le titre de l'onglet
                tab.addRemoveListener(() -> tabs.remove(id));
            }
        }
    }

    public static void open() {  //Permet d'ouvrir la fen�tre
        JFrame frame = new JFrame("EmployeeForm");
        frame.setContentPane(new EmployeeForm().root);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(800, 600));
    }
}
