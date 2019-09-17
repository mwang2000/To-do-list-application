package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {
    private static ArrayList<Entry> todo = new ArrayList<>();
    private static ArrayList<Entry> crossedOff = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("what would you like to do [1] add a to do list item, [2] cross off an item [3] "
                    + "show all the items");
            int choice = scanner.nextInt();
            scanner = new Scanner(System.in);
            if (choice == 1) {
                System.out.println("Enter the item text.");
                addTodo();
            } else if (choice == 2) {
                System.out.println("Which item would you like to cross off?");
                moveItem();
            } else {
                printLists();
            }
        }
    }

    public static void addTodo() {
        Entry entry = new Entry(scanner.next());
        entry.setNumber(todo.size() + crossedOff.size() + 1);
        todo.add(entry);
    }

    public static void printLists() {
        for (Entry e : todo) {
            System.out.println(e.todoGetItem());
        }
        for (Entry e : crossedOff) {
            System.out.println(e.crossedOffGetItem());
        }
    }

    public static void moveItem() {
        int removing = scanner.nextInt();
        crossedOff.add(todo.get(removing - 1));
        todo.remove(removing - 1);
    }
}