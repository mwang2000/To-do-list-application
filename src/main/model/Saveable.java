package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public interface Saveable {
    void save(Map<String,Item> map) throws IOException;
}


