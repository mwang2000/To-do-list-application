package model;

import exceptions.OverDueException;
import observer.Subject;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TodoList extends Subject implements Saveable,Loadable {
    private ArrayList<Item> list;

    public TodoList() {
        list = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds an item to list
    public void addItem(Item i) {
        if (!list.contains(i)) {
            list.add(i);
            i.addList(this);
            notifyObservers(this);
        }
    }

    public ArrayList<Item> getList() {
        return list;
    }

    public int indexOfItem(Item i) {
        return list.indexOf(i);
    }

    // EFFECTS: returns true if list contains given item
    public boolean listContains(Item i) {
        return list.contains(i);
    }

    // EFFECTS: returns size of list
    public int listSize() {
        return list.size();
    }

    // EFFECTS: prints the todo list and then the crossed out list
//    public String returnTodoList() {
//        if (list.size() == 0) {
//            return "Nothing in the to do list.\n";
//        } else {
//            String print = "The to do list is:\n";
//            for (Item e : list) {
//                int number = (list.indexOf(e) + 1);
//                if (e instanceof UrgentItem) {
//                    try {
//                        print = print + ((UrgentItem) e).printUrgentItem(number) + "\n";
//                    } catch (OverDueException od) {
//                        print = print + ((UrgentItem) e).printOverdue(number) + "\n";
//                    }
//                } else {
//                    print = print + ((RegularItem) e).printRegularItem(number) + "\n";
//                }
//            }
//            return print;
//        }
//    }


    //EFFECTS: returns the crossed off list as a string to be printed
    public String returnCrossedOffList() {
        if (list.size() == 0) {
            return "\nNothing in the crossed off list.";
        } else {
            String print = "";
            for (Item e : list) {
                print = print + "\n" + e.crossedOffGetItem();
            }
            return "The crossed off list is" + print;
        }
    }

    //MODIFIES: todoMap,todo,crossedOff
    //EFFECTS: moves an item from todoMap and examPrep ,if applicable, to crossedOff and changes the status to "done"
    public void moveItem(Item item,TodoList crossedOff) {
        list.remove(item);
        item.setStatus("done");
        crossedOff.addItem(item);
        notifyObservers(this);
    }

    // EFFECTS: saves items in to do list as strings
    public void save() throws FileNotFoundException {
        File file = new File(String.valueOf(Paths.get("./data/file")));
        PrintWriter printWriter = new PrintWriter(file);
        for (Item i : list) {
            printWriter.println(Item.saveTodo(i));
        }
        printWriter.close();
    }


    // EFFECTS: converts strings in file into a hashmap of strings and items
    public TodoList load() {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get("./data/file"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        TodoList list = new TodoList();
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnUnderscore(line);
            if (partsOfLine.size() == 6) {
                Item item = new UrgentItem();
                list.addItem(item.retrieveItemFields(partsOfLine,item));
            } else if (partsOfLine.size() == 5) {
                Item item = new RegularItem();
                list.addItem(item.retrieveItemFields(partsOfLine,item));
            }
        }
        return list;
    }

    // EFFECTS: splits a line of string into an arraylist of string where the underscores lie
    public ArrayList<String> splitOnUnderscore(String line) {
        String[] splits = line.split("_");
        return new ArrayList<>(Arrays.asList(splits));
    }
}

