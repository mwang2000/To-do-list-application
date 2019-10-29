package tests;

import model.Item;
import model.TodoList;
import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.RegularItem;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestTodoList {
    private TodoList todo;
    private TodoList crossedOff;
    private TodoList examPrep;
    private RegularItem entry;
    private RegularItem entry2;
    private UrgentItem entry3;
    private Map<String,Item> todoMap;

    @BeforeEach
    public void runBefore() {
        entry = new RegularItem();
        entry.setTask("abc");
        entry.setDue(2019,12,31);
        entry.setStatus("not done");
        entry2 = new RegularItem();
        entry2.setTask("def");
        entry3 = new UrgentItem();
        entry3.setTask("ghi");
        entry3.setDue(2019,12,1);
        crossedOff = new TodoList();
        todoMap = new HashMap<>();
        todoMap.put("a",entry);
        todo = new TodoList();
        examPrep = new TodoList();
    }

    @Test
    public void testUpdateTodo() {
        TodoList.updateTodo(todoMap);
        assertTrue(TodoList.todo.contains(entry));
        assertEquals(1,TodoList.todo.size());
    }

//    @Test
//    public void testAddExamPrep() {
//        Item e = new RegularItem();
//        e.setTask(entry.getTask());
//        e.setDue(entry.getDue().getYear(),entry.getDue().getMonthValue(),entry.getDue().getDayOfMonth());
//        examPrep.addExamPrep(entry);
//        assertTrue(TodoList.examPrep.contains(entry));
//        assertTrue(entry.getList().equals(examPrep));
//        Item i = new UrgentItem();
//        i.setTask(entry3.getTask());
//        i.setDue(entry3.getDue().getYear(),entry3.getDue().getMonthValue(),entry3.getDue().getDayOfMonth());
//        examPrep.addExamPrep(entry3);
//        assertTrue(TodoList.examPrep.contains(entry3));
//        assertEquals(2, TodoList.examPrep.size());
//    }

    @Test
    public void testMoveItemEmptyCrossedOff() {
        todoMap.put("a",entry);
        todoMap.put("b",entry2);
        todoMap.put("c",entry3);
        TodoList.updateTodo(todoMap);
        crossedOff.moveItem("a",todoMap, crossedOff);
        entry.setNumber(0);
        assertEquals(2, TodoList.todo.size());
        assertTrue(TodoList.todo.contains(entry2));
        assertEquals(1, TodoList.crossedOff.size());
        assertTrue(TodoList.crossedOff.contains(entry));
    }

    @Test
    public void testMoveItemNotEmptyCrossedOff() {
        todoMap.put("a",entry);
        todoMap.put("b",entry2);
        TodoList.updateTodo(todoMap);
        TodoList.crossedOff.add(entry3);
        crossedOff.moveItem("b",todoMap, crossedOff);
        entry2.setNumber(0);
        assertEquals(1, TodoList.todo.size());
        assertTrue(TodoList.todo.contains(entry));
        assertEquals(2, TodoList.crossedOff.size());
        assertTrue(TodoList.crossedOff.contains(entry2));
    }

    @Test
    public void testMoveItemUrgentItem() {
        todoMap.remove("a",entry);
        todoMap.put("c",entry3);
        TodoList.updateTodo(todoMap);
        TodoList.crossedOff.add(entry);
        crossedOff.moveItem("c",todoMap,crossedOff);
        entry3.setNumber(0);
        assertEquals(0,TodoList.todo.size());
        assertEquals(2,TodoList.crossedOff.size());
        assertTrue(TodoList.crossedOff.contains(entry3));
    }

    @Test
    public void testReturnTodoEmpty() {
        assertEquals("Nothing in the to do list.",
                TodoList.returnTodoList());
    }

    @Test
    public void testReturnTodoUrgent() {
        todo.addTodo(entry3);
        entry3.setDue(2019,12,31);
        assertEquals("0. ghi due:2019-12-31 not done Keyword: \nThere are 64 days until this task is due.",
                TodoList.returnTodoList());
    }

    @Test
    public void testReturnTodoRegular() {
        todoMap.put("a",entry);
        entry.setDue(2019,12,31);
        TodoList.updateTodo(todoMap);
        assertEquals("0. abc due:2019-12-31 not done Keyword: ",TodoList.returnTodoList());
    }

    @Test
    public void testReturnTodoException() {
        todoMap.remove("a");
        todoMap.put("c",entry3);
        entry3.setDue(2019,10,10);
        TodoList.updateTodo(todoMap);
        assertEquals("0. ghi due:2019-10-10 not done Keyword: \nThis item is overdue!",
                TodoList.returnTodoList());
    }

    @Test
    public void testReturnCrossedOffEmpty() {
        assertEquals("\nNothing in the crossed off list.",TodoList.returnCrossedOffList());
    }

    @Test
    public void testReturnCrossedOffMultiple() {
        TodoList.crossedOff.add(entry);
        TodoList.crossedOff.add(entry3);
        entry.setStatus("done");
        entry3.setStatus("done");
        entry.setDue(2019,10,31);
        entry3.setDue(2019,10,10);
        assertEquals("\nThe crossed off list is\nabc due:2019-10-31 done\nghi due:2019-10-10 done\n",
                TodoList.returnCrossedOffList());
    }

    @Test
    void testSaveLoad() throws IOException {
        todoMap.put("b",entry3);
        todo.save();
        assertEquals(todoMap,todo.load(todoMap));
    }
}