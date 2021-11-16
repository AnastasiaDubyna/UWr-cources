package structures;
import java.util.ArrayList;

public interface Set {

    Pair search(String k);
    void insert(Pair p);
    void remove(String k);
    void removeAll();
    int count();
}
