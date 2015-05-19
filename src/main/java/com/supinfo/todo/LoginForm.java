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

    public LoginForm() {
        ActionListener onLogin = e -> {
            System.out.println(Database.login(userField.getText(), new String(passField.getPassword())));
        };

        KeyListener onEnter = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                switch(e.getKeyCode()) {
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
        JFrame frame = new JFrame("LoginForm");
        frame.setContentPane(new LoginForm().root);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
