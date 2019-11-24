package observer;

import model.TodoList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    public List<TodoListObserver> observers = new ArrayList<>();

    public void addObserver(TodoListObserver o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    public void notifyObservers(TodoList todoList) {
        for (TodoListObserver o : observers) {
            o.update(todoList);
        }
    }

}
