package model;

import exceptions.TooManyThingsToDoException;

import static model.TodoList.returnTodoList;
import static ui.Main.setItem;
import static ui.Main.todo;

// represents a regular item in a list
public class RegularItem extends Item {
    public RegularItem() {
        super();
    }


    // EFFECTS: returns a regular item to be printed
    public static String printRegularItem(String print, Item e) {
        if (print.equals("")) {
            print = print + (e.todoGetItem());
        } else {
            print = print + "\n" + (e.todoGetItem());
        }
        return print;
    }
}
