package ui.gui;

import javax.swing.*;
import java.awt.*;

public class TodoListRunnerGUI {

    public TodoListRunnerGUI() {
        JFrame frame = new JFrame("ToDoList");
        Font titleFont = new Font("Arial", Font.PLAIN, 30);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLayout(new GridBagLayout());
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
        frame.add(textPanel,c);
        welcomeMessage.setFont(titleFont);
        question.setFont(new Font("Arial",Font.PLAIN,20));
        question.setAlignmentX(Component.CENTER_ALIGNMENT);

//        JPanel selectionPanel = new JPanel();
        JButton choice1 = new JButton("Add a regular to-do list item");
        c.weighty = 3;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        frame.add(choice1,c);

        JButton choice2 = new JButton("Add an urgent to-do list item");
        c.gridheight = 3;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        frame.add(choice2,c);

        JButton choice3 = new JButton("Cross off a finished item");
        c.weighty = 3;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 2;
        frame.add(choice3,c);

        JButton choice4 = new JButton("Show all to-do list items");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        frame.add(choice4,c);

        JButton choice5 = new JButton("Save and exit");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 3;
        frame.add(choice5,c);

        frame.setVisible(true);
    }
}
