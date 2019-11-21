package ui;

import ui.gui.TodoListRunnerGUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    private static TodoListRunner todoListRunner;

    public static void main(String[] args) throws IOException {
//        new TodoListRunner();
        new TodoListRunnerGUI();
    }
}
