package placeholder;

import model.Item;
import model.TodoList;
import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.RegularItem;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTodoList {
    private ArrayList<Item> testTodo;
    private ArrayList<Item> testCrossedOff;
    private RegularItem entry;
    private RegularItem entry2;
    private UrgentItem entry3;

    @BeforeEach
    public void runBefore() {
        entry = new RegularItem("abc");
        entry2 = new RegularItem("def");
        entry3 = new UrgentItem("ghi");
        testTodo = new ArrayList<>();
        testCrossedOff = new ArrayList<>();
    }

    @Test
    public void testMoveItemEmptyCrossedOff() {
        testTodo.add(entry);
        testTodo.add(entry2);
        TodoList.moveItem(1,testTodo,testCrossedOff);
        assertEquals(1,testTodo.size());
        assertTrue(testTodo.contains(entry2));
        assertEquals(1,testCrossedOff.size());
        assertTrue(testCrossedOff.contains(entry));
    }

    @Test
    public void testMoveItemNotEmptyCrossedOff() {
        testTodo.add(entry);
        testTodo.add(entry2);
        testCrossedOff.add(entry3);
        TodoList.moveItem(2,testTodo,testCrossedOff);
        assertEquals(1,testTodo.size());
        assertTrue(testTodo.contains(entry));
        assertEquals(2,testCrossedOff.size());
        assertTrue(testCrossedOff.contains(entry2));
    }

    @Test
    public void testMoveItemUrgentItem() {
        testTodo.add(entry3);
        testCrossedOff.add(entry);
        TodoList.moveItem(1,testTodo,testCrossedOff);
        assertEquals(0,testTodo.size());
        assertEquals(2,testCrossedOff.size());
        assertTrue(testCrossedOff.contains(entry3));
    }

    @Test
    public void testReturnTodoEmpty() {
        assertEquals("Nothing in the to do list.",TodoList.returnTodoList(testTodo));
    }

    @Test
    public void testReturnTodoUrgent() {
        testTodo.add(entry3);
        entry3.setDueDate(2019,10,10);
        assertEquals("0. ghi due:2019-10-10 not done (to do)\nThere are 4 days until this task is due.",
                TodoList.returnTodoList(testTodo));
    }

    @Test
    public void testReturnTodoRegular() {
        testTodo.add(entry);
        entry.setDueDate(2019,10,10);
        assertEquals("0. abc due:2019-10-10 not done (to do)",TodoList.returnTodoList(testTodo));
    }

    @Test
    public void testReturnTodoMultiple() {
        testTodo.add(entry);
        testTodo.add(entry3);
        entry.setDueDate(2019,10,31);
        entry3.setDueDate(2019,10,10);
        assertEquals("0. abc due:2019-10-31 not done (to do)\n0. ghi due:2019-10-10 not done (to do)"
                + "\nThere are 4 days until this task is due.",TodoList.returnTodoList(testTodo));
    }

    @Test
    public void testReturnCrossedOffEmpty() {
        assertEquals("Nothing in the crossed off list.",TodoList.returnCrossedOffList(testCrossedOff));
    }

    @Test
    public void testReturnCrossedOffMultiple() {
        testCrossedOff.add(entry);
        testCrossedOff.add(entry3);
        entry.setStatus("done");
        entry3.setStatus("done");
        entry.setDueDate(2019,10,31);
        entry3.setDueDate(2019,10,10);
        assertEquals("\n0. abc due:2019-10-31 done (crossed off)\n0. ghi due:2019-10-10 done (crossed off)",
                TodoList.returnCrossedOffList(testCrossedOff));
    }

    @Test
    public void testPrintRegularItemEmpty() {
        String print = "";
        assertEquals("0. abc due:null not done (to do)",TodoList.printRegularItem(print,entry));
    }

    @Test
    public void testPrintRegularItem() {
        String print = "hello";
        assertEquals("hello\n0. abc due:null not done (to do)",TodoList.printRegularItem(print,entry));
    }

    @Test
    public void testPrintUrgentItemEmpty() {
        String print = "";
        entry3.setDueDate(2019,10,10);
        assertEquals("0. ghi due:2019-10-10 not done (to do)\nThere are 4 days until this task is due.",
                TodoList.printUrgentItem(print, entry3));
    }

    @Test
    public void testPrintUrgentItem() {
        String print = "world";
        entry3.setDueDate(2019,10,10);
        assertEquals("world\n0. ghi due:2019-10-10 not done (to do)\nThere are 4 days until this task is due.",
                TodoList.printUrgentItem(print,entry3));
    }
}