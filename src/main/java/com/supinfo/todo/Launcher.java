package com.supinfo.todo;

import javax.swing.*;

/**
 * Created by l3ops on 19/05/2015.
 */
public class Launcher {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            e.printStackTrace();
        }

        LoginForm.open();
    }
}
