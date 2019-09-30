package ui;

import name.TodoList;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TodoList todo = new TodoList();
        todo.run();
    }
}
