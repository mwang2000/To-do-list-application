package model;

import exceptions.OverDueException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class UrgentItem extends Item {
    public UrgentItem() {
        super();
    }

    //EFFECTS: returns a string that states the number of days until due date
    public String timeLeft() throws OverDueException {
        long difference = ChronoUnit.DAYS.between(LocalDate.now(),dueDate);
        if (difference < 0) {
            throw new OverDueException();
        }
        return "There are " + difference + " days until this task is due.";
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

    //EFFECTS: returns a string to be printed for an overdue item
    public static String printOverdue(String print, Item e) {
        if (print.equals("")) {
            print = print + e.todoGetItem() + "\nThis item is overdue!";
        } else {
            print = print + "\n" + e.todoGetItem() + "\nThis item is overdue!";
        }
        return print;
    }
}
