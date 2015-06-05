package com.supinfo.todo;

import javax.swing.*;

/**
 * Created by l3ops on 19/05/2015.
 */
public class Launcher {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Il utilise un th�me natif au lieu d'un th�me de java
        } catch(Exception e)  // Evite que le programme s'arr�te en cas d'erreur
        {
            e.printStackTrace(); //La m�thode pour l'afficher
        }

        LoginForm.open(); // Ouvre le formulaire de login
    }
}
