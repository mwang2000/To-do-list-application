package model;

import exceptions.OverDueException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Item {
    private String task;
    LocalDate dueDate;
    private String status;
    private TodoList list;

    Item() {
        this.task = "";
        this.status = "not done";
    }

    Item(String task, int y, int m, int d) throws NumberFormatException {
        this.task = task;
        this.dueDate = LocalDate.of(y,m,d);
        this.status = "not done";
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDue(int y, int m, int d) {
        this.dueDate = LocalDate.of(y,m,d);
    }

    public String getTask() {
        return task;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDue() {
        return dueDate;
    }

    public TodoList getList() {
        return list;
    }

    // MODIFIES: this
    // EFFECTS: if this is not assigned to a list, adds given list to the list field calls the method that adds this to
    // the list
    public void addList(TodoList list) {
        if (this.list == null) {
            this.list = list;
            list.addItem(this);
        }
    }

    @Override
    // EFFECTS: returns the string to be displayed for the item
    public String toString() {
        if (this instanceof RegularItem) {
            return printItemHelper("");
        } else {
            try {
                return printItemHelper(((UrgentItem) this).timeLeft());
            } catch (OverDueException e) {
                return printItemHelper(" (This item is overdue!)");
            }
        }
    }

    // REQUIRES: the entry is in the crossedOff list
    // EFFECTS: returns the number and item of the entry
    public String crossedOffGetItem() {
        return task + " due:" + dueDate + " " + status;
    }

    // EFFECTS: prints all the properties of an item
    public static String saveTodo(Item i) {
        String print = "";
        if (i instanceof UrgentItem) {
            try {
                print = print + i.getTask() + "_" + i.getDue().getYear() + "_"
                        + i.getDue().getMonthValue() + "_" + i.getDue().getDayOfMonth() + "_" + i.getStatus()
                        + "_" + ((UrgentItem) i).timeLeft();
            } catch (OverDueException e) {
                print = print + i.getTask() + "_" + i.getDue().getYear() + "_"
                        + i.getDue().getMonthValue() + "_" + i.getDue().getDayOfMonth() + "_" + i.getStatus()
                        + "_" + "This item is overdue!";
            }
        } else {
            print = print + i.getTask() + "_" + i.getDue().getYear() + "_"
                    + i.getDue().getMonthValue() + "_" + i.getDue().getDayOfMonth() + "_" + i.getStatus();
        }
        return print;
    }

    // EFFECTS: prints a string combined with another string
    public String printItemHelper(String addOn) {
        return (list.indexOfItem(this) + 1) + "." + task + " due:" + dueDate + " " + status + " " + addOn;
    }

    // MODIFIES: this
    // EFFECTS: sets fields of an item with elements of an arraylist
    public Item retrieveItemFields(ArrayList<String> partsOfLine, Item item) {
        item.setTask(partsOfLine.get(0));
        item.setDue(Integer.parseInt(partsOfLine.get(1)), Integer.parseInt(partsOfLine.get(2)),
                Integer.parseInt(partsOfLine.get(3)));
        item.setStatus(partsOfLine.get(4));
        return item;
    }

    @Override
    // EFFECTS: make two items that have the same task, due date, and status equal
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(task, item.task) && Objects.equals(dueDate, item.dueDate)
                && Objects.equals(status,item.status);
    }

    @Override
    // EFFECTS: returns hashcode of item
    public int hashCode() {
        return Objects.hash(task, dueDate, status);
    }
}
