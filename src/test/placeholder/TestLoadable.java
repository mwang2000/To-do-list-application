package placeholder;

import model.RegularItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLoadable {
    private ArrayList<String> test;

    @BeforeEach
    public void runBefore() {
        test = new ArrayList<>();
        test.add("abc");
        test.add("def");
    }

    @Test
    void testLoad() throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream("file");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(test);
        RegularItem e = new RegularItem("");
        assertEquals(test, e.load());
    }
}
