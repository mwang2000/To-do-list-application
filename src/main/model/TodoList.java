package model;

import exceptions.OverDueException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

//    public static void addTodo(ArrayList<Item> todo, Item item) throws TooManyThingsToDoException {
//        if (todo.size() == MAX_TODO_SIZE) {
//            throw new TooManyThingsToDoException();
//        }
//        todo.add(item);
//    }

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

    public void save(ArrayList<Item> list) throws FileNotFoundException {
        File file = new File(String.valueOf(Paths.get("./data/file")));
        PrintWriter printWriter = new PrintWriter(file);
        for (Item i : list) {
            printWriter.println(saveTodo(i));
        }
        printWriter.close();
    }

    public String saveTodo(Item i) {
        String print = "";
        if (i instanceof UrgentItem) {
            try {
                print = print + i.getNumber() + "_" + i.getTask() + "_" + i.getDue().getYear() + "_"
                        + i.getDue().getMonthValue() + "_" + i.getDue().getDayOfMonth() + "_" + i.getStatus()
                        + "_" + ((UrgentItem) i).timeLeft();
            } catch (OverDueException e) {
                print = print + i.getNumber() + "_" + i.getTask() + "_" + i.getDue().getYear() + "_"
                        + i.getDue().getMonthValue() + "_" + i.getDue().getDayOfMonth() + "_" + i.getStatus()
                        + "_" + "This item is overdue!";
            }
        } else {
            print = print + i.getNumber() + "_" + i.getTask() + "_" + i.getDue().getYear() + "_"
                    + i.getDue().getMonthValue() + "_" + i.getDue().getDayOfMonth() + "_" + i.getStatus();
        }
        return print;
    }

    public ArrayList<Item> load(ArrayList<Item> list) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./data/file"));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnBar(line);
            if (partsOfLine.size() == 7) {
                Item item = new UrgentItem();
                retrieveItemFields(partsOfLine, item,list);
            } else {
                Item item = new RegularItem();
                retrieveItemFields(partsOfLine, item,list);
            }
        }
        return list;
    }

    public void retrieveItemFields(ArrayList<String> partsOfLine, Item item,ArrayList<Item> list) {
        item.setNumber(Integer.parseInt(partsOfLine.get(0)));
        item.setTask(partsOfLine.get(1));
        item.setDue(Integer.parseInt(partsOfLine.get(2)), Integer.parseInt(partsOfLine.get(3)),
                Integer.parseInt(partsOfLine.get(4)));
        item.setStatus(partsOfLine.get(5));
        list.add(item);
    }

    public static ArrayList<String> splitOnBar(String line) {
        String[] splits = line.split("_");
        return new ArrayList<>(Arrays.asList(splits));
    }

}

