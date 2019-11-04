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
    private TodoList crossedOff;
    private TodoList examPrep;
    private RegularItem entry;
    private RegularItem entry2;
    private UrgentItem entry3;
    private Map<String,Item> todoMap;

    @BeforeEach
    public void runBefore() {
        entry = new RegularItem();
        entry.setKeyword("a");
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
        todo.updateTodo(todoMap);
        assertTrue(todo.listContains(entry));
        assertEquals(1,todo.listSize());
    }

//    @Test
//    public void testAddExamPrepRegular() {
//        Item e = new RegularItem();
//        e.setTask(entry.getTask());
//        e.setDue(entry.getDue().getYear(), entry.getDue().getMonthValue(), entry.getDue().getDayOfMonth());
//        examPrep.addExamPrep(entry);
//        assertTrue(TodoList.examPrep.contains(entry));
//        assertTrue(entry.getList().equals(examPrep));
//        assertEquals(1, TodoList.examPrep.size());
//    }

//    @Test
//    public void testAddExamPrepUrgent() {
//        Item i = new UrgentItem();
//        i.setTask(entry3.getTask());
//        i.setDue(entry3.getDue().getYear(),entry3.getDue().getMonthValue(),entry3.getDue().getDayOfMonth());
//        examPrep.addExamPrep(entry3);
//        assertTrue(TodoList.examPrep.contains(entry3));
//        assertTrue(entry3.getList().equals(examPrep));
//        assertEquals(1, TodoList.examPrep.size());
//    }
//
//    @Test
//    public void testAddExamPrepAlreadyIncluded() {
//        TodoList.examPrep.add(entry);
//        examPrep.addExamPrep(entry);
//        assertTrue(TodoList.examPrep.contains(entry));
//        assertEquals(1, TodoList.examPrep.size());
//    }

    @Test
    public void testMoveItemEmptyCrossedOff() {
        todoMap.put("a",entry);
        todoMap.put("b",entry2);
        todoMap.put("c",entry3);
        todo.updateTodo(todoMap);
        crossedOff.moveItem("a",todoMap,todo);
        entry.setNumber(0);
        assertEquals(2, todo.listSize());
        assertTrue(todo.listContains(entry2));
        assertEquals(1, crossedOff.listSize());
        assertTrue(crossedOff.listContains(entry));
    }

    @Test
    public void testMoveItemNotEmptyCrossedOff() {
        todoMap.put("a",entry);
        todoMap.put("b",entry2);
        todo.updateTodo(todoMap);
        crossedOff.addItem(entry3);
        crossedOff.moveItem("b",todoMap,todo);
        entry2.setNumber(0);
        assertEquals(1, todo.listSize());
        assertTrue(todo.listContains(entry));
        assertEquals(2, crossedOff.listSize());
        assertTrue(crossedOff.listContains(entry2));
    }

    @Test
    public void testMoveItemUrgentItem() {
        todoMap.remove("a",entry);
        todoMap.put("c",entry3);
        todo.updateTodo(todoMap);
        crossedOff.addItem(entry);
        crossedOff.moveItem("c",todoMap,todo);
        entry3.setNumber(0);
        assertEquals(0,todo.listSize());
        assertEquals(2,crossedOff.listSize());
        assertTrue(crossedOff.listContains(entry3));
    }

    @Test
    public void testReturnTodoEmpty() {
        assertEquals("Nothing in the to do list.",
                todo.returnTodoList());
    }

    @Test
    public void testReturnTodoUrgent() {
        todo.addItem(entry3);
        entry3.setDue(2019,12,31);
        assertEquals("0. ghi due:2019-12-31 not done Keyword: There are 57 days until this task is due.",
                todo.returnTodoList());
    }

    @Test
    public void testReturnTodoRegular() {
        todoMap.put("a",entry);
        entry.setDue(2019,12,31);
        todo.updateTodo(todoMap);
        assertEquals("0. abc due:2019-12-31 not done Keyword: a",todo.returnTodoList());
    }

    @Test
    public void testReturnTodoException() {
        todoMap.remove("a");
        todoMap.put("c",entry3);
        entry3.setDue(2019,10,10);
        todo.updateTodo(todoMap);
        assertEquals("0. ghi due:2019-10-10 not done Keyword: \nThis item is overdue!",
                todo.returnTodoList());
    }

    @Test
    public void testReturnCrossedOffEmpty() {
        assertEquals("\nNothing in the crossed off list.",crossedOff.returnCrossedOffList());
    }

    @Test
    public void testReturnCrossedOffMultiple() {
        crossedOff.addItem(entry);
        crossedOff.addItem(entry3);
        entry.setStatus("done");
        entry.setKeyword("a");
        entry3.setStatus("done");
        entry.setDue(2019,10,31);
        entry3.setDue(2019,10,10);
        assertEquals("\nThe crossed off list is\nabc due:2019-10-31 done\nghi due:2019-10-10 done",
                crossedOff.returnCrossedOffList());
    }

//    @Test
//    public void testReturnExamPrepEmpty() {
//        assertEquals("\nNothing in the exam prep list.",TodoList.returnExamPrep());
//    }

//    @Test
//    public void testReturnExamPrepNotEmpty() {
//        TodoList.examPrep.add(entry);
//        TodoList.examPrep.add(entry3);
//        assertEquals("\nThe exam prep list is:\nabc 2019-12-31\nghi 2019-12-01",TodoList.returnExamPrep());
//    }
//
//    @Test
//    public void testRemoveExamPrep() {
//        examPrep.addExamPrep(entry);
//        examPrep.removeExamPrep(entry);
//        assertEquals(0,TodoList.examPrep.size());
//    }

    @Test
    void testSaveLoad() throws IOException {
        todoMap.put("b",entry3);
        todo.save(todoMap);
        assertEquals(todoMap,todo.load(todoMap));
    }

//    @Test
//    public void testSaveExamPrep() {
//        TodoList.examPrep.add(entry);
//        TodoList.examPrep.add(entry3);
//        assertEquals("abc_2019_12_31\nghi_2019_12_1",TodoList.saveExamPrep());
//    }
//
//    @Test
//    public void testSaveExamPrepOverdue() {
//        TodoList.examPrep.add(entry3);
//        entry3.setDue(2019,10,10);
//        assertEquals("ghi_2019_10_10",TodoList.saveExamPrep());
//    }

    @Test
    public void testRetrieveItemFields() {
        ArrayList<String> partsOfLine = new ArrayList<>();
        partsOfLine.add("a");
        partsOfLine.add("0");
        partsOfLine.add("abc");
        partsOfLine.add("2019");
        partsOfLine.add("12");
        partsOfLine.add("31");
        partsOfLine.add("not done");
        Item item = new RegularItem();
        item.retrieveItemFields(partsOfLine,item);
        assertEquals( entry.getKeyword(), item.getKeyword());
        assertEquals( entry.getNumber(), item.getNumber());
        assertEquals( entry.getTask(), item.getTask());
        assertEquals( entry.getDue().getYear(), item.getDue().getYear());
        assertEquals( entry.getDue().getMonthValue(), item.getDue().getMonthValue());
        assertEquals( entry.getDue().getDayOfMonth(), item.getDue().getDayOfMonth());
        assertEquals( entry.getStatus(), item.getStatus());

    }

    @Test
    public void testSplitOnUnderscore() {
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