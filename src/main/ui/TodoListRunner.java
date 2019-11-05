package ui;

import model.RegularItem;
import model.UrgentItem;

import java.io.IOException;
import java.util.Scanner;

public class TodoListRunner {
    private TodoListManager todoListManager;
    private Scanner scanner;

    public TodoListRunner() {
        todoListManager = new TodoListManager();
        scanner = new Scanner(System.in);
    }

    public void run() throws IOException {
        todoListManager.loadAtStart();
        while (true) {
            int choice = welcome();
            if (choice == 1) {
                addRegularItem();
            } else if (choice == 2) {
                addUrgentItem();
            } else if (choice == 3) {
                todoListManager.move();
            } else if (choice == 4) {
                todoListManager.printLists();
            } else {
                todoListManager.saveAtEnd();
                break;
            }
        }
    }

    //EFFECTS: catches TooManyThingsToDoException for UrgentItems
    public void addUrgentItem() {
        UrgentItem item = new UrgentItem();
        todoListManager.tryAddItem(item);
    }

    //EFFECTS: catches TooManyThingsToDoException for RegularItems
    public void addRegularItem() {
        RegularItem item = new RegularItem();
        todoListManager.tryAddItem(item);
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
}