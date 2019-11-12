package model;

import exceptions.OverDueException;
import observer.Subject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TodoList extends Subject implements Saveable,Loadable {
    private ArrayList<Item> list;
    public List<User> users;

    public TodoList() {
        list = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void updateTodo(Map<String,Item> map) throws IOException {
        list = new ArrayList<>(map.values());
        notifyObservers();
    }

    public void addItem(Item i) {
        list.add(i);
    }

    public boolean listContains(Item i) {
        return list.contains(i);
    }

    public int listSize() {
        return list.size();
    }

    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
            addObserver(user);
            user.addTodo(this);
        }
    }

//    public void addExamPrep(Item i) {
//        if (!list.contains(i)) {
//            if (i instanceof UrgentItem) {
//                Item e = new UrgentItem();
//                e.setTask(i.getTask());
//                e.setDue(i.getDue().getYear(), i.getDue().getMonthValue(), i.getDue().getDayOfMonth());
//                list.add(e);
//            } else {
//                Item e = new RegularItem();
//                e.setTask(i.getTask());
//                e.setDue(i.getDue().getYear(), i.getDue().getMonthValue(), i.getDue().getDayOfMonth());
//                list.add(e);
//            }
//            i.addList(this);
//        }
//    }


    // EFFECTS: prints the todo list and then the crossed out list
    public String returnTodoList() {
        if (list.size() == 0) {
            return "Nothing in the to do list.";
        } else {
            String print = "";
            for (Item e : list) {
                if (e instanceof UrgentItem) {
                    try {
                        print = ((UrgentItem) e).printUrgentItem(print);
                    } catch (OverDueException od) {
                        print = ((UrgentItem) e).printOverdue(print);
                    }
                } else {
                    print = ((RegularItem) e).printRegularItem(print);
                }
            }
            return print;
        }
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
            return "\nThe crossed off list is" + print;
        }
    }

//    public String returnExamPrep() {
//        if (list.size() == 0) {
//            return "\nNothing in the exam prep list.";
//        } else {
//            String print = "";
//            for (Item e : list) {
//                print = print + "\n" + e.task + " " + e.dueDate;
//            }
//            return "\nThe exam prep list is:" + print;
//        }
//    }

    //MODIFIES: todoMap,todo,crossedOff
    //EFFECTS: moves an item from todoMap and examPrep ,if applicable, to crossedOff and changes the status to "done"
    public void moveItem(String removing, Map<String, Item> map, TodoList todo) throws IOException {
        Item removedItem = map.get(removing);
//        if (list.contains(removedItem)) {
//            removeExamPrep(removedItem);
//        }
        removedItem.setStatus("done");
        removedItem.setNumber(0);
        list.add(removedItem);
        map.remove(removing);
        todo.updateTodo(map);
    }

//    public void removeExamPrep(Item i) {
//        if (list.contains(i)) {
//            list.remove(i);
//            i.removeList(this);
//        }
//    }

    public void save(Map<String,Item> map) throws FileNotFoundException {
        File file = new File(String.valueOf(Paths.get("./data/file")));
        PrintWriter printWriter = new PrintWriter(file);
        for (Map.Entry<String,Item> i : map.entrySet()) {
            printWriter.println(i.getKey() + "_" + Item.saveTodo(i.getValue()));
        }
        printWriter.close();
//        if (!list.isEmpty()) {
//            printWriter.println(saveExamPrep());
//            printWriter.close();
//        }
    }

//    public String saveExamPrep() {
//        String print = "";
//        for (Item i : list) {
//            if (print == "") {
//                print = print + i.getTask() + "_" + i.getDue().getYear() + "_" + i.getDue().getMonthValue() + "_"
//                        + i.getDue().getDayOfMonth();
//            } else {
//                print = print + "\n" + i.getTask() + "_" + i.getDue().getYear() + "_" + i.getDue().getMonthValue()
//                + "_" + i.getDue().getDayOfMonth();
//            }
//        }
//        return print;
//    }


    //EFFECTS: loads todo list from file
//    public void loadToList() throws IOException {
//        Map<String,Item> map = new HashMap<>();
//        list = new ArrayList<>(load(map).values());
//    }

    public Map<String,Item> load(Map<String,Item> map) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./data/file"));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnUnderscore(line);
            if (partsOfLine.size() == 8) {
                Item item = new UrgentItem();
                map.put(partsOfLine.get(0),item.retrieveItemFields(partsOfLine,item));
            } else if (partsOfLine.size() == 7) {
                Item item = new RegularItem();
                map.put(partsOfLine.get(0),item.retrieveItemFields(partsOfLine,item));
            }
//            else {
//                Item item = new UrgentItem();
//                item.setTask(partsOfLine.get(0));
//                item.setDue(Integer.parseInt(partsOfLine.get(1)),Integer.parseInt(partsOfLine.get(2)),
//                        Integer.parseInt(partsOfLine.get(3)));
//                list.add(item);
//            }
        }
        return map;
    }

//    public Item retrieveItemFields(ArrayList<String> partsOfLine, Item item) {
//        item.setKeyword(partsOfLine.get(0));
//        item.setNumber(Integer.parseInt(partsOfLine.get(1)));
//        item.setTask(partsOfLine.get(2));
//        item.setDue(Integer.parseInt(partsOfLine.get(3)), Integer.parseInt(partsOfLine.get(4)),
//                Integer.parseInt(partsOfLine.get(5)));
//        item.setStatus(partsOfLine.get(6));
//        return item;
//    }

    public ArrayList<String> splitOnUnderscore(String line) {
        String[] splits = line.split("_");
        return new ArrayList<>(Arrays.asList(splits));
    }
}

