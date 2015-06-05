package com.supinfo.todo;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by l3ops on 21/05/2015.
 */
public class ManagerForm { // class managerform
    private JTextArea infos; // champs pour entrer un commentaire pour chaque tache
    private JButton button; // bouton pour valider
    private JPanel root; //le conteneur
    private JTable taskList; //tableau qui affiche liste des tache a faire
    private JTextField date; // entrer date a laquelle il faut rendre tache

    public ManagerForm() { // fenetre manager
        List<Todo> tasks = Database.getTodos(); // recupere liste todo
        taskList.setModel(new TableModel()  { // créer modele de la table
            @Override
            public int getRowCount() {
                return tasks.size();
            }

            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public String getColumnName(int columnIndex) {
                switch(columnIndex) {
                    case 0:
                        return "#";
                    case 1:
                        return "Date";
                    case 2:
                        return "Informations";
                    default:
                        return "Commentaire";
                }
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Todo task = tasks.get(rowIndex);
                switch(columnIndex) {
                    case 0:
                        return task.id;
                    case 1:
                        return task.getDate();
                    case 2:
                        return task.getInfo();
                    default:
                        return task.getComment();
                }
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                // NOOP
            }

            @Override
            public void addTableModelListener(TableModelListener l) {
                // NOOP
            }

            @Override
            public void removeTableModelListener(TableModelListener l) {
                // NOOP
            }
        });

        button.addActionListener(evt -> { // ajouter une nouvelle tache
            Date obj = null;
            try {
                obj = Todo.format.parse(date.getText());
            } catch(ParseException e) {
                e.printStackTrace();
            }

            Todo task = new Todo(0, obj, infos.getText(), null); //Il le créer a partir des donnée donner par les utilisateur
            task.id = Database.addTodo(task); //Enregistre "Todo" dans la base de donnée
            if(task.id > -1) {
                tasks.add(task);
                taskList.addNotify();
                infos.setText("");
            }
        });
    }

    public static void open() { // Ouvre la fenêtre
        JFrame frame = new JFrame("ManagerForm");
        frame.setContentPane(new ManagerForm().root);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
