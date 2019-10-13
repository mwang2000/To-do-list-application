package model;

import exceptions.OverDueException;
import exceptions.TooManyThingsToDoException;

import java.util.ArrayList;

import static ui.Main.crossedOff;

public class TodoList {
    public static int MAX_TODO_SIZE = 3;

    // EFFECTS: prints the todo list and then the crossed out list
    public static String returnTodoList(ArrayList<Item> todo) {
        if (todo.size() == 0) {
            return "Nothing in the to do list.";
        } else {
            String print = "";
            for (Item e : todo) {
                if (e instanceof UrgentItem) {
                    try {
                        print = UrgentItem.printUrgentItem(print, e);
                    } catch (OverDueException od) {
                        print = UrgentItem.printOverdue(print,e);
                    }
                } else {
                    print = RegularItem.printRegularItem(print, e);
                }
            }
            return print;
        }
    }


    //EFFECTS: returns the crossed off list as a string to be printed
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

    //MODIFIES: todo,crossedOff
    //EFFECTS: moves an item from todo to crossedOff and changes the status to "done"
    public static void moveItem(int removing, ArrayList<Item> todo, ArrayList<Item> crossedOff) {
        todo.get(removing - 1).setStatus("done");
        todo.get(removing - 1).setNumber(0);
        crossedOff.add(todo.get(removing - 1));
        todo.remove(removing - 1);
    }

    //EFFECTS: adds regular item to todo list unless there are too many items in todo
//    public static boolean option1(ArrayList<Item> todo) {
//        try {
//            addRegularItem(todo);
//        } catch (TooManyThingsToDoException t) {
//            System.out.println("There are too many things to do! Finish some tasks first.");
//            return false;
//        }
//        return true;
//    }

    public static void addTodo(ArrayList<Item> todo, Item item) throws TooManyThingsToDoException {
        if (todo.size() == MAX_TODO_SIZE) {
            throw new TooManyThingsToDoException();
        }
        todo.add(item);
    }

    public static void option1(ArrayList<Item> todo,Item item) {
        try {
            TodoList.addTodo(todo,item);
        } catch (TooManyThingsToDoException t) {
            System.out.println("Too many things to do! Finish some tasks first.");
        } finally {
            System.out.println("The to-do list is:\n" + returnTodoList(todo));
        }
    }

    public static void option2(ArrayList<Item> todo,Item item) {
        try {
            TodoList.addTodo(todo,item);
        } catch (TooManyThingsToDoException t) {
            System.out.println("Too many things to do! Finish some tasks first.");
        } finally {
            System.out.println("The to-do list is:\n" + returnTodoList(todo));
        }
    }

    //EFFECTS: adds urgentItem to todo list unless there are too many items in todo
//    public static boolean option2(ArrayList<Item> todo) {
//        try {
//            addUrgentItem(todo);
//        } catch (TooManyThingsToDoException t) {
//            System.out.println("There are too many things to do! Finish some tasks first.");
//            return false;
//        }
//        return true;
//    }

}

