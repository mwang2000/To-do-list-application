package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {
    private static ArrayList<ListEntry> todo;
    private static ArrayList<ListEntry> crossedOff;
    private static Scanner scanner;
    private static ListEntry listEntry;


    public static void main(String[] args) {
        todo = new ArrayList<>();
        crossedOff = new ArrayList<>();
        scanner = new Scanner(System.in);
        listEntry = new ListEntry();

        new TodoList();

    }

    private TodoList() {
        while (true) {
            System.out.println("what would you like to do [1] add a to do list item, [2] cross off an item [3] "
                    + "show all the items");
            int choice = scanner.nextInt();
            scanner = new Scanner(System.in);
            if (choice == 1) {
                System.out.println("Enter the item text.");
                addItem(listEntry, todo);
            } else if (choice == 2) {
                System.out.println("Which item would you like to cross off?");
                int removing = scanner.nextInt();
                removeItem(removing);
            } else {
                System.out.println(todo);
            }
        }
    }

    private void addItem(ListEntry listEntry, ArrayList<ListEntry> list) {
        String newItem = scanner.nextLine();
        listEntry.increaseNumber();
        listEntry.setItem(newItem);
        listEntry.setStatus(false);
        list.add(listEntry);
        //listEntry = new ListEntry();

    }

    private void removeItem(int removing) {
        todo.remove(removing - 1);
    }

}
