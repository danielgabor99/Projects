package Model.PrgState;

import com.company.MyException;

import java.util.HashMap;
import java.util.Map;

public class FileTable<T, V> implements MyIDictionary<T, V> {
    private Map<T, V> dictionary1;

    public FileTable() {
        dictionary1 = new HashMap<T, V>();
    }

    public Map<T, V> getContent() {
        return dictionary1;
    }

    @Override
    public V getValue(T symbol) {
        return dictionary1.get(symbol);
    }

    @Override
    public void update(T id, V v) {
        dictionary1.put(id, v);
    }

    @Override
    public boolean isDefined(T id) {
        return dictionary1.containsKey(id);
    }

    @Override
    public V lookup(T id) throws MyException {
        if (isDefined(id))
            return getValue(id);
        throw new MyException("Variable" + id.toString() + "not defined");
    }

    @Override
    public void remove(T key) {
        dictionary1.remove(key);

    }

    public String toString() {
        String output = "FileTable:\n";
        for (Map.Entry<T, V> entry : dictionary1.entrySet())
            output = output + "[" + entry.getKey().toString() + " -> " + entry.getValue().toString() + "]\n";
        return output;
    }
}
