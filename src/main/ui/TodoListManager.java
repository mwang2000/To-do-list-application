package ui;

import exceptions.TooManyThingsToDoException;
import model.Item;
import model.RegularItem;
import model.TodoList;
import model.UrgentItem;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TodoListManager {
    private TodoList todo;
    private TodoList crossedOff;
    private Scanner scanner;
    public Map<String, Item> todoMap = new HashMap<>();
    private static int maxTodoSize = 3;

    public TodoListManager() {
        todo = new TodoList();
        crossedOff = new TodoList();
        scanner = new Scanner(System.in);
    }

    public void run() throws IOException {
        todo.loadTodo();
        while (true) {
            int choice = welcome();
            if (choice == 1) {
                option1();
            } else if (choice == 2) {
                option2();
            } else if (choice == 3) {
                move();
            } else if (choice == 4) {
                printLists();
            } else {
                todo.save(todoMap);
                break;
            }
        }
    }


    //EFFECTS: catches TooManyThingsToDoException for UrgentItems
    public void option2() {
        UrgentItem item = new UrgentItem();
        tryAddItem(item);
    }

    //EFFECTS: catches TooManyThingsToDoException for RegularItems
    public void option1() {
        RegularItem item = new RegularItem();
        tryAddItem(item);
    }

    //EFFECTS: sets and adds an item if the list is not full, otherwise add to
    public void tryAddItem(Item item) {
        try {
            setItem(item);
            todo.updateTodo(todoMap);
        } catch (TooManyThingsToDoException t) {
            System.out.println("Too many tasks to do! Finish some tasks first.");
        } finally {
            System.out.println("\nThe to-do list is:" + todo.returnTodoList());
        }
    }


    //MODIFIES: this
    //EFFECTS: returns the next integer entered by the user as their choice
    public int welcome() {
        System.out.println("what would you like to do: [1] add or replace a regular to do list item, "
                + "[2] add or replace an urgent to do item " + "item, [3] cross off an item, "
                + "[4] show all the items, [5] exit");
        int choice = scanner.nextInt();
        scanner = new Scanner(System.in);
        return choice;
    }

    // MODIFIES: this
    // EFFECTS: adds an entry into the todo list consisting of the item and its number
    public void setItem(Item item) throws TooManyThingsToDoException {
        if (todoMap.size() == maxTodoSize) {
            throw new TooManyThingsToDoException();
        }
        item.setNumber(todoMap.size() + 1);
        System.out.println("Enter the unique keyword for the task.");
        String key = scanner.nextLine();
        item.setKeyword(key);
        System.out.println("Enter the task.");
        item.setTask(scanner.nextLine());
        System.out.println("Enter the due date (3 integers in the form of yyyy mm dd):");
        int y = scanner.nextInt();
        int m = scanner.nextInt();
        int d = scanner.nextInt();
        item.setDue(y, m, d);
        todoMap.put(key,item);
    }

    //MODIFIES: this
    //EFFECTS: moves the selected item from the todo list to the crossedOff list
    public void move() {
        System.out.println("Which item would you like to cross off (enter its keyword)?");
        String removing = scanner.nextLine();
//        Item removedItem = todoMap.get(removing);
//        if (removedItem.getList() == (Item.emptyList)) {
//            System.out.println(removedItem.returnNumberOfItemsLeft());
//        }
        crossedOff.moveItem(removing,todoMap,todo);
    }

    // EFFECTS: prints both lists
    public void printLists() {
        System.out.println(todo.returnTodoList());
        System.out.println(crossedOff.returnCrossedOffList());
    }
}