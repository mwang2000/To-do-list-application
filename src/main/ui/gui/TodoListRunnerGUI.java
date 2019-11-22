package ui.gui;

import ui.TodoListManager;
import ui.TodoListRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class TodoListRunnerGUI extends JFrame implements ActionListener {
    TodoListRunner todoListRunner = new TodoListRunner();
    TodoListManager todoListManager = new TodoListManager();
    JButton choice1;
    JButton choice2;
    JButton choice3;
    JButton choice4;
    JButton choice5;

    public TodoListRunnerGUI() throws IOException {
        super("ToDoList");
        Font titleFont = new Font("Arial", Font.PLAIN, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLayout(new GridBagLayout());
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
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textPanel,c);
        welcomeMessage.setFont(titleFont);
        question.setFont(new Font("Arial",Font.PLAIN,20));
        question.setAlignmentX(Component.CENTER_ALIGNMENT);

//        JPanel selectionPanel = new JPanel();
        choice1 = new JButton("Add a regular to-do list item");
        c.weighty = 3;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
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

        choice3 = new JButton("Cross off a finished item");
        c.weighty = 3;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 2;
        add(choice3,c);
        choice3.addActionListener(this);

        choice4 = new JButton("Show all to-do list items");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        add(choice4,c);
        choice4.addActionListener(this);

        choice5 = new JButton("Save and exit");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 3;
        add(choice5,c);
        choice5.addActionListener(this);

//        addMouseListener(new MouseHandler);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == choice1) {
            todoListRunner.addRegularItem();
        } else if (e.getSource() == choice2) {
            todoListRunner.addUrgentItem();
        } else if (e.getSource() == choice3) {
            todoListManager.move();
        } else if (e.getSource() == choice4) {
            todoListManager.printLists();
        } else {
            todoListManager.saveAtEnd();
        }
        setVisible(true);
    }

//    private class MouseHandler extends MouseAdapter {
//        @Override
//        public void mouseClicked(MouseEvent e) {
//            mouseClicked(e);
//        }
//    }
}
