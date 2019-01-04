import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Section {
    private String name;
    private Map<String, String> tree;

    public Section(String name) {
        this.name = name;
        tree = new HashMap<>();
    }

    public void put(String key, String value) {
        this.tree.put(key, value);
    }

    public String getName() {
        return this.name;
    }

    public Set<String> keySet() {
        return this.tree.keySet();
    }

    public String getValue(String key) {
        return this.tree.get(key);
    }

    public boolean containsKey(String key) {
        return this.tree.containsKey(key);
    }
}
