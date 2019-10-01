package name;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public interface Saveable {
    void save() throws IOException;
}


