package tests;

import model.Item;
import model.TodoList;
import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.RegularItem;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestItem {
    private TodoList testTodo;
    private RegularItem entry;
    private UrgentItem entry2;

    @BeforeEach
    void runBefore() {
        testTodo = new TodoList();
        entry = new RegularItem();
        entry.setTask("abc");
        entry.setDue(2019,12,31);
        entry.addList(testTodo);
        entry2 = new UrgentItem();
        entry2.setDue(2020,1,1);
        entry2.setTask("def");
        entry2.addList(testTodo);
    }

    @Test
    void testConstructor() {
        Item i = new RegularItem();
        assertEquals("",i.getTask());
        assertEquals("not done",i.getStatus());
    }

    @Test
    void testConstructor2() {
        Item i = new UrgentItem("abc",2019,12,25);
        assertEquals("abc",i.getTask());
        assertEquals(2019,i.getDue().getYear());
        assertEquals(12,i.getDue().getMonthValue());
        assertEquals(25,i.getDue().getDayOfMonth());
        assertEquals("not done",i.getStatus());
    }

    @Test
    void testSetItem() {
        testTodo.addItem(entry);
        testTodo.addItem(entry2);
        assertEquals("abc", entry.getTask());
        assertEquals("def", entry2.getTask());
    }

    @Test
    void testSetStatus() {
        testTodo.addItem(entry);
        testTodo.addItem(entry2);
        entry.setStatus("done");
        entry2.setStatus("not done");
        assertEquals("done", entry.getStatus());
        assertEquals("not done", entry2.getStatus());
    }

    @Test
    void testAddListNotNull() {
        entry.addList(new TodoList());
        assertEquals(testTodo,entry.getList());
    }

    @Test
    void testSetDueDate() {
        testTodo.addItem(entry2);
        entry.setDue(2019,10,10);
        assertEquals(LocalDate.of(2019,10,10),entry.getDue());
    }

    @Test
    void testGetItem() {
        testTodo.addItem(entry);
        testTodo.addItem(entry2);
        assertEquals("abc", entry.getTask());
        assertEquals("def", entry2.getTask());
    }

    @Test
    void testGetStatus() {
        testTodo.addItem(entry);
        assertEquals("not done", entry.getStatus());
    }

    @Test
    void testGetDueDate() {
        testTodo.addItem(entry);
        assertEquals(LocalDate.of(2019,12,31), entry.getDue());
    }

    @Test
    void testTodoGetItem() {
        assertEquals("2.def due:2020-01-01 not done (There are 37 days until this task is due.)",
                entry2.toString());
    }

    @Test
    void testGetList() {
        assertEquals(testTodo,entry2.getList());
    }

    @Test
    void testCrossedOffGetItem() {
        assertEquals("abc due:2019-12-31 not done",entry.crossedOffGetItem());
    }

    @Test
    void testToStringRegular() {
        assertEquals("1.abc due:2019-12-31 not done ",entry.toString());
    }

    @Test
    void testListContains() {

    }

    @Test
    void testToStringUrgent() {
        assertEquals("2.def due:2020-01-01 not done (There are 37 days until this task is due.)",
                entry2.toString());
    }

    @Test
    void testToStringOverdue() {
        UrgentItem item = new UrgentItem("xyz",2019,10,31);
        item.addList(testTodo);
        assertEquals("3.xyz due:2019-10-31 not done  (This item is overdue!)",item.toString());
    }

    @Test
    void testSaveTodoRegular() {
        assertEquals("abc_2019_12_31_not done",Item.saveTodo(entry));
    }

    @Test
    void testSaveTodoUrgent() {
        assertEquals("def_2020_1_1_not done_(There are 37 days until this task is due.)",
                Item.saveTodo(entry2));
    }

    @Test
    void testSaveTodoUrgentOverdue() {
        entry2.setDue(2019,10,10);
        assertEquals("def_2019_10_10_not done_This item is overdue!", Item.saveTodo(entry2));
    }

    @Test
    void testPrintItemHelper() {
        assertEquals("1.abc due:2019-12-31 not done ",entry.printItemHelper(""));
    }

    @Test
    void testRetrieveItemFields() {
        ArrayList<String> list = new ArrayList<>();
        list.add("abc");
        list.add("2019");
        list.add("12");
        list.add("31");
        list.add("not done");
        assertEquals(entry,entry.retrieveItemFields(list,new RegularItem()));
    }

    @Test
    void testEqualsTrue() {
        Item entry3 = new RegularItem();
        entry3.setTask("abc");
        entry3.setDue(2019,12,31);
        assertEquals(entry, entry3);
    }

    @Test
    void testEqualsFalse() {
        assertNotEquals(entry, entry2);
    }

    @Test
    void testHashCodeMatch() {
        Item entry3 = new RegularItem();
        entry3.setTask("abc");
        entry3.setDue(2019,12,31);
        assertEquals(entry.hashCode(),entry3.hashCode());
    }

    @Test
    void testHashCodeDifferent() {
        assertNotEquals(entry.hashCode(), entry2.hashCode());
    }
}
