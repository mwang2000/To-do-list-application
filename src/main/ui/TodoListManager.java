package ui;

import exceptions.TooManyThingsToDoException;
import model.Item;
import model.TodoList;
import ui.gui.AddItemGUI;
import ui.gui.TodoListsGUI;

import javax.swing.*;
import java.io.FileNotFoundException;

public class TodoListManager {
    private TodoList todo;
    private TodoList crossedOff;
    private TodoListsGUI listGUI;
    private int maxTodoSize = 5;

    public TodoListManager(JFrame frame) {
        todo = new TodoList();
        todo = todo.load();
        crossedOff = new TodoList();
        listGUI = new TodoListsGUI(todo,crossedOff,frame);
        todo.addObserver(listGUI);
    }

    // MODIFIES: this
    // EFFECTS: if the list size is not at capacity, call to AddItemGUI with the to-do list and given item
    public void addItem(Item item) throws TooManyThingsToDoException {
        if (todo.listSize() == maxTodoSize) {
            throw new TooManyThingsToDoException();
        } else {
            new AddItemGUI(todo,item);
        }
    }

    //MODIFIES: this
    //EFFECTS: takes the selected item in the JList as input and moves it to the crossedOff list, then displays the new
    // crossedOff list on the GUI
    public void move() {
        Item item = (Item) listGUI.getList().getSelectedValue();
        todo.moveItem(item,crossedOff);
        listGUI.getTextArea().setText(crossedOff.returnCrossedOffList());
    }

    // EFFECTS: makes call to method to save the contents of todo
    public void saveAtEnd() {
        try {
            todo.save();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
