package model;

import ui.Main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import static ui.Main.*;

public abstract class Item {
    protected int number;
    protected String keyword;
    protected String task;
    protected LocalDate dueDate;
    protected String status;
    protected TodoList onList;


    // MODIFIES: this
    // EFFECTS: creates an entry with number of 0 and takes a string as a parameter which becomes the item
    public Item() {
        this.number = 0;
        this.keyword = "";
        this.task = "";
        this.status = "not done";
    }

    // MODIFIES: this
    // EFFECTS: sets the number of the entry to an int number
    public void setNumber(int number) {
        this.number = number;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public String getKeyword() {
        return this.keyword;
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
        return onList;
    }

    // REQUIRES: the entry is in the todo list
    // EFFECTS: returns the number and item of the entry
    public String todoGetItem() {
        return number + ". " + task + " due:" + dueDate + " " + status + " Keyword: " + keyword;
    }

    // REQUIRES: the entry is in the crossedOff list
    // EFFECTS: returns the number and item of the entry
    public String crossedOffGetItem() {
        return task + " due:" + dueDate + " " + status;
    }

    public void addList(TodoList t) {
        if (!(onList == t)) {
            onList = t;
            t.addExamPrep(this);
        }
    }

    public void removeList(TodoList t) {
        if (onList == t) {
            onList = null;
            t.removeExamPrep(this);
        }
    }

    public String returnNumberOfItemsLeft() {
        String print = "";
        print = print + "by crossing off this item, you have " + (onList.todoListSize() - 1)
                + " items in the exam prep list";
        return print;
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
        return Objects.equals(task, item.task) && Objects.equals(dueDate, item.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task, dueDate);
    }
}
