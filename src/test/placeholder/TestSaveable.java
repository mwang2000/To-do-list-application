package placeholder;

import model.RegularItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class TestSaveable {
    private ArrayList<String> test;

    @BeforeEach
    public void runBefore() {
       test = new ArrayList<>();
        test.add("abc");
        test.add("def");
    }

    @Test
    void testSave() throws IOException, ClassNotFoundException {
        RegularItem e = new RegularItem("");
        e.save();
        test.equals(e.load());
    }

}
