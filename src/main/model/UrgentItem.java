package model;

import exceptions.OverDueException;
import exceptions.TooManyThingsToDoException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static ui.Main.setItem;

public class UrgentItem extends Item {
    public UrgentItem() {
        super();
    }

    public String timeLeft() throws OverDueException {
        LocalDate today = LocalDate.now();
        long difference = ChronoUnit.DAYS.between(today,dueDate);
        if (difference < 0) {
            throw new OverDueException();
        }
        return "There are " + difference + " days until this task is due.";
    }

    public static UrgentItem setUrgentItem() {
        UrgentItem item = new UrgentItem();
        setItem(item);
        return item;
    }

    // EFFECTS: returns an urgent item to be printed
    public static String printUrgentItem(String print, Item e) throws OverDueException {
        if (print.equals("")) {
            print = print + e.todoGetItem() + "\n" + ((UrgentItem) e).timeLeft();
        } else {
            print = print + "\n" + e.todoGetItem() + "\n" + ((UrgentItem) e).timeLeft();
        }
        return print;
    }

    public static String printOverdue(String print, Item e) {
        if (print.equals("")) {
            print = print + e.todoGetItem() + "\nThis item is overdue!";
        } else {
            print = print + "\n" + e.todoGetItem() + "\nThis item is overdue!";
        }
        return print;
    }
}
