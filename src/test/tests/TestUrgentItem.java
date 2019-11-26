package tests;

import exceptions.OverDueException;
import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TestUrgentItem {
    private UrgentItem entry;
    private UrgentItem entry2;

    @BeforeEach
    void runBefore() {
        entry = new UrgentItem();
        entry.setTask("abc");
        entry.setDue(2019,1,1);
        entry2 = new UrgentItem();
        entry2.setTask("def");
        entry2.setDue(2020,1,1);
    }

    @Test
    void testTimeLeft() throws OverDueException {
        assertEquals("(There are 37 days until this task is due.)", entry2.timeLeft());
    }

    @Test
    void testOverDueNoException() {
        try{
            entry2.timeLeft();
            System.out.println("pass");
        } catch (OverDueException od) {
            fail();
        }
    }

    @Test
    void testOverDueException() {
        try{
            entry.timeLeft();
            fail();
        } catch (OverDueException od) {
            System.out.println("pass");
        }
    }
}
