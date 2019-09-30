package name;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class TodoList {
    public static ArrayList<Entry> todo;
    public static ArrayList<Entry> crossedOff;
    public static Scanner scanner = new Scanner(System.in);

    public TodoList() {
        todo = new ArrayList<>();
        crossedOff = new ArrayList<>();
    }

    // run the program
    // MODIFIES: this, crossedOff
    // EFFECTS: adds and removes entries in todo and crossedOff lists and prints lists according to the functions
    // required by the user
    public void run() throws IOException, ClassNotFoundException {
        todo = Loadable.load();
        while (true) {
            int choice = welcome();
            if (choice == 1) {
                Entry entry = promptAddItem();
                addTodo(entry);
            } else if (choice == 2) {
                int removing = promptRemoveItem();
                moveItem(removing);
            } else if (choice == 3) {
                printLists();
            } else {
                Saveable.save();
                break;
            }
        }
    }

    //EFFECTS: returns the next integer entered by the user
    public int promptRemoveItem() {
        System.out.println("Which item would you like to cross off?");
        return scanner.nextInt();
    }

    //EFFECTS: returns the next line the user enters
    public Entry promptAddItem() {
        System.out.println("Enter the item text.");
        return new Entry(scanner.nextLine());
    }

    //MODIFIES: this
    //EFFECTS: returns the next integer entered by the user as their choice
    public int welcome() {
        System.out.println("what would you like to do: [1] add a to do list item, [2] cross off an item, [3] "
                + "show all the items, [4] exit");
        int choice = scanner.nextInt();
        scanner = new Scanner(System.in);
        return choice;
    }


    // REQUIRES: user has chosen to add an item to the todo list
    // MODIFIES: this
    // EFFECTS: adds an entry into the todo list consisting of the item and its number
    public void addTodo(Entry e) {
        e.setNumber(todo.size() + crossedOff.size() + 1);
        todo.add(e);
    }

    // EFFECTS: prints the todo list and then the crossed out list
    public void printLists() {
        for (Entry e : todo) {
            System.out.println(e.todoGetEntry());
        }
        for (Entry e : crossedOff) {
            System.out.println(e.crossedOffGetEntry());
        }
    }

    //MODIFIES: this
    //EFFECTS: moves the selected item from the todo list to the crossedOff list
    public void moveItem(int removing) {
        todo.get(removing - 1).setStatus("done");
        crossedOff.add(todo.get(removing - 1));
        todo.remove(removing - 1);
    }

    // EFFECTS: returns the size of the list
    public int size() {
        return todo.size();
    }

    // MODIFIES: this
    // EFFECTS: inserts an entry into todo
    public void insert(Entry entry) {
        todo.add(entry);
    }

    // EFFECTS: returns true if todo contains the given Entry
    public boolean contains(Entry entry) {
        return todo.contains(entry);
    }

}

