package ui;

import java.io.IOException;

public class Main {
    private static TodoListManager todoListManager;

    public static void main(String[] args) throws IOException {
        todoListManager = new TodoListManager();
        todoListManager.run();
    }
}
