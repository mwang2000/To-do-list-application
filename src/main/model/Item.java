package model;

import exceptions.OverDueException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ui.Main.todo;

public abstract class Item implements Serializable,Loadable,Saveable {
    protected int number;
    protected String task;
    protected LocalDate dueDate;
    protected String status;

    // MODIFIES: this
    // EFFECTS: creates an entry with number of 0 and takes a string as a parameter which becomes the item
    public Item() {
        this.number = 0;
        this.task = "";
        this.status = "not done";
    }

    // MODIFIES: this
    // EFFECTS: sets the number of the entry to an int number
    public void setNumber(int number) {
        this.number = number;
    }

    // MODIFIES: this
    // EFFECTS: sets the item of the entry to the given string
    public void setTask(String task) {
        this.task = task;
    }

    // MODIFIES: this
    // EFFECTS: sets the status of the entry to the given string
    public void setStatus(String status) {
        this.status = status;
    }

    // REQUIRES: the date given is not in the past
    // MODIFIES: this
    // EFFECTS: sets the due date of the entry to the user input
    public void setDue(int y, int m, int d) {
        this.dueDate = LocalDate.of(y,m,d);
    }

    // EFFECTS: returns the number of the entry it is called on
    public int getNumber() {
        return number;
    }

    // EFFECTS: returns the item of the entry it is called on
    public String getTask() {
        return task;
    }

    // EFFECTS: returns the status of the entry it is called on
    public String getStatus() {
        return status;
    }

    // EFFECTS: returns the due date of the entry
    public LocalDate getDue() {
        return dueDate;
    }

    // REQUIRES: the entry is in the todo list
    // EFFECTS: returns the number and item of the entry
    public String todoGetItem() {
        return number + ". " + task + " due:" + dueDate + " " + status + " (to do)";
    }

    // REQUIRES: the entry is in the crossedOff list
    // EFFECTS: returns the number and item of the entry
    public String crossedOffGetItem() {
        return number + ". " + task + " due:" + dueDate + " " + status + " (crossed off)";
    }

//    public ArrayList<Item> load() throws IOException, ClassNotFoundException {
//        FileInputStream fis = new FileInputStream("./data/file");
//        ObjectInputStream ois = new ObjectInputStream(fis);
//        ArrayList<Item> todo = (ArrayList<Item>) ois.readObject();
//        ois.close();
//        return todo;
//    }

//    public ArrayList<Item> load() throws IOException {
//        File file = new File(String.valueOf(Paths.get("./data/file")));
//        List<String> lines = Files.readAllLines(Paths.get(String.valueOf(file)));
//        int i = 0;
//        Item item = new RegularItem();
//        while (lines != null) {
//            item.setNumber(Integer.parseInt(lines.get(0)));
//            item.setTask(lines.get(1));
//            item.setDueDate();
//        }
//    }

//    public void save() throws IOException {
//        FileOutputStream fos = new FileOutputStream("./data/file");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject(todo);
//        oos.close();
//    }
}
