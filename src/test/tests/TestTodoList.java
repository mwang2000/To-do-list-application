package tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TestTodoList {
    private TodoList todo;
    private TodoList crossedOff;
    private RegularItem entry;
    private RegularItem entry2;
    private UrgentItem entry3;

    @BeforeEach
    void runBefore() {
        crossedOff = new TodoList();
        todo = new TodoList();
        entry = new RegularItem();
        entry.setTask("abc");
        entry.setDue(2019,12,31);
        entry.setStatus("not done");
        entry.addList(todo);
        entry2 = new RegularItem();
        entry2.setTask("def");
        entry3 = new UrgentItem();
        entry3.setTask("ghi");
        entry3.setDue(2019,12,1);
    }

    @Test
    void testGetList() {
        assertTrue(todo.getList().contains(entry));
    }

    @Test
    void testMoveItemEmptyCrossedOff() {
        todo.addItem(entry);
        todo.addItem(entry2);
        todo.addItem(entry3);
        todo.moveItem(entry,crossedOff);
        assertEquals(2, todo.listSize());
        assertTrue(todo.listContains(entry2));
        assertEquals(1, crossedOff.listSize());
        assertTrue(crossedOff.listContains(entry));
    }

    @Test
    void testMoveItemNotEmptyCrossedOff() {
        todo.addItem(entry);
        todo.addItem(entry2);
        crossedOff.addItem(entry3);
        todo.moveItem(entry2,crossedOff);
        assertEquals(1, todo.listSize());
        assertTrue(todo.listContains(entry));
        assertEquals(2, crossedOff.listSize());
        assertTrue(crossedOff.listContains(entry2));
    }

    @Test
    void testReturnCrossedOffEmpty() {
        assertEquals("\nNothing in the crossed off list.",crossedOff.returnCrossedOffList());
    }

    @Test
    void testReturnCrossedOffMultiple() {
        crossedOff.addItem(entry);
        crossedOff.addItem(entry3);
        entry.setStatus("done");
        entry3.setStatus("done");
        entry.setDue(2019,10,31);
        entry3.setDue(2019,10,10);
        assertEquals("The crossed off list is\nabc due:2019-10-31 done\nghi due:2019-10-10 done",
                crossedOff.returnCrossedOffList());
    }

    @Test
    void testSaveLoadUrgent() throws IOException {
        todo.addItem(entry3);
        todo.save();
        assertTrue(todo.load().listContains(entry3));
    }

    @Test
    void testSaveLoadRegular() throws IOException {
        todo.addItem(entry);
        todo.save();
        assertTrue(todo.load().listContains(entry));
    }

    @Test
    void testRetrieveItemFields() {
        ArrayList<String> partsOfLine = new ArrayList<>();
        partsOfLine.add("abc");
        partsOfLine.add("2019");
        partsOfLine.add("12");
        partsOfLine.add("31");
        partsOfLine.add("not done");
        Item item = new RegularItem();
        item.retrieveItemFields(partsOfLine,item);
        assertEquals( entry.getTask(), item.getTask());
        assertEquals( entry.getDue().getYear(), item.getDue().getYear());
        assertEquals( entry.getDue().getMonthValue(), item.getDue().getMonthValue());
        assertEquals( entry.getDue().getDayOfMonth(), item.getDue().getDayOfMonth());
        assertEquals( entry.getStatus(), item.getStatus());

    }

    @Test
    void testSplitOnUnderscore() {
        ArrayList<String> list = todo.splitOnUnderscore("a_1_abc_2019_12_31_not done");
        assertEquals(7,list.size());
        assertTrue(list.contains("a"));
        assertTrue(list.contains("1"));
        assertTrue(list.contains("abc"));
        assertTrue(list.contains("2019"));
        assertTrue(list.contains("12"));
        assertTrue(list.contains("31"));
        assertTrue(list.contains("not done"));
    }
}