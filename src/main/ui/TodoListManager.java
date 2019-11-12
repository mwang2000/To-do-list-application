package ui;

import exceptions.TooManyThingsToDoException;
import model.Item;
import model.TodoList;
import model.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TodoListManager {
    public TodoList todo;
    public TodoList crossedOff;
    public Scanner scanner;
    public Map<String, Item> todoMap;
    private User user;

    public TodoListManager() {
        todo = new TodoList();
        crossedOff = new TodoList();
        scanner = new Scanner(System.in);
        todoMap = new HashMap<>();
        user = new User();
        todo.addUser(user);
    }

    //EFFECTS: sets and adds an item if the list is not full, otherwise add to
    public void tryAddItem(Item item) throws IOException {
        try {
            setItem(item);
            todo.updateTodo(todoMap);
        } catch (TooManyThingsToDoException t) {
            System.out.println("Too many tasks to do! Finish some tasks first.");
        } finally {
            System.out.println("\nThe to-do list is:" + todo.returnTodoList());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds an entry into the todo list consisting of the item and its number
    public void setItem(Item item) throws TooManyThingsToDoException {
        int maxTodoSize = 3;
        if (todoMap.size() == maxTodoSize) {
            throw new TooManyThingsToDoException();
        }
        item.setNumber(todoMap.size() + 1);
        System.out.println("Enter the unique keyword for the task.");
        item.setKeyword(scanner.nextLine());
        System.out.println("Enter the task.");
        item.setTask(scanner.nextLine());
        System.out.println("Enter the due date (3 integers in the form of yyyy mm dd):");
        int y = scanner.nextInt();
        int m = scanner.nextInt();
        int d = scanner.nextInt();
        item.setDue(y, m, d);
        todoMap.put(item.getKeyword(),item);
    }

    //MODIFIES: this
    //EFFECTS: moves the selected item from the todo list to the crossedOff list
    public void move() throws IOException {
        System.out.println("Which item would you like to cross off (enter its keyword)?");
        String removing = scanner.nextLine();
//        Item removedItem = todoMap.get(removing);
//        if (removedItem.getList() == (Item.emptyList)) {
//            System.out.println(removedItem.returnNumberOfItemsLeft());
//        }
        crossedOff.moveItem(removing,todoMap,todo);
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

    public void saveAtEnd() throws FileNotFoundException {
        todo.save(todoMap);
    }


}
