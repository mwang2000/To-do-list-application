package model;

import exceptions.OverDueException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UrgentItem extends Item {
    public UrgentItem() {
        super();
    }

    public UrgentItem(String keyword, String task, int y, int m, int d) {
        super(keyword, task, y, m, d);
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
//    public String printUrgentItem(String print) throws OverDueException {
//        if (print.equals("")) {
//            print = print + todoGetItem() + timeLeft();
//        } else {
//            print = print + "\n" + todoGetItem() + timeLeft();
//        }
//        return print;
//    }

    public String printUrgentItem(int number) throws OverDueException {
        return printItemHelper(number,"(" + timeLeft() + ")");
    }

    //EFFECTS: returns a string to be printed for an overdue item
//    public String printOverdue(String print) {
//        if (print.equals("")) {
//            print = print + todoGetItem() + "\nThis item is overdue!";
//        } else {
//            print = print + "\n" + todoGetItem() + "\nThis item is overdue!";
//        }
//        return print;
//    }

    public String printOverdue(int number) {
        return printItemHelper(number,"\nThis item is overdue!");
    }
}
