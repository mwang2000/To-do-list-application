package tests;

import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.RegularItem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TestRegularItem {
    private RegularItem entry;
    private UrgentItem entry2;

    @BeforeEach
    void runBefore() {
        entry = new RegularItem();
        entry.setTask("abc");
        entry.setDue(2020,1,1);
        entry2 = new UrgentItem();
        entry2.setTask("def");
        entry2.setDue(2020,12,31);
    }

    @Test
    void testFirstConstructor() {
        RegularItem item = new RegularItem();
        assertEquals("",item.getTask());
        assertNull(item.getDue());
        assertEquals("not done", item.getStatus());
        assertNull(item.getList());
    }
}
