package tests;

import model.TodoList;
import observer.TodoListObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.gui.TodoListsGUI;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TestSubject {
    private TodoList todoList;
    private TodoListObserver observer;

    @BeforeEach
    void runBefore() {
        todoList = new TodoList();
        JFrame frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        observer = new TodoListsGUI(todoList,todoList, frame);
    }

    @Test
    void testAddObserver() {
        todoList.addObserver(observer);
        assertTrue(todoList.observers.contains(observer));
        assertEquals(1,todoList.observers.size());
        todoList.addObserver(observer);
        assertTrue(todoList.observers.contains(observer));
        assertEquals(1,todoList.observers.size());
    }
}
