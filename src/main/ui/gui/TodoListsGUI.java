package ui.gui;

import model.TodoList;
import observer.TodoListObserver;

import javax.swing.*;
import java.awt.*;

public class TodoListsGUI implements TodoListObserver {
    private JList list = new JList();
    private JTextArea textArea = new JTextArea();
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();

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
    // MODIFIES: this
    // EFFECTS: sets this JList to contain the elements of the given TodoList
    public void update(TodoList todo) {
        list.setListData(todo.getList().toArray());
    }
}
