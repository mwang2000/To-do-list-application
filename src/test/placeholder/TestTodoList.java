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
}