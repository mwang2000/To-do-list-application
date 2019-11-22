package ui.gui;

import model.RegularItem;
import model.TodoList;

import javax.swing.*;

public class AddRegularItemGUI {
    String keyword;
    String task;
    int year;
    int month;
    int day;

    public AddRegularItemGUI(TodoList todo) {
        keyword = JOptionPane.showInputDialog("Enter a keyword for the task");
        task = JOptionPane.showInputDialog("Enter a task");
        year = Integer.parseInt(JOptionPane.showInputDialog("Enter the year due"));
        month = Integer.parseInt(JOptionPane.showInputDialog("Enter the month due"));
        day = Integer.parseInt(JOptionPane.showInputDialog("Enter the day due"));
        RegularItem item = new RegularItem(keyword,task,year,month,day);
        todo.addItem(item);
    }
}
