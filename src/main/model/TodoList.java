package model;

import java.util.ArrayList;

public class TodoList {

    // EFFECTS: prints the todo list and then the crossed out list
    public static String returnTodoList(ArrayList<Item> todo) {
        if (todo.size() == 0) {
            return "Nothing in the to do list.";
        } else {
            String print = "";
            for (Item e : todo) {
                if (e instanceof UrgentItem) {
                    print = printUrgentItem(print, e);
                } else {
                    print = printRegularItem(print, e);
                }
            }
            return print;
        }
    }

    // EFFECTS: returns a regular item to be printed
    public static String printRegularItem(String print, Item e) {
        if (print.equals("")) {
            print = print + (e.todoGetItem());
        } else {
            print = print + "\n" + (e.todoGetItem());
        }
        return print;
    }

    // EFFECTS: returns an urgent item to be printed
    public static String printUrgentItem(String print, Item e) {
        if (print.equals("")) {
            print = print + e.todoGetItem() + "\n" + ((UrgentItem) e).timeLeft();
        } else {
            print = print + "\n" + e.todoGetItem() + "\n" + ((UrgentItem) e).timeLeft();
        }
        return print;
    }

    public static String returnCrossedOffList(ArrayList<Item> crossedOff) {
        if (crossedOff.size() == 0) {
            return "\nNothing in the crossed off list.";
        } else {
            String print = "";
            for (Item e : crossedOff) {
                print = print + "\n" + e.crossedOffGetItem();
            }
            return print;
        }
    }

    public static void moveItem(int removing, ArrayList<Item> todo, ArrayList<Item> crossedOff) {
        todo.get(removing - 1).setStatus("done");
        crossedOff.add(todo.get(removing - 1));
        todo.remove(removing - 1);
    }
}

