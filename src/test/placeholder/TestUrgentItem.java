package placeholder;

import model.Item;
import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.RegularItem;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUrgentItem {
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
    public void testTimeLeft() {
        entry2.setDueDate(2019,10,10);
        assertEquals("There are 4 days until this task is due.", entry2.timeLeft());
    }

}
