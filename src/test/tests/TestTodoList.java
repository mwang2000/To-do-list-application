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
    private TodoList todo;
    private TodoList testCrossedOff;
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
        testCrossedOff = new TodoList();
        todoMap = new HashMap<>();
        todoMap.put("a",entry);
        todo = new TodoList();
        examPrep = new TodoList();
    }

    @Test
    public void testUpdateTodo() {
        TodoList.updateTodo(todoMap);
        assertTrue(todo.todoListContains(entry));
        assertEquals(1,todo.todoListSize());
    }

//    @Test
//    public void testAddExamPrep() {
//        Item e = new RegularItem();
//        e.setTask(entry.getTask());
//        e.setDue(entry.getDue().getYear(),entry.getDue().getMonthValue(),entry.getDue().getDayOfMonth());
//        examPrep.addExamPrep(entry);
//        assertTrue(examPrep.todoListContains(e));
//        assertTrue(e.getList() == examPrep);
//        Item i = new UrgentItem();
//        e.setTask(entry3.getTask());
//        e.setDue(entry3.getDue().getYear(),entry3.getDue().getMonthValue(),entry3.getDue().getDayOfMonth());
//        examPrep.addExamPrep(entry3);
//        assertTrue(examPrep.todoListContains(i));
//        assertEquals(2,examPrep.todoListSize());
//    }

//    @Test
//    public void testMoveItemEmptyCrossedOff() {
//        todoMap.put("a",entry);
//        todoMap.put("b",entry2);
//        todo.updateTodo(todoMap);
//        testCrossedOff.moveItem("a",todoMap);
//        entry.setNumber(0);
//        assertEquals(1, todo.todoListSize());
//        assertTrue(todo.todoListContains(entry2));
//        assertEquals(1,testCrossedOff.todoListSize());
//        assertTrue(testCrossedOff.todoListContains(entry));
//    }

//    @Test
//    public void testMoveItemNotEmptyCrossedOff() {
//        todoMap.put("a",entry);
//        todoMap.put("b",entry2);
//        testCrossedOff.addTodo(entry3);
//        testCrossedOff.moveItem("b",todoMap);
//        entry2.setNumber(0);
//        assertEquals(1, todo.todoListSize());
//        assertTrue(todo.todoListContains(entry));
//        assertEquals(2,testCrossedOff.todoListSize());
//        assertTrue(testCrossedOff.todoListContains(entry2));
//    }

//    @Test
//    public void testMoveItemUrgentItem() {
//        todoMap.put("c",entry3);
//        testCrossedOff.addTodo(entry);
//        testCrossedOff.moveItem("c",todoMap);
//        entry3.setNumber(0);
//        assertEquals(0, todo.todoListSize());
//        assertEquals(2,testCrossedOff.todoListSize());
//        assertTrue(testCrossedOff.todoListContains(entry3));
//    }

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
        TodoList.addCrossedOff(entry);
        TodoList.addCrossedOff(entry3);
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