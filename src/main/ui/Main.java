package ui;

import exceptions.TooManyThingsToDoException;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static model.TodoList.*;

public class Main {
    public static ArrayList<Item> todo;
    public static ArrayList<Item> crossedOff;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        todo = new ArrayList<>();
        crossedOff = new ArrayList<>();
        run();
    }

    // run the program
    // MODIFIES: this, crossedOff
    // EFFECTS: adds and removes entries in todo and crossedOff lists and prints lists according to the functions
    // required by the user
    public static void run() throws IOException, ClassNotFoundException {
        Item e = loadTodo();
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
                e.save();
                break;
            }
        }
    }

    //EFFECTS: catches TooManyThingsToDoException for UrgentItems
    public static void option2() {
        UrgentItem item = new UrgentItem();
        try {
            addTodo(todo,item);
        } catch (TooManyThingsToDoException t) {
            System.out.println("Too many tasks to do! Finish some tasks first.");
        } finally {
            System.out.println("\nThe to-do list is:" + returnTodoList(todo));
        }
    }

    //EFFECTS: catches TooManyThingsToDoException for RegularItems
    public static void option1() {
        RegularItem item = new RegularItem();
        try {
            addTodo(todo,item);
        } catch (TooManyThingsToDoException t) {
            System.out.println("Too many tasks to do! Finish some tasks first.");
        } finally {
            System.out.println("\nThe to-do list is:" + returnTodoList(todo));
        }
    }


    public static Item loadTodo() throws IOException, ClassNotFoundException {
        Item e = new RegularItem();
        todo = e.load();
        return e;
    }


    //MODIFIES: this
    //EFFECTS: returns the next integer entered by the user as their choice
    public static int welcome() {
        System.out.println("what would you like to do: [1] add a regular to do list item, [2] add an urgent to do list "
                + "item, [3] cross off an item, [4] show all the items, [5] exit");
        int choice = scanner.nextInt();
        scanner = new Scanner(System.in);
        return choice;
    }

    //REQUIRES: choice == 1
    //EFFECTS: returns the next line the user enters
//    public static Item addRegularItem(ArrayList<Item> todo) throws TooManyThingsToDoException {
//        if (todo.size() == MAX_TODO_SIZE) {
//            throw new TooManyThingsToDoException();
//        }
//        Item entry = new RegularItem();
//        setItem(entry);
//        return entry;
//    }

//    public static Item setRegularItem(ArrayList<Item> todo) throws InvalidDateException, TooManyThingsToDoException {
//        if (todo.size() == MAX_TODO_SIZE) {
//            throw new TooManyThingsToDoException();
//        }
//        Item item = new RegularItem();
//        item.setNumber(todo.size() + 1);
//        System.out.println("Enter the due date (3 integers in the form of yyyy mm dd):");
//        int y = scanner.nextInt();
//        int m = scanner.nextInt();
//        int d = scanner.nextInt();
//        item.setDueDate(y, m, d);
//        if (ChronoUnit.DAYS.between(LocalDate.now(),item.getDueDate()) < 0) {
//            throw new InvalidDateException();
//        }
//        return item;
//    }

    //REQUIRES: choice == 2
    //EFFECTS: returns the next line the user enters
//    public static Item addUrgentItem(ArrayList<Item> todo) throws TooManyThingsToDoException {
//        if (todo.size() == MAX_TODO_SIZE) {
//            throw new TooManyThingsToDoException();
//        }
//        Item entry = new UrgentItem();
//        setItem(entry);
//        return entry;
//    }

//    public static Item setUrgentItem(ArrayList<Item> todo) throws InvalidDateException, TooManyThingsToDoException {
//        if (todo.size() == MAX_TODO_SIZE) {
//            throw new TooManyThingsToDoException();
//        }
//        Item item = new UrgentItem();
//        item.setNumber(todo.size() + 1);
//        System.out.println("Enter the due date (3 integers in the form of yyyy mm dd):");
//        int y = scanner.nextInt();
//        int m = scanner.nextInt();
//        int d = scanner.nextInt();
//        item.setDueDate(y, m, d);
//        if (ChronoUnit.DAYS.between(LocalDate.now(),item.getDueDate()) < 0) {
//            throw new InvalidDateException();
//        }
//        return item;
//    }

    // REQUIRES: choice == 1 or choice == 2
    // MODIFIES: this
    // EFFECTS: adds an entry into the todo list consisting of the item and its number
    public static void setItem(Item item) throws TooManyThingsToDoException {
        if (todo.size() == MAX_TODO_SIZE) {
            throw new TooManyThingsToDoException();
        }
        item.setNumber(todo.size() + 1);
        System.out.println("Enter the task.");
        item.setTask(scanner.nextLine());
        System.out.println("Enter the due date (3 integers in the form of yyyy mm dd):");
        int y = scanner.nextInt();
        int m = scanner.nextInt();
        int d = scanner.nextInt();
        item.setDueDate(y, m, d);
    }


    //MODIFIES: this
    //EFFECTS: moves the selected item from the todo list to the crossedOff list
    public static void move() {
        System.out.println("Which item would you like to cross off?");
        int removing = scanner.nextInt();
        TodoList.moveItem(removing, todo, crossedOff);
    }

    // EFFECTS: prints both lists
    public static void printLists() {
        System.out.println(TodoList.returnTodoList(todo));
        System.out.println(TodoList.returnCrossedOffList(crossedOff));
    }
}
