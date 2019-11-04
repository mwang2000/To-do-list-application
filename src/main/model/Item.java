package model;

import exceptions.OverDueException;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Item {
    protected int number;
    protected String keyword;
    protected String task;
    protected LocalDate dueDate;
    protected String status;
//    protected TodoList onList;
    public static TodoList emptyList;


    // MODIFIES: this
    // EFFECTS: creates an entry with number of 0 and takes a string as a parameter which becomes the item
    public Item() {
        this.number = 0;
        this.keyword = "";
        this.task = "";
        this.status = "not done";
//        this.onList = new TodoList();
        emptyList = new TodoList();
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

//    public TodoList getList() {
//        return onList;
//    }

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

//    public void addList(TodoList t) {
//        if (!(onList == t)) {
//            onList = t;
//            t.addExamPrep(this);
//        }
//    }

//    public void removeList(TodoList t) {
//        if (onList == t) {
//            onList = emptyList;
//            t.removeExamPrep(this);
//        }
//    }

//    public String returnNumberOfItemsLeft() {
//        String print = "";
//        print = print + "by crossing off this item, you have " + (TodoList.examPrep.size() - 1)
//                + " items in the exam prep list";
//        return print;
//    }


    public static String saveTodo(Item i) {
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

    public String printItemHelper(String print, String addOn) {
        if (print.equals("")) {
            print = print + todoGetItem() + addOn;
        } else {
            print = print + "\n" + todoGetItem() + addOn;
        }
        return print;
    }

    public Item retrieveItemFields(ArrayList<String> partsOfLine, Item item) {
        item.setKeyword(partsOfLine.get(0));
        item.setNumber(Integer.parseInt(partsOfLine.get(1)));
        item.setTask(partsOfLine.get(2));
        item.setDue(Integer.parseInt(partsOfLine.get(3)), Integer.parseInt(partsOfLine.get(4)),
                Integer.parseInt(partsOfLine.get(5)));
        item.setStatus(partsOfLine.get(6));
        return item;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        Item item = (Item) o;
//        return Objects.equals(task, item.task) && Objects.equals(dueDate, item.dueDate);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(task, dueDate);
//    }
}
