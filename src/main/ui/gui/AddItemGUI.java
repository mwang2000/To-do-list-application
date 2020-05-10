package ui.gui;

import model.Item;
import model.TodoList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemGUI implements ActionListener {
    private JTextField taskField;
    private JTextField yearField;
    private JTextField monthField;
    private JTextField dateField;
    private Item item;
    private TodoList todo;
    private JFrame frame;

    // EFFECTS: creates new JFrame that asks user for input
    public AddItemGUI(TodoList todo,Item item) {
        frame = new JFrame();
        this.item = item;
        this.todo = todo;
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,300);
        Font font = new Font("Arial",Font.PLAIN,20);
        JLabel textArea = new JLabel("Enter the task");
        textArea.setFont(font);
        taskField = new JTextField(50);
        taskField.setFont(font);
        JLabel textArea1 = new JLabel("Enter the due date in the format yyyy-mm-dd.");
        textArea1.setFont(font);
        yearField = new JTextField(10);
        yearField.setFont(font);
        monthField = new JTextField(10);
        monthField.setFont(font);
        dateField = new JTextField(10);
        dateField.setFont(font);
        JButton button = new JButton("Add");
        frame.add(textArea);
        frame.add(taskField);
        frame.add(textArea1);
        frame.add(yearField);
        frame.add(monthField);
        frame.add(dateField);
        frame.add(button);
        button.addActionListener(this);
        frame.getRootPane().setDefaultButton(button);
        frame.setVisible(true);
    }

    @Override
    // EFFECTS: takes user input from JTextfields and sets the item's fields, then calls the method that adds the item
    // into the to-do list, or returns a text box with an error message if fields are of the wrong type
    public void actionPerformed(ActionEvent e) {
        try {
            String task = taskField.getText();
            int year = Integer.parseInt(yearField.getText());
            int month = Integer.parseInt(monthField.getText());
            int date = Integer.parseInt(dateField.getText());
            frame.dispose();
            item.setTask(task);
            item.setDue(year, month, date);
            todo.addItem(item);
        } catch (NumberFormatException ex) {
            new JOptionPane().showMessageDialog(null,
                    "Input for the due dates must be integers. Please try again.");
        }
    }
}
