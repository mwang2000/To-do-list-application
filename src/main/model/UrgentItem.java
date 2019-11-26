package model;

import exceptions.OverDueException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UrgentItem extends Item {
    public UrgentItem() {
        super();
    }

    public UrgentItem(String task, int y, int m, int d) {
        super(task, y, m, d);
    }

    //EFFECTS: returns a string that states the number of days until due date
    public String timeLeft() throws OverDueException {
        long difference = ChronoUnit.DAYS.between(LocalDate.now(),dueDate);
        if (difference < 0) {
            throw new OverDueException();
        }
        return "(There are " + difference + " days until this task is due.)";
    }
}
