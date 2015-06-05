package com.supinfo.todo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by l3ops on 21/05/2015.
 */
public class TodoTab { // Contenu d'un onglet dans la fen^tre des employées
    private JTextArea comment; // pour mettre commentaire
    private JButton mark; // valider le commentaire
    private JLabel infos; //  contient info sur les tache a faire
    private JLabel date; // date a laquelle la tache doit etre rendu
    private JPanel root; // le conteneur

    private List<Runnable> listeners; //Sa fai appel au fonction a apeller quand la tache est appeler comme terminer

    public TodoTab(Todo todo) {
        listeners = new ArrayList<>();

        date.setText("Pour le: " + todo.getDate());
        infos.setText(todo.getInfo());

        mark.addActionListener(evt -> {
            Database.setTodo(comment.getText());
            listeners.forEach(Runnable::run);
        });
    }

    public JPanel getRoot() {
        return root;
    } //ouvre le conteneur de la fenêtre pour l'ajouter a un onglet

    public void addRemoveListener(Runnable listener) {
        listeners.add(listener);
    } //Enregistre un évenement pour la suppression d'un onglet
}
