package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddRegularItemGUI extends JFrame implements ActionListener {
    public JLabel label;
    public JLabel label1;
    public JLabel label2;
    public JLabel label3;
    public JLabel label4;
    public JTextField field;
    public JTextField field1;
    public JTextField field2;
    public JTextField field3;
    public JTextField field4;

    public AddRegularItemGUI() {
        super("AddItem");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label = new JLabel("Enter the keyword for the item");
        label1 = new JLabel("Enter the task");
        label2 = new JLabel("Enter the year");
        label3 = new JLabel("month");
        label4 = new JLabel("day");
        field = new JTextField();
        field1 = new JTextField();
        field2 = new JTextField();
        field3 = new JTextField();
        field4 = new JTextField();
        setLayout(new FlowLayout());
        pack();
        setVisible(true);

        field.addActionListener(this);
        field1.addActionListener(this);
        field2.addActionListener(this);
        field3.addActionListener(this);
        field4.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
