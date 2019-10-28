package tests;

import model.Item;
import model.TodoList;
import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.RegularItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestTodoList {
    private ArrayList<Item> testTodo;
    private ArrayList<Item> testCrossedOff;
    private RegularItem entry;
    private RegularItem entry2;
    private UrgentItem entry3;
    private Map<String,Item> todoMap;
    private TodoList tl;

    @BeforeEach
    public void runBefore() {
        entry = new RegularItem();
        entry.setTask("abc");
        entry2 = new RegularItem();
        entry2.setTask("def");
        entry3 = new UrgentItem();
        entry3.setTask("ghi");
        testTodo = new ArrayList<>(todoMap.values());
        testCrossedOff = new ArrayList<>();
        todoMap = new HashMap<>();
        tl = new TodoList();
    }

    @Test
    public void testMoveItemEmptyCrossedOff() {
        todoMap.put("a",entry);
        todoMap.put("b",entry2);
        tl.moveItem("a");
        entry.setNumber(0);
        assertEquals(1,testTodo.size());
        assertTrue(testTodo.contains(entry2));
        assertEquals(1,testCrossedOff.size());
        assertTrue(testCrossedOff.contains(entry));
    }

    @Test
    public void testMoveItemNotEmptyCrossedOff() {
        todoMap.put("a",entry);
        todoMap.put("b",entry2);
        testCrossedOff.add(entry3);
        tl.moveItem("b");
        entry2.setNumber(0);
        assertEquals(1,testTodo.size());
        assertTrue(testTodo.contains(entry));
        assertEquals(2,testCrossedOff.size());
        assertTrue(testCrossedOff.contains(entry2));
    }

    @Test
    public void testMoveItemUrgentItem() {
        todoMap.put("c",entry3);
        testCrossedOff.add(entry);
        tl.moveItem("c");
        entry3.setNumber(0);
        assertEquals(0,testTodo.size());
        assertEquals(2,testCrossedOff.size());
        assertTrue(testCrossedOff.contains(entry3));
    }

    @Test
    public void testReturnTodoEmpty() {
        assertEquals("Nothing in the to do list.",
                TodoList.returnTodoList());
    }

//    @Test
//    public void testReturnTodoUrgent() {
//        testTodo.add(entry3);
//        entry3.setDueDate(2019,10,10);
//        assertEquals("0. ghi due:2019-10-10 not done (to do)\nThere are 4 days until this task is due.",
//                TodoList.returnTodoList(testTodo));
//    }

    @Test
    public void testReturnTodoRegular() {
        todoMap.put("a",entry);
        entry.setDue(2019,10,20);
        assertEquals("0. abc due:2019-10-20 not done (to do)",TodoList.returnTodoList());
    }

    @Test
    public void testReturnTodoUrgent() {
        todoMap.put("c",entry3);
        entry3.setDue(2019,10,20);
        assertEquals("0. ghi due:2019-10-20 not done (to do)\nThere are 6 days until this task is due.",
                TodoList.returnTodoList());
    }

    @Test
    public void testReturnTodoException() {
        todoMap.put("c",entry3);
        entry3.setDue(2019,10,10);
        assertEquals("0. ghi due:2019-10-10 not done (to do)\nThis item is overdue!",
                TodoList.returnTodoList());
    }

    @Test
    public void testReturnCrossedOffEmpty() {
        assertEquals("\nNothing in the crossed off list.",TodoList.returnCrossedOffList());
    }

    @Test
    public void testReturnCrossedOffMultiple() {
        testCrossedOff.add(entry);
        testCrossedOff.add(entry3);
        entry.setStatus("done");
        entry3.setStatus("done");
        entry.setDue(2019,10,31);
        entry3.setDue(2019,10,10);
        assertEquals("\n0. abc due:2019-10-31 done (crossed off)\n0. ghi due:2019-10-10 done (crossed off)",
                TodoList.returnCrossedOffList());
    }

    @Test
    void testSaveLoad() throws IOException {
        TodoList tl = new TodoList();
        entry.setDue(2020, 5,25);
        testTodo.add(entry);
        entry3.setDue(2019,10,31);
        testTodo.add(entry3);
        tl.save();
        assertEquals(testTodo,tl.load(todoMap));
    }
}