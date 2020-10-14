package Model.PrgState;

import com.company.MyException;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<T, V> implements MyIDictionary<T, V> {
    private Map<T, V> dictionary;

    public MyDictionary() {
        dictionary = new HashMap<T, V>();
    }

    public Map<T, V> getContent() {
        return dictionary;
    }

    @Override
    public V getValue(T symbol) {
        return dictionary.get(symbol);
    }

    @Override
    public void update(T id, V v) {
        dictionary.put(id, v);
    }

    @Override
    public boolean isDefined(T id) {
        return dictionary.containsKey(id);
    }

    @Override
    public V lookup(T id) throws MyException {
        if (isDefined(id))
            return getValue(id);
        throw new MyException("Variable" + id.toString() + "not defined");
    }

    public String toString() {
        String output = "SymbTable:\n";
        for (Map.Entry<T, V> entry : dictionary.entrySet())
            output = output + "[" + entry.getKey().toString() + " -> " + entry.getValue().toString() + "]\n";
        return output;
    }

    @Override
    public void remove(T key) {
        dictionary.remove(key);
    }
}
