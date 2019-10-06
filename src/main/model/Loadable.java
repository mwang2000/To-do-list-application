package model;

import java.io.IOException;
import java.util.ArrayList;

public interface Loadable {
    ArrayList<Item> load() throws IOException, ClassNotFoundException;
}
