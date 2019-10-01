package name;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public interface Loadable {
    ArrayList<Entry> load() throws IOException, ClassNotFoundException;
}
