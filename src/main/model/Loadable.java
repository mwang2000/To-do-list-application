package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public interface Loadable {
    TodoList load() throws IOException, ClassNotFoundException;
}
