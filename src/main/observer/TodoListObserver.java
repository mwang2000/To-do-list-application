package observer;

import model.TodoList;

import java.io.IOException;

public interface TodoListObserver {
    void update(TodoList todo);
}
