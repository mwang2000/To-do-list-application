package ui;

import model.Item;
import model.RegularItem;
import model.UrgentItem;
import ui.gui.AddRegularItemGUI;

import java.io.IOException;
import java.util.Scanner;

public class TodoListRunner {
    private TodoListManager todoListManager;
    private Scanner scanner;

    public TodoListRunner() throws IOException {
        todoListManager = new TodoListManager();
        scanner = new Scanner(System.in);
        todoListManager.loadAtStart();
//        run();
    }

//    public void run() throws IOException {
//        int choice = 1;
//        while (choice >= 1 && choice <= 4) {
//            choice = welcome();
//            switch (choice) {
//                case 1: addRegularItem();
//                break;
//                case 2: addUrgentItem();
//                break;
//                case 3: todoListManager.move();
//                break;
//                case 4: todoListManager.printLists();
//                break;
//                default: todoListManager.saveAtEnd();
//                break;
//            }

    //EFFECTS: catches TooManyThingsToDoException for UrgentItems
    public void addUrgentItem() {
        UrgentItem item = new UrgentItem();
//        todoListManager.tryAddItem(item);
    }

    //EFFECTS: catches TooManyThingsToDoException for RegularItems
    public void addRegularItem(String keyword, String task, int y, int m, int d) {
        new AddRegularItemGUI();
        RegularItem item = new RegularItem(keyword,task,y,m,d);
        todoListManager.addItem(item);
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