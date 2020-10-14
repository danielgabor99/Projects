package Model.PrgState;

import Model.Value.Value;
import com.company.MyException;

import javax.lang.model.type.NullType;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MyHeap<T, V> implements MyIHeap<T, V> {
    private Map<T, V> heap;
    static AtomicInteger Static = new AtomicInteger();

    public MyHeap() {
        heap = new HashMap<T, V>();
    }

    public int getFirstFree() {
        return Static.incrementAndGet();
    }

    public boolean isDefined(T id) {
        return heap.containsKey(id);
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
        if (address <= Static.get())
            return true;
        return false;
    }

    @Override
    public V lookup(T id) {
        return heap.get(id);
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
