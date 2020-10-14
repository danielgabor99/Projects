package Model.PrgState;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Latch<T, V> implements ILatch<T, V> {
    private Map<T, V> latch;
    static AtomicInteger Static = new AtomicInteger();

    public Latch() {
        latch = new HashMap<T, V>();
    }

    public int getFirstFree() {
        return Static.incrementAndGet();
    }

    public boolean isDefined(T id) {
        return latch.containsKey(id);
    }

    public void setContent(Map<T, V> garbage) {
        latch = garbage;
    }

    public Map<T, V> getContent() {
        return latch;
    }

    public void update(T id, V v) {
        latch.put(id, v);
    }

    public boolean isKey(int address) {
        if (address <= Static.get())
            return true;
        return false;
    }

    @Override
    public V lookup(T id) {
        return latch.get(id);
    }

    public V getVal(T addr) {
        return latch.get(addr);
    }

    public String toString() {
        String output = "Heap:\n";
        for (Map.Entry<T, V> entry : latch.entrySet())
            output = output + "[" + entry.getKey().toString() + " -> " + entry.getValue().toString() + "]\n";
        return output + "\n";
    }
}
