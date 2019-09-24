package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {
    private static ArrayList<Entry> todo;
    private static ArrayList<Entry> crossedOff;
    private static Scanner scanner = new Scanner(System.in);

    public TodoList() {
        todo = new ArrayList<>();
        crossedOff = new ArrayList<>();
    }

    // run the program
    // MODIFIES: this
    // EFFECTS:
    public static void main(String[] args) {
        TodoList todo = new TodoList();
        while (true) {
            System.out.println("what would you like to do [1] add a to do list item, [2] cross off an item [3] "
                    + "show all the items");
            int choice = scanner.nextInt();
            scanner = new Scanner(System.in);
            if (choice == 1) {
                System.out.println("Enter the item text.");
                Entry entry = new Entry(scanner.next());
                todo.addTodo(entry);
            } else if (choice == 2) {
                System.out.println("Which item would you like to cross off?");
                int removing = scanner.nextInt();
                todo.moveItem(removing);
            } else {
                todo.printLists();
            }
        }
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
        crossedOff.add(todo.get(removing - 1));
        todo.remove(removing - 1);
    }

    public int size() {
        return todo.size();
    }

    public void insert(Entry entry) {
        todo.add(entry);
    }

    public boolean contains(Entry entry) {
        return todo.contains(entry);
    }
}