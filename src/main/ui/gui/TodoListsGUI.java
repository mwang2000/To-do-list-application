package ui.gui;

import model.TodoList;
import observer.TodoListObserver;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TodoListsGUI implements TodoListObserver {
    JList list = new JList();
    JTextArea textArea = new JTextArea();
    GridBagConstraints gridBagConstraints = new GridBagConstraints();

    public TodoListsGUI(TodoList todo, TodoList crossedOff, JFrame frame) {
        todo = todo.load();
        list.setListData(todo.getList().toArray());
        list.setFont(new Font("Arial",Font.PLAIN, 16));
        list.setFixedCellHeight(50);
        list.setFixedCellWidth(500);
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        frame.add(list,gridBagConstraints);
        textArea.setFont(new Font("Arial",Font.PLAIN,14));
        textArea.setText("Crossed off list is:" + crossedOff.returnCrossedOffList());
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        frame.add(textArea,gridBagConstraints);
        frame.setVisible(true);
    }

    public JList getList() {
        return list;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    @Override
    public void update(TodoList todo) {
        list.setListData(todo.getList().toArray());
    }
}
