package Model.PrgState;

import Model.Value.Value;

import javax.lang.model.type.NullType;
import java.util.HashMap;
import java.util.Map;

public class MyHeap<T, V> implements MyIHeap<T, V> {
    private Map<T, V> heap;
    int Static = 0;

    public MyHeap() {
        heap = new HashMap<T, V>();
    }

    public int getFirstFree() {
        Static += 1;
        return Static;
    }

    public void setContent(Map<T, V> garbage) {
        heap = garbage;
    }

    public Map<T, V> getContent() {
        return heap;
    }

    public void update(T id, V v) {
        heap.put(id, v);
    }

    public boolean isKey(int address) {
        if (address <= Static)
            return true;
        return false;
    }

    public V getVal(T addr) {
        return heap.get(addr);
    }

    public String toString() {
        String output = "Heap:\n";
        for (Map.Entry<T, V> entry : heap.entrySet())
            output = output + "[" + entry.getKey().toString() + " -> " + entry.getValue().toString() + "]\n";
        return output + "\n";
    }
}
