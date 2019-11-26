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

    // EFFECTS: returns index of given item in this list
    int indexOfItem(Item i) {
        return list.indexOf(i);
    }

    // EFFECTS: returns true if this list contains given item
    public boolean listContains(Item i) {
        return list.contains(i);
    }

    // EFFECTS: returns size of this list
    public int listSize() {
        return list.size();
    }

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

    //MODIFIES: this
    //EFFECTS: moves an item from this to another todolist and sets status to done
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


    // EFFECTS: converts strings in file into a todolist of strings and items
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

