package tests;

import exceptions.OverDueException;
import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestUrgentItem {
    private UrgentItem entry;
    private UrgentItem entry2;

    @BeforeEach
    public void runBefore() {
        entry = new UrgentItem();
        entry.setTask("abc");
        entry.setDue(2019,1,1);
        entry.setKeyword("a");
        entry2 = new UrgentItem();
        entry2.setTask("def");
        entry2.setDue(2020,1,1);
        entry2.setKeyword("b");
    }

    @Test
    public void testTimeLeft() throws OverDueException {
        assertEquals("There are 66 days until this task is due.", entry2.timeLeft());
    }

    @Test
    public void testOverDueNoException() {
        try{
            entry2.timeLeft();
            System.out.println("pass");
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
            System.out.println("pass");
        }
    }

    @Test
    public void testPrintOverdueEmpty() {
        String print = "";
        assertEquals("0. abc due:2019-01-01 not done Keyword: a\nThis item is overdue!",
                UrgentItem.printOverdue(print,entry));
    }

    @Test
    public void testPrintOverdueNotEmpty() {
        String print = "hello";
        assertEquals("hello\n0. abc due:2019-01-01 not done Keyword: a\nThis item is overdue!",
                UrgentItem.printOverdue(print,entry));
    }

    @Test
    public void testPrintUrgentItemEmpty() throws OverDueException {
        String print = "";
        assertEquals("0. def due:2020-01-01 not done Keyword: b\nThere are 66 days until this task is due.",
                UrgentItem.printUrgentItem(print,entry2));
    }

    @Test
    public void testPrintUrgentItemNotEmpty() throws OverDueException {
        String print = "hello";
        assertEquals("hello\n0. def due:2020-01-01 not done Keyword: b\n"
                + "There are 66 days until this task is due.",
                UrgentItem.printUrgentItem(print,entry2));
    }

}
