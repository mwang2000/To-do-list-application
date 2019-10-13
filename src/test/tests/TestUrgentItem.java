package tests;

import exceptions.OverDueException;
import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestUrgentItem {
    private UrgentItem entry;
    private UrgentItem entry2;

    @BeforeEach
    public void runBefore() {
        entry = new UrgentItem();
        entry.setTask("abc");
        entry.setDueDate(2019,10,10);
        entry2 = new UrgentItem();
        entry2.setTask("def");
        entry2.setDueDate(2019,10,20);
    }

    @Test
    public void testTimeLeft() throws OverDueException {
        assertEquals("There are 7 days until this task is due.", entry2.timeLeft());
    }

    @Test
    public void testOverDueNoException() {
        try{
            entry2.timeLeft();
        } catch (OverDueException od) {
            fail();
        }
    }

    @Test
    public void testOverDueException() {
        try{
            entry.timeLeft();
            fail();
        } catch (OverDueException od) {
        }
    }

    @Test
    public void testPrintOverdueEmpty() {
        String print = "";
        assertEquals("0. def due:null not done (to do)\nThis item is overdue!",
                entry2.printOverdue(print,entry2));
    }

    @Test
    public void testPrintOverdueNotEmpty() {
        String print = "hello";
        assertEquals("hello\n0. def due:null not done (to do)\nThis item is overdue!",
                entry2.printOverdue(print,entry2));
    }

}
