package observer;

import model.TodoList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    public List<TodoListObserver> observers = new ArrayList<>();

    // MODIFIES: this
    // EFFECTS: adds given TodoListObserver to observers if observers does not already contain it
    public void addObserver(TodoListObserver o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    // EFFECTS: calls update on each TodoListObserver in observers
    protected void notifyObservers(TodoList todoList) {
        for (TodoListObserver o : observers) {
            o.update(todoList);
        }
    }

}
