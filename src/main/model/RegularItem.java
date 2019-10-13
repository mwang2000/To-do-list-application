package model;

import static ui.Main.setItem;

// represents a regular item in a list
public class RegularItem extends Item {
    public RegularItem() {
        super();
    }


    public static RegularItem setRegularItem() {
        RegularItem item = new RegularItem();
        setItem(item);
        return item;
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
