package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public interface Loadable {
    Map<String,Item> load(Map<String,Item> map) throws IOException, ClassNotFoundException;
}
