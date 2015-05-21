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
public class ManagerForm {
    private JTextArea infos;
    private JButton button;
    private JPanel root;
    private JTable taskList;
    private JTextField date;

    public ManagerForm() {
        List<Todo> tasks = Database.getTodos();
        taskList.setModel(new TableModel() {
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

        button.addActionListener(evt -> {
            Date obj = null;
            try {
                obj = Todo.format.parse(date.getText());
            } catch(ParseException e) {
                e.printStackTrace();
            }

            Todo task = new Todo(0, obj, infos.getText(), null);
            task.id = Database.addTodo(task);
            if(task.id > -1) {
                tasks.add(task);
                taskList.addNotify();
                infos.setText("");
            }
        });
    }

    public static void open() {
        JFrame frame = new JFrame("ManagerForm");
        frame.setContentPane(new ManagerForm().root);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
