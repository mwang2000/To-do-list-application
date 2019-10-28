package tests;

import model.Item;
import model.TodoList;
import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.RegularItem;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestItem {
    private ArrayList<Item> testTodo;
    private RegularItem entry;
    private UrgentItem entry2;
    private TodoList testTodoList;

    @BeforeEach
    public void runBefore() {
        entry = new RegularItem();
        entry.setTask("abc");entry.setNumber(1);
        entry.setKeyword("a");
        entry.setDue(2019,12,31);
        entry2 = new UrgentItem();
        entry2.setNumber(2);
        entry2.setKeyword("d");
        entry2.setDue(2020,1,1);
        entry2.setTask("def");
        testTodo = new ArrayList<>();
        testTodoList = new TodoList();
    }

    @Test
    public void testSetNumber() {
        testTodo.add(entry);
        entry.setNumber(3);
        testTodo.add(entry2);
        entry2.setNumber(8);
        assertEquals(3, entry.getNumber());
        assertEquals(8, entry2.getNumber());
    }

    @Test
    public void testSetItem() {
        testTodo.add(entry);
        testTodo.add(entry2);
        assertEquals("abc", entry.getTask());
        assertEquals("def", entry2.getTask());
    }

    @Test
    public void testSetStatus() {
        testTodo.add(entry);
        testTodo.add(entry2);
        entry.setStatus("done");
        entry2.setStatus("not done");
        assertEquals("done", entry.getStatus());
        assertEquals("not done", entry2.getStatus());
    }

    @Test
    public void testSetDueDate() {
        testTodo.add(entry2);
        entry.setDue(2019,10,10);
        assertEquals(LocalDate.of(2019,10,10),entry.getDue());
    }

    @Test
    public void testGetNumber() {
        testTodo.add(entry);
        testTodo.add(entry2);
        assertEquals(1, entry.getNumber());
        assertEquals(2, entry2.getNumber());
    }

    @Test
    public void testGetItem() {
        testTodo.add(entry);
        testTodo.add(entry2);
        assertEquals("abc", entry.getTask());
        assertEquals("def", entry2.getTask());
    }

    @Test
    public void testGetStatus() {
        testTodo.add(entry);
        assertEquals("not done", entry.getStatus());
    }

    @Test
    public void testGetDueDate() {
        testTodo.add(entry);
        assertEquals(LocalDate.of(2019,12,31), entry.getDue());
    }

    @Test
    public void testTodoGetItem() {
        assertEquals("2. def due:2020-01-01 not done Keyword: d", entry2.todoGetItem());
    }

    @Test
    public void testCrossedOffGetItem() {
        assertEquals("1. a abc due:2019-12-31 not done",entry.crossedOffGetItem());
    }

    @Test
    public void testSetKeyword() {
        entry.setKeyword("hello");
        assertEquals("hello",entry.getKeyword());
    }

    @Test
    public void testAddList() {
        entry.addList(testTodo);
        assertTrue(entry.getLists().contains(testTodo));
        assertEquals(1, entry.getLists().size());
    }

    @Test
    public void testAddListNoNew() {
        entry.addList(testTodo);
        entry.addList(testTodo);
        assertTrue(entry.getLists().contains(testTodoList));
        assertEquals(1, entry.getLists().size());
    }

    @Test
    public void testRemoveList() {
        entry.addList(testTodo);
        entry.removeList(testTodoList);
        assertEquals(0,entry.getLists().size());
    }

    @Test
    public void testRemoveListNo() {
        entry.removeList(testTodoList);
        assertEquals(0,entry.getLists().size());
    }

    @Test
    public void testEquals() {
        Item entry3 = new RegularItem();
        entry3.setTask("abc");
        entry3.setDue(2019,12,31);
        assertTrue(entry.equals(entry3));
    }

    @Test
    public void testHashCode() {
        Item entry3 = new RegularItem();
        entry3.setTask("abc");
        entry3.setDue(2019,12,31);
        assertEquals(entry.hashCode(),entry3.hashCode());
    }
}

