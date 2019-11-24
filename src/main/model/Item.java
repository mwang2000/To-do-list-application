package model;

import exceptions.OverDueException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Item {
    protected String task;
    protected LocalDate dueDate;
    protected String status;
    private TodoList list;

    // MODIFIES: this
    // EFFECTS: creates an entry with number of 0 and takes a string as a parameter which becomes the item
    public Item() {
        this.task = "";
        this.status = "not done";
    }

    public Item(String task, int y, int m, int d) throws NumberFormatException {
        this.task = task;
        this.dueDate = LocalDate.of(y,m,d);
        this.status = "not done";
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

    public TodoList getList() {
        return list;
    }

    public void addList(TodoList list) {
        if (this.list == null) {
            this.list = list;
            list.addItem(this);
        }
    }

    @Override
    // REQUIRES: the entry is in the todo list
    // EFFECTS: returns the number and item of the entry
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

//    public String returnNumberOfItemsLeft() {
//        String print = "";
//        print = print + "by crossing off this item, you have " + (TodoList.examPrep.size() - 1)
//                + " items in the exam prep list";
//        return print;
//    }

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
        String print = (list.indexOfItem(this) + 1) + "." + task + " due:" + dueDate + " " + status + " " + addOn;
        return print;
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
    public int hashCode() {
        return Objects.hash(task, dueDate, status);
    }
}
