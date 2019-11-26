package observer;

import model.TodoList;

public interface TodoListObserver {
    // EFFECTS: changes JList in GUI to be the new TodoList
    void update(TodoList todo);
}
