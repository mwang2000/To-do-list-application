package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public interface Saveable {
    // EFFECTS: saves TodoList into a file as strings
    void save() throws IOException;
}


