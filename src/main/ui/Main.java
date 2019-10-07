package ui;

import model.Item;
import model.RegularItem;
import model.TodoList;
import model.UrgentItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Item> todo;
    public static ArrayList<Item> crossedOff;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        todo = new ArrayList<>();
        crossedOff = new ArrayList<>();
        run();
    }

    public static ArrayList<Item> getTodo() {
        return todo;
    }

    public static ArrayList<Item> getCrossedOff() {
        return crossedOff;
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
                addRegularItem();
            } else if (choice == 2) {
                addUrgentItem();
            } else if (choice == 3) {
                move();
            } else if (choice == 4) {
                TodoList.printLists();
            } else {
                e.save();
                break;
            }
        }
    }

    public static Item loadTodo() throws IOException, ClassNotFoundException {
        Item e = new RegularItem("");
//        todo = e.load();
        return e;
    }

    //REQUIRES: choice == 1
    //EFFECTS: returns the next line the user enters
    public static void addRegularItem() {
        System.out.println("Enter the item text.");
        Item entry = new RegularItem(scanner.nextLine());
        addTodo(entry);
    }

    //REQUIRES: choice == 2
    //EFFECTS: returns the next line the user enters
    public static void addUrgentItem() {
        System.out.println("Enter the item text.");
        Item entry = new UrgentItem(scanner.nextLine());
        addTodo(entry);
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

    // REQUIRES: choice == 1 or choice == 2
    // MODIFIES: this
    // EFFECTS: adds an entry into the todo list consisting of the item and its number
    public static void addTodo(Item item) {
        item.setNumber(todo.size() + crossedOff.size() + 1);
        System.out.println("Enter the due date (in yyyy mm dd):");
        int y = scanner.nextInt();
        int m = scanner.nextInt();
        int d = scanner.nextInt();
        item.setDueDate(y, m, d);
        todo.add(item);
    }

    //MODIFIES: this
    //EFFECTS: moves the selected item from the todo list to the crossedOff list
    public static void move() {
        System.out.println("Which item would you like to cross off?");
        int removing = scanner.nextInt();
        TodoList.moveItem(removing, todo, crossedOff);
    }
}
