package ui;

import exceptions.TooManyThingsToDoException;
import model.Item;
import model.TodoList;
import model.User;
import ui.gui.AddRegularItemGUI;
import ui.gui.AddUrgentItemGUI;
import ui.gui.MoveItemGUI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TodoListManager {
    public TodoList todo;
    public TodoList crossedOff;
    public Map<String, Item> todoMap;
    private User user;

    public TodoListManager() throws IOException {
        todo = new TodoList();
        crossedOff = new TodoList();
        todoMap = new HashMap<>();
        user = new User();
        todo.addUser(user);
        loadAtStart();
    }

    public void addRegularItem() {
        new AddRegularItemGUI(todo);
    }

    public void addUrgentItem() {
        new AddUrgentItemGUI(todo);
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
        new MoveItemGUI(crossedOff,todoMap,todo);
        printLists();
    }

    // EFFECTS: prints both lists
    public void printLists() {
        System.out.println(todo.returnTodoList());
        System.out.println(crossedOff.returnCrossedOffList());
    }

    public void loadAtStart() throws IOException {
        todoMap = todo.load(todoMap);
        todo.updateTodo(todoMap);
    }

    public void saveAtEnd() {
        try {
            todo.save(todoMap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
