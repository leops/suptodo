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
    private JTextField userField;
    private JPasswordField passField;
    private JButton okButton;
    private JPanel root;
    private static JFrame frame;

    public LoginForm() {
        ActionListener onLogin = e -> {
            User user = Database.login(userField.getText(), new String(passField.getPassword()));
            if(user != null) {
                if(user.role == User.EMPLOYEE) {
                    EmployeeForm.open();
                } else {
                    ManagerForm.open();
                }
                frame.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this.root, "Mauvais nom de compte ou mot de passe", "Erreur de conexion", JOptionPane.ERROR_MESSAGE);
            }
        };

        KeyListener onEnter = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                switch(e.getKeyChar()) {
                    case KeyEvent.VK_ENTER:
                        onLogin.actionPerformed(null);
                        break;
                }
            }
        };

        userField.addKeyListener(onEnter);
        passField.addKeyListener(onEnter);
        okButton.addActionListener(onLogin);
    }

    public static void open() {
        frame = new JFrame("LoginForm");
        frame.setContentPane(new LoginForm().root);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
