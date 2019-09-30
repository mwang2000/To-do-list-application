package name;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public interface Loadable {
    static ArrayList<Entry> load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("file");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Entry> todo = (ArrayList<Entry>) ois.readObject();
        ois.close();
        return todo;
    }
}
