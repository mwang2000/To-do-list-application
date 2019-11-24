package tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestTodoList {
    private TodoList todo;
    private TodoList crossedOff;
    private RegularItem entry;
    private RegularItem entry2;
    private UrgentItem entry3;

    @BeforeEach
    public void runBefore() {
        crossedOff = new TodoList();
        todo = new TodoList();
        entry = new RegularItem();
        entry.setTask("abc");
        entry.setDue(2019,12,31);
        entry.setStatus("not done");
        entry.addList(todo);
        entry2 = new RegularItem();
        entry2.setTask("def");
        entry3 = new UrgentItem();
        entry3.setTask("ghi");
        entry3.setDue(2019,12,1);
    }

    @Test
    public void testGetList() {
        assertTrue(todo.getList().contains(entry));
    }

    @Test
    public void testMoveItemEmptyCrossedOff() {
        todo.addItem(entry);
        todo.addItem(entry2);
        todo.addItem(entry3);
        todo.moveItem(entry,crossedOff);
        assertEquals(2, todo.listSize());
        assertTrue(todo.listContains(entry2));
        assertEquals(1, crossedOff.listSize());
        assertTrue(crossedOff.listContains(entry));
    }

    @Test
    public void testMoveItemNotEmptyCrossedOff() {
        todo.addItem(entry);
        todo.addItem(entry2);
        crossedOff.addItem(entry3);
        todo.moveItem(entry2,crossedOff);
        assertEquals(1, todo.listSize());
        assertTrue(todo.listContains(entry));
        assertEquals(2, crossedOff.listSize());
        assertTrue(crossedOff.listContains(entry2));
    }

//    @Test
//    public void testMoveItemUrgentItem() throws IOException {
//        todo.addItem();
//        crossedOff.addItem(entry);
//        crossedOff.moveItem(todo);
//        assertEquals(0,todo.listSize());
//        assertEquals(2,crossedOff.listSize());
//        assertTrue(crossedOff.listContains(entry3));
//    }

//    @Test
//    public void testReturnTodoEmpty() {
//        assertEquals("Nothing in the to do list.",
//                todo.returnTodoList());
//    }
//
//    @Test
//    public void testReturnTodoUrgent() {
//        todo.addItem(entry3);
//        entry3.setDue(2019,12,31);
//        assertEquals("0. ghi due:2019-12-31 not done Keyword: There are 57 days until this task is due.",
//                todo.returnTodoList());
//    }

//    @Test
//    public void testReturnTodoRegular() throws IOException {
//        todo.addItem(entry);
//        entry.setDue(2019,12,31);
//        assertEquals("0. abc due:2019-12-31 not done Keyword: a",todo.returnTodoList());
//    }
//
//    @Test
//    public void testReturnTodoException() throws IOException {
//        todo.addItem(entry3);
//        entry3.setDue(2019,10,10);
//        assertEquals("0. ghi due:2019-10-10 not done Keyword: \nThis item is overdue!",
//                todo.returnTodoList());
//    }

    @Test
    public void testReturnCrossedOffEmpty() {
        assertEquals("\nNothing in the crossed off list.",crossedOff.returnCrossedOffList());
    }

    @Test
    public void testReturnCrossedOffMultiple() {
        crossedOff.addItem(entry);
        crossedOff.addItem(entry3);
        entry.setStatus("done");
        entry3.setStatus("done");
        entry.setDue(2019,10,31);
        entry3.setDue(2019,10,10);
        assertEquals("The crossed off list is\nabc due:2019-10-31 done\nghi due:2019-10-10 done",
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

//    @Test
//    void testLoadTodoUrgent() throws IOException {
//        todoMap.put("b",entry3);
//        todo.save(todoMap);
//        todo.loadToList();
//        assertTrue(todo.listContains(entry3));
//    }

    @Test
    void testSaveLoadUrgent() throws IOException {
        todo.addItem(entry3);
        todo.save();
        assertTrue(todo.load().listContains(entry3));
    }

    @Test
    void testSaveLoadRegular() throws IOException {
        todo.addItem(entry);
        todo.save();
        assertTrue(todo.load().listContains(entry));
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
        partsOfLine.add("abc");
        partsOfLine.add("2019");
        partsOfLine.add("12");
        partsOfLine.add("31");
        partsOfLine.add("not done");
        Item item = new RegularItem();
        item.retrieveItemFields(partsOfLine,item);
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