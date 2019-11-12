package tests;

import model.TodoList;
import model.User;
import observer.Observer;
import observer.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestSubject {
    private TodoList todoList;
    private User observer;
    private User observer2;

    @BeforeEach
    public void runBefore() {
        todoList = new TodoList();
        observer = new User();
        observer2 = new User();
    }

    @Test
    public void testAddObserver() {
        todoList.addObserver(observer);
        assertTrue(todoList.observers.contains(observer));
        assertEquals(1,todoList.observers.size());
        todoList.addObserver(observer);
        assertTrue(todoList.observers.contains(observer));
        assertEquals(1,todoList.observers.size());
    }
}
