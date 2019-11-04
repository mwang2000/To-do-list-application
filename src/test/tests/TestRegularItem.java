package tests;

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
        entry.setDue(2020,1,1);
        entry.setNumber(1);
        entry.setKeyword("a");
        entry2 = new UrgentItem();
        entry2.setTask("def");
        entry2.setDue(2020,12,31);
        entry2.setNumber(2);
        entry2.setKeyword("b");
    }

    @Test
    public void testPrintRegularItemEmpty() {
        String print = "";
        assertEquals("1. abc due:2020-01-01 not done Keyword: a",entry.printRegularItem(print));
    }

    @Test
    public void testPrintRegularItem() {
        String print = "hello";
        assertEquals("hello\n1. abc due:2020-01-01 not done Keyword: a",entry.printRegularItem(print));
    }
}
