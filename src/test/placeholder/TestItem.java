package placeholder;

import model.Item;
import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.RegularItem;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestItem {
    private ArrayList<Item> testTodo;
    private RegularItem entry;
    private UrgentItem entry2;

    @BeforeEach
    public void runBefore() {
        entry = new RegularItem("abc");
        entry2 = new UrgentItem("def");
        testTodo = new ArrayList<>();
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
        entry.setTask("xyz");
        entry2.setTask("abc");
        assertEquals("xyz", entry.getTask());
        assertEquals("abc", entry2.getTask());
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
        entry.setDueDate(2019,10,10);
        assertEquals(LocalDate.of(2019,10,10),entry.getDueDate());
    }

    @Test
    public void testGetNumber() {
        testTodo.add(entry);
        entry.setNumber(1);
        entry2.setNumber(2);
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
        entry.setDueDate(2020,12,31);
        assertEquals(LocalDate.of(2020,12,31), entry.getDueDate());
    }

    @Test
    public void testTodoGetRegularItem() {
        testTodo.add(entry);
        entry.setTask("abc");
        entry.setDueDate(2019, 10, 10);
        assertEquals(entry.getNumber() + ". " + entry.getTask() + " due:" + entry.getDueDate() + " "
                + entry.getStatus() + " (to do)", entry.todoGetItem());
    }

    @Test
    public void testTodoGetUrgentItem() {
        testTodo.add(entry2);
        entry2.setTask("abc");
        entry2.setDueDate(2020, 12, 31);
        assertEquals(entry2.getNumber() + ". " + entry2.getTask() + " due:" + entry2.getDueDate() + " "
                + entry2.getStatus() + " (crossed off)", entry2.crossedOffGetItem());
    }
}

