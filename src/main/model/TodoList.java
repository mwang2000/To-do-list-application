package model;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;
import ui.Main;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {
    public static ArrayList<Item> todo;
    public static ArrayList<Item> crossedOff;
    public static Scanner scanner = new Scanner(System.in);

    public TodoList() {
        todo = new ArrayList<>();
        crossedOff = new ArrayList<>();
    }


    // EFFECTS: prints the todo list and then the crossed out list
    public static void printLists() {
        if (Main.getTodo().size() == 0) {
            System.out.println("Nothing");
        } else {
            for (Item e : Main.getTodo()) {
                System.out.println(e.todoGetItem());
                if (e instanceof UrgentItem) {
                    ((UrgentItem) e).timeLeft();
                }
            }
        }
        for (Item e : Main.getCrossedOff()) {
            System.out.println(e.crossedOffGetItem());
        }
    }

    public static void moveItem(int removing, ArrayList<Item> todo, ArrayList<Item> crossedOff) {
        todo.get(removing - 1).setStatus("done");
        crossedOff.add(todo.get(removing - 1));
        todo.remove(removing - 1);
    }
}

