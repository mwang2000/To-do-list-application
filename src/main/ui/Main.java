package ui;

import exceptions.TooManyThingsToDoException;
import model.*;

import java.io.IOException;
import java.util.*;

import static model.TodoList.*;

public class Main {
    public static ArrayList<Item> todo;
    public static ArrayList<Item> crossedOff;
    public static ArrayList<Item> examPrep;
    private static Scanner scanner = new Scanner(System.in);
    public static Map<String,Item> todoMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        todo = new ArrayList<>();
        examPrep = new ArrayList<>();
        crossedOff = new ArrayList<>();
        run();
    }

    // run the program
    // MODIFIES: this, crossedOff
    // EFFECTS: adds and removes entries in todo and crossedOff lists and prints lists according to the functions
    // required by the user
    public static void run() throws IOException {
        TodoList tl = new TodoList();
        loadTodo(tl);
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
                tl.save();
                break;
            }
        }
    }

    //EFFECTS: catches TooManyThingsToDoException for UrgentItems
    public static void option2() {
        UrgentItem item = new UrgentItem();
        try {
            setItem(item);
            System.out.println("Would you like to add this item to the exam prep list as well? "
                    + "Enter [1] for yes, [2] for no.");
            if (scanner.nextInt() == 1) {
                TodoList tl = new TodoList();
                tl.addExamPrep(item,examPrep);
            }
        } catch (TooManyThingsToDoException t) {
            System.out.println("Too many tasks to do! Finish some tasks first.");
        } finally {
            System.out.println("\nThe to-do list is:" + returnTodoList());
        }
    }

    //EFFECTS: catches TooManyThingsToDoException for RegularItems
    public static void option1() {
        RegularItem item = new RegularItem();
        try {
            setItem(item);
            System.out.println("Would you like to add this item to the exam prep list as well? "
                    + "Enter [1] for yes, [2] for no.");
            if (scanner.nextInt() == 1) {
                TodoList tl = new TodoList();
                tl.addExamPrep(item,examPrep);
            }
        } catch (TooManyThingsToDoException t) {
            System.out.println("Too many tasks to do! Finish some tasks first.");
        } finally {
            System.out.println("\nThe to-do list is:" + returnTodoList());
        }
    }



    //MODIFIES: this
    //EFFECTS: returns the next integer entered by the user as their choice
    public static int welcome() {
        System.out.println("what would you like to do: [1] add or replace a regular to do list item, "
                + "[2] add or replace an urgent to do item " + "item, [3] cross off an item, "
                + "[4] show all the items, [5] exit");
        int choice = scanner.nextInt();
        scanner = new Scanner(System.in);
        return choice;
    }

    // MODIFIES: this
    // EFFECTS: adds an entry into the todo list consisting of the item and its number
    public static void setItem(Item item) throws TooManyThingsToDoException {
        if (todo.size() == MAX_TODO_SIZE) {
            throw new TooManyThingsToDoException();
        }
        item.setNumber(todo.size() + 1);
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
        updateTodo(todoMap);
    }


    //MODIFIES: this
    //EFFECTS: moves the selected item from the todo list to the crossedOff list
    public static void move() {
        System.out.println("Which item would you like to cross off (enter its keyword)?");
        String removing = scanner.nextLine();
        Item removedItem = todoMap.get(removing);
        System.out.println(removedItem.returnNumberOfItemsLeft(examPrep));
        TodoList tl = new TodoList();
        tl.moveItem(removing);
    }

    // EFFECTS: prints both lists
    public static void printLists() {
        System.out.println(TodoList.returnTodoList());
        System.out.println(TodoList.returnExamPrep());
        System.out.println(TodoList.returnCrossedOffList());
    }
}
