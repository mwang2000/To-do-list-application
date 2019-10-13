package tests;

import model.Item;
import model.RegularItem;
import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLoadable {
    private ArrayList<Item> test;
    private Item entry;
    private Item entry2;

    @BeforeEach
    public void runBefore() {
        test = new ArrayList<>();
        entry = new RegularItem();
        entry.setTask("abc");
        entry2 = new UrgentItem();
        entry2.setTask("def");
        test.add(entry);
        test.add(entry2);
    }

    @Test
    void testLoad() throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream("file");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(test);
        RegularItem e = new RegularItem();
        assertEquals(test, e.load());
    }
}
