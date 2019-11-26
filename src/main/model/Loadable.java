package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public interface Loadable {
    // EFFECTS: loads from file into a new TodoList
    TodoList load() throws IOException, ClassNotFoundException;
}
