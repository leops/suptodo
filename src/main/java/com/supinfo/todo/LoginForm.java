package com.supinfo.todo;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by l3ops on 19/05/2015.
 */
public class LoginForm {
    private JTextField userField; //Champ pour rentre le nom de compte
    private JPasswordField passField; // Pour rentre le mot de passe
    private JButton okButton; // POur valider
    private JPanel root; // Conteneur de la fenêtre
    private static JFrame frame; //Fenêtre

    public LoginForm() {
        ActionListener onLogin = e -> { //Code exécuter sur le bouton
            User user = Database.login(userField.getText(), new String(passField.getPassword())); //Récuperer l'utilisateur dans la base de donnée
            if(user != null) { // Si l'utilisateur existe
                if(user.role == User.EMPLOYEE) { //OUvre la fenêtre pour un manager ou un employée
                    EmployeeForm.open(); // Ouvre la fenêtre
                } else {
                    ManagerForm.open();
                }
                frame.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this.root, "Mauvais nom de compte ou mot de passe", "Erreur de conexion", JOptionPane.ERROR_MESSAGE);
                // Message d'erreur si l'utilisateur n'existe pas
            }
        };

        KeyListener onEnter = new KeyAdapter() {  // Ca le valide
            @Override
            public void keyTyped(KeyEvent e) { // Valide le formulaire quand on appuie sur entrer
                super.keyTyped(e);
                switch(e.getKeyChar()) { // Quand la touche c'est "Entrer" ca valide le formulaire
                    case KeyEvent.VK_ENTER:
                        onLogin.actionPerformed(null);
                        break;
                }
            }
        };

        userField.addKeyListener(onEnter);// enregistre les évenement sur les champs de texte
        passField.addKeyListener(onEnter);
        okButton.addActionListener(onLogin);
    }

    public static void open() { //Permet d'ouvrir la fenêtre
        frame = new JFrame("LoginForm");
        frame.setContentPane(new LoginForm().root);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
