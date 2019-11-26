package ui.gui;

import model.Item;
import model.TodoList;

import javax.swing.*;

public class AddItemGUI {

    // EFFECTS: shows JOptionPane input dialogs asking user for task and due date and sets the item's fields accordingly
    // then calls a method to add the item to the given to do list, or asks user to try again if due date fields are not
    // integers
    public AddItemGUI(TodoList todo, Item item) {
        try {
            String task = JOptionPane.showInputDialog("Enter a task");
            int year = Integer.parseInt(JOptionPane.showInputDialog("Enter the year due"));
            int month = Integer.parseInt(JOptionPane.showInputDialog("Enter the month due"));
            int day = Integer.parseInt(JOptionPane.showInputDialog("Enter the day due"));
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
