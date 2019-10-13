package tests;

import model.Item;
import model.RegularItem;
import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestExceptions {
    private ArrayList<Item> testTodo;
    private RegularItem entry;
    private RegularItem entry2;
    private UrgentItem entry3;
    private UrgentItem entry4;
    private int MAX_TODO_SIZE = 10;

    @BeforeEach
    public void runBefore() {
        entry = new RegularItem("abc");
        entry2 = new RegularItem("aaa");
        entry3 = new UrgentItem("def");
        entry4 = new UrgentItem("ddd");
        testTodo = new ArrayList<>();
    }

//    @Test
//    public void testTooManyThingsToDoException() {
//        for (int i = 0; i <= MAX_TODO_SIZE - 1; i++) {
//            testTodo.add(entry);
//        }
//        entry2.addRegularItem
//    }
}
