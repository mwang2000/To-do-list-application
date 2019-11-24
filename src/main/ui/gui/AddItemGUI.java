package ui.gui;

import model.Item;
import model.TodoList;

import javax.swing.*;

public class AddItemGUI {
    String task;
    int year;
    int month;
    int day;

    public AddItemGUI(TodoList todo, Item item) {
        try {
            task = JOptionPane.showInputDialog("Enter a task");
            year = Integer.parseInt(JOptionPane.showInputDialog("Enter the year due"));
            month = Integer.parseInt(JOptionPane.showInputDialog("Enter the month due"));
            day = Integer.parseInt(JOptionPane.showInputDialog("Enter the day due"));
            item.setTask(task);
            item.setDue(year,month,day);
            item.setStatus("not done");
            todo.addItem(item);
        } catch (NumberFormatException e) {
            new JOptionPane().showMessageDialog(null,
                    "Input must be an integer. Please try again.");
        }
    }
}
