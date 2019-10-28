package model;

import exceptions.OverDueException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import static ui.Main.todoMap;

public class TodoList implements Saveable,Loadable {
    public static ArrayList<Item> todo;
    public static ArrayList<Item> crossedOff;
    public static ArrayList<Item> examPrep;
    public static int MAX_TODO_SIZE = 3;

    public TodoList() {
        todo = new ArrayList<>();
        crossedOff = new ArrayList<>();
        examPrep = new ArrayList<>();
    }

    public static int getTodoSize() {
        return todo.size();
    }

    public static int getExamPrepSize() {
        return examPrep.size();
    }

    public static void updateTodo(Map<String,Item> map) {
        todo = new ArrayList<>(map.values());
    }

    public void addExamPrep(Item i) {
        if (!examPrep.contains(i)) {
            if (i instanceof UrgentItem) {
                Item e = new UrgentItem();
                e.setTask(i.getTask());
                e.setDue(i.getDue().getYear(), i.getDue().getMonthValue(), i.getDue().getDayOfMonth());
                examPrep.add(e);
            } else {
                Item e = new RegularItem();
                e.setTask(i.getTask());
                e.setDue(i.getDue().getYear(), i.getDue().getMonthValue(), i.getDue().getDayOfMonth());
                examPrep.add(e);
            }
            i.addList(this);
        }
    }


    // EFFECTS: prints the todo list and then the crossed out list
    public static String returnTodoList() {
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
    public static String returnCrossedOffList() {
        if (crossedOff.size() == 0) {
            return "\nNothing in the crossed off list.";
        } else {
            String print = "";
            for (Item e : crossedOff) {
                print = print + e.crossedOffGetItem();
            }
            return "\nThe crossed off list is\n" + print;
        }
    }

    public static String returnExamPrep() {
        if (examPrep.size() == 0) {
            return "\nNothing in the exam prep list.";
        } else {
            String print = "";
            for (Item e : examPrep) {
                print = print + e.task + " " + e.dueDate + "\n";
            }
            return "The exam prep list is:\n" + print;
        }
    }


    //MODIFIES: todo,crossedOff
    //EFFECTS: moves an item from todo to crossedOff and changes the status to "done"
    public void moveItem(String removing) {
        Item removedItem = todoMap.get(removing);
        if (examPrep.contains(removedItem)) {
            removeExamPrep(removedItem);
        }
        crossedOff.add(removedItem);
        removedItem.setStatus("done");
        removedItem.setNumber(0);
        todoMap.remove(removing);
        updateTodo(todoMap);
    }

    public void removeExamPrep(Item i) {
        if (examPrep.contains(i)) {
            examPrep.remove(i);
            i.removeList(this);
        }
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

    public void save() throws FileNotFoundException {
        File file = new File(String.valueOf(Paths.get("./data/file")));
        PrintWriter printWriter = new PrintWriter(file);
        for (Map.Entry<String,Item> i : todoMap.entrySet()) {
            printWriter.println(i.getKey() + "_" + saveTodo(i.getValue()));
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


    //EFFECTS: loads todo list from file
    public static void loadTodo(TodoList tl) throws IOException {
        Map<String,Item> map = new HashMap<>();
        todoMap = tl.load(map);
        todo = new ArrayList<>(todoMap.values());
    }

    public Map<String,Item> load(Map<String,Item> map) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./data/file"));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnBar(line);
            if (partsOfLine.size() == 8) {
                Item item = new UrgentItem();
                map.put(partsOfLine.get(0),retrieveItemFields(partsOfLine,item));
            } else {
                Item item = new RegularItem();
                map.put(partsOfLine.get(0),retrieveItemFields(partsOfLine,item));
            }
        }
        return map;
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

    public static ArrayList<String> splitOnBar(String line) {
        String[] splits = line.split("_");
        return new ArrayList<>(Arrays.asList(splits));
    }

}

