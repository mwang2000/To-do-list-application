package ui;

import exceptions.TooManyThingsToDoException;
import model.Item;
import model.TodoList;
import ui.gui.AddItemGUI;
import ui.gui.TodoListsGUI;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TodoListManager {
    public TodoList todo;
    public TodoList crossedOff;
    public TodoListsGUI listGUI;
    private int maxTodoSize = 5;

    public TodoListManager(JFrame frame) {
        todo = new TodoList();
        todo = todo.load();
        crossedOff = new TodoList();
        listGUI = new TodoListsGUI(todo,crossedOff,frame);
        todo.addObserver(listGUI);
    }

    public void addItem(Item item) throws TooManyThingsToDoException {
        if (todo.listSize() == maxTodoSize) {
            throw new TooManyThingsToDoException();
        } else {
            new AddItemGUI(todo,item);
        }
    }

    public TodoList getTodo() {
        return todo;
    }

    public TodoListsGUI getListGUI() {
        return listGUI;
    }

    //EFFECTS: sets and adds an item if the list is not full, otherwise add to
//    public void tryAddItem(Item item) {
//        try {
//            setItem(item);
//            todoMap.put(item.getKeyword(),item);
//            todo.updateTodo(todoMap);
//        } catch (TooManyThingsToDoException t) {
//            System.out.println("Too many tasks to do! Finish some tasks first.");
//        } finally {
//            System.out.println(todo.returnTodoList());
//        }
//    }

    // MODIFIES: this
    // EFFECTS: adds an entry into the todo list consisting of the item and its number
//    public void setItem(Item item) throws TooManyThingsToDoException {
//        int maxTodoSize = 3;
//        if (todoMap.size() == maxTodoSize) {
//            throw new TooManyThingsToDoException();
//        }
//        System.out.println("Enter the unique keyword for the task.");
//        item.setKeyword(scanner.nextLine());
//        System.out.println("Enter the task.");
//        item.setTask(scanner.nextLine());
//        System.out.println("Enter the due date (3 integers in the form of yyyy mm dd):");
//        int y = scanner.nextInt();
//        int m = scanner.nextInt();
//        int d = scanner.nextInt();
//        item.setDue(y, m, d);
//    }

    //MODIFIES: this
    //EFFECTS: moves the selected item from the todo list to the crossedOff list
    public void move() {
        Item item = (Item) listGUI.getList().getSelectedValue();
        todo.moveItem(item,crossedOff);
        listGUI.getTextArea().setText(crossedOff.returnCrossedOffList());
    }

    public void saveAtEnd() {
        try {
            todo.save();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
