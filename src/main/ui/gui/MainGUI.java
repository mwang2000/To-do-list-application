package ui.gui;

import exceptions.TooManyThingsToDoException;
import model.Item;
import model.RegularItem;
import model.UrgentItem;
import network.Network;
import ui.TodoListManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame implements ActionListener {
    TodoListManager todoListManager;
    JLabel weather;
    JButton choice1;
    JButton choice2;
    JButton choice3;
    JButton choice4;

    public MainGUI() {
        super("ToDoList");
        Font titleFont = new Font("Arial", Font.PLAIN, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 1000);
        setLayout(new GridBagLayout());
        todoListManager = new TodoListManager(this);
        GridBagConstraints c = new GridBagConstraints();

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel,BoxLayout.PAGE_AXIS));
        JLabel welcomeMessage = new JLabel("Welcome to a generic to-do list application for CPSC 210!",
                JLabel.CENTER);
        textPanel.add(welcomeMessage);
        welcomeMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel question = new JLabel("What would you like to do?",JLabel.CENTER);
        textPanel.add(question);
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 3;
        c.gridwidth = 3;
        c.gridheight = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textPanel,c);
        welcomeMessage.setFont(titleFont);
        question.setFont(new Font("Arial",Font.PLAIN,20));
        question.setAlignmentX(Component.CENTER_ALIGNMENT);

        choice1 = new JButton("Add a regular to-do list item");
        c.weighty = 3;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        c.gridheight = 1;
        Insets i = new Insets(5,5,5,5);
        c.insets = i;
        c.fill = GridBagConstraints.BOTH;
        add(choice1,c);
        choice1.addActionListener(this);

        choice2 = new JButton("Add an urgent to-do list item");
        c.gridheight = 3;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        add(choice2,c);
        choice2.addActionListener(this);

        choice3 = new JButton("Cross off selected finished item");
        c.weighty = 3;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 2;
        add(choice3,c);
        choice3.addActionListener(this);

        choice4 = new JButton("Save and exit");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 4;
        c.fill = GridBagConstraints.BOTH;
        add(choice4,c);
        choice4.addActionListener(this);

        weather = new JLabel("<html>" + Network.printWebPage() + "</html>");
        weather.setFont(new Font("Arial",Font.PLAIN, 20));
        c.gridx = 4;
        c.gridy = 0;
        c.weightx = 1;
        add(weather,c);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == choice1) {
            RegularItem item = new RegularItem();
            newItemHelper(item);
        } else if (e.getSource() == choice2) {
            UrgentItem item = new UrgentItem();
            newItemHelper(item);
        } else if (e.getSource() == choice3) {
            todoListManager.move();
        } else {
            todoListManager.saveAtEnd();
            this.dispose();
        }
    }

    public void newItemHelper(Item item) {
        try {
            todoListManager.addItem(item);
        } catch (TooManyThingsToDoException ex) {
            new JOptionPane().showMessageDialog(null,
                    "Too many things to do! Try finishing some tasks first.");
        }
    }
}
