package tests;

import exceptions.OverDueException;
import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.RegularItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRegularItem {
    private RegularItem entry;
    private UrgentItem entry2;

    @BeforeEach
    public void runBefore() {
        entry = new RegularItem();
        entry.setTask("abc");
        entry2 = new UrgentItem();
        entry2.setTask("def");
    }

    @Test
    public void testPrintRegularItemEmpty() {
        String print = "";
        assertEquals("0. abc due:null not done (to do)",RegularItem.printRegularItem(print,entry));
    }

    @Test
    public void testPrintRegularItem() {
        String print = "hello";
        assertEquals("hello\n0. abc due:null not done (to do)",RegularItem.printRegularItem(print,entry));
    }
}
