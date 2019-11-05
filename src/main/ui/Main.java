package ui;

import java.io.IOException;

public class Main {
    private static TodoListRunner todoListRunner;

    public static void main(String[] args) throws IOException {
        todoListRunner = new TodoListRunner();
        todoListRunner.run();
    }
}
