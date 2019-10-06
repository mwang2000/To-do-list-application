package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UrgentItem extends Item {
    public UrgentItem(String item) {
        super(item);
    }

    public void timeLeft() {
        LocalDate today = LocalDate.now();
        long difference = ChronoUnit.DAYS.between(today,dueDate);
        System.out.println("There are " + difference + " days until this task is due.");
    }
}
