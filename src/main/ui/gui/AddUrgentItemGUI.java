package ui.gui;

import model.RegularItem;
import model.TodoList;
import model.UrgentItem;

import javax.swing.*;

public class AddUrgentItemGUI {
    String keyword;
    String task;
    int year;
    int month;
    int day;

    public AddUrgentItemGUI(TodoList todo) {
        keyword = JOptionPane.showInputDialog("Enter a keyword for the task");
        task = JOptionPane.showInputDialog("Enter a task");
        year = Integer.parseInt(JOptionPane.showInputDialog("Enter the year due"));
        month = Integer.parseInt(JOptionPane.showInputDialog("Enter the month due"));
        day = Integer.parseInt(JOptionPane.showInputDialog("Enter the day due"));
        UrgentItem item = new UrgentItem(keyword,task,year,month,day);
        todo.addItem(item);
    }
}
