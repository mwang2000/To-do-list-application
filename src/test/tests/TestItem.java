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

public class TestItem {
    private TodoList testTodo;
    private RegularItem entry;
    private UrgentItem entry2;

    @BeforeEach
    public void runBefore() {
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
    public void testConstructor() {
        Item i = new RegularItem();
        assertEquals("",i.getTask());
        assertEquals("not done",i.getStatus());
//        assertEquals(null,i.getList());
    }

    @Test
    public void testConstructor2() {
        Item i = new UrgentItem("abc",2019,12,25);
        assertEquals("abc",i.getTask());
        assertEquals(2019,i.getDue().getYear());
        assertEquals(12,i.getDue().getMonthValue());
        assertEquals(25,i.getDue().getDayOfMonth());
        assertEquals("not done",i.getStatus());
    }

    @Test
    public void testSetItem() {
        testTodo.addItem(entry);
        testTodo.addItem(entry2);
        assertEquals("abc", entry.getTask());
        assertEquals("def", entry2.getTask());
    }

    @Test
    public void testSetStatus() {
        testTodo.addItem(entry);
        testTodo.addItem(entry2);
        entry.setStatus("done");
        entry2.setStatus("not done");
        assertEquals("done", entry.getStatus());
        assertEquals("not done", entry2.getStatus());
    }

    @Test
    public void testAddListNotNull() {
        entry.addList(new TodoList());
        assertEquals(testTodo,entry.getList());
    }

    @Test
    public void testSetDueDate() {
        testTodo.addItem(entry2);
        entry.setDue(2019,10,10);
        assertEquals(LocalDate.of(2019,10,10),entry.getDue());
    }

    @Test
    public void testGetItem() {
        testTodo.addItem(entry);
        testTodo.addItem(entry2);
        assertEquals("abc", entry.getTask());
        assertEquals("def", entry2.getTask());
    }

    @Test
    public void testGetStatus() {
        testTodo.addItem(entry);
        assertEquals("not done", entry.getStatus());
    }

    @Test
    public void testGetDueDate() {
        testTodo.addItem(entry);
        assertEquals(LocalDate.of(2019,12,31), entry.getDue());
    }

    @Test
    public void testTodoGetItem() {
        assertEquals("2.def due:2020-01-01 not done (There are 38 days until this task is due.)",
                entry2.toString());
    }

    @Test
    public void testGetList() {
        assertEquals(testTodo,entry2.getList());
    }

    @Test
    public void testCrossedOffGetItem() {
        assertEquals("abc due:2019-12-31 not done",entry.crossedOffGetItem());
    }

    @Test
    public void testToStringRegular() {
        assertEquals("1.abc due:2019-12-31 not done ",entry.toString());
    }

    @Test
    public void testListContains() {

    }

    @Test
    public void testToStringUrgent() {
        assertEquals("2.def due:2020-01-01 not done (There are 38 days until this task is due.)",
                entry2.toString());
    }

    @Test
    public void testToStringOverdue() {
        UrgentItem item = new UrgentItem("xyz",2019,10,31);
        item.addList(testTodo);
        assertEquals("3.xyz due:2019-10-31 not done  (This item is overdue!)",item.toString());
    }

//    @Test
//    public void testAddList() {
//        entry.addList(testTodo);
//        assertTrue(entry.getList() == testTodo);
//    }

//    @Test
//    public void testAddListAlready() {
//        entry.addList(testTodo);
//        entry.addList(testTodo);
//        assertTrue(entry.getList() == testTodo);
//    }
//
//    @Test
//    public void testRemoveList() {
//        entry.addList(testTodo);
//        entry.removeList(testTodo);
//        assertEquals(null,entry.getList());
//    }
//
//    @Test
//    public void testRemoveListNo() {
//        entry.addList(testTodo);
//        TodoList test = new TodoList();
//        entry.removeList(test);
//        assertTrue(entry.getList() == testTodo);
//    }

//    @Test
//    public void testReturnNumberOfItemsLeft() {
//        testTodo.addItem(entry2);
//        entry.addList(testTodo);
//        assertEquals("by crossing off this item, you have 0 items in the exam prep list",
//                entry.returnNumberOfItemsLeft());
//    }

    @Test
    public void testSaveTodoRegular() {
        assertEquals("abc_2019_12_31_not done",Item.saveTodo(entry));
    }

    @Test
    public void testSaveTodoUrgent() {
        assertEquals("def_2020_1_1_not done_(There are 38 days until this task is due.)",
                Item.saveTodo(entry2));
    }

    @Test
    public void testSaveTodoUrgentOverdue() {
        entry2.setDue(2019,10,10);
        assertEquals("def_2019_10_10_not done_This item is overdue!", Item.saveTodo(entry2));
    }

    @Test
    public void testPrintItemHelper() {
        assertEquals("1.abc due:2019-12-31 not done ",entry.printItemHelper(""));
    }

    @Test
    public void testRetrieveItemFields() {
        ArrayList<String> list = new ArrayList<>();
        list.add("abc");
        list.add("2019");
        list.add("12");
        list.add("31");
        list.add("not done");
        assertEquals(entry,entry.retrieveItemFields(list,new RegularItem()));
    }

    @Test
    public void testEqualsTrue() {
        Item entry3 = new RegularItem();
        entry3.setTask("abc");
        entry3.setDue(2019,12,31);;
        assertEquals(entry, entry3);
    }

    @Test
    public void testEqualsFalse() {
        assertNotEquals(entry, entry2);
    }

    @Test
    public void testHashCodeMatch() {
        Item entry3 = new RegularItem();
        entry3.setTask("abc");
        entry3.setDue(2019,12,31);
        assertEquals(entry.hashCode(),entry3.hashCode());
    }

    @Test
    public void testHashCodeDifferent() {
        assertFalse(entry.hashCode() == entry2.hashCode());
    }
}
