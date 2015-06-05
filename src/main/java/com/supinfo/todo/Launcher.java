package com.supinfo.todo;

import javax.swing.*;

/**
 * Created by l3ops on 19/05/2015.
 */
public class Launcher {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Il utilise un thème natif au lieu d'un thème de java
        } catch(Exception e)  // Evite que le programme s'arrête en cas d'erreur
        {
            e.printStackTrace(); //La méthode pour l'afficher
        }

        LoginForm.open(); // Ouvre le formulaire de login
    }
}
