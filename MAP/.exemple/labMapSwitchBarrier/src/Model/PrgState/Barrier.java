package Model.PrgState;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Barrier<T, V> implements IBarrier<T, V> {
    private Map<T, V> barrier;
    static AtomicInteger Static = new AtomicInteger();

    public Barrier() {
        barrier = new HashMap<T, V>();
    }

    public int getFirstFree() {
        return Static.incrementAndGet();
    }

    public boolean isDefined(T id) {
        return barrier.containsKey(id);
    }

    public void setContent(Map<T, V> garbage) {
        barrier = garbage;
    }

    public Map<T, V> getContent() {
        return barrier;
    }

    public void update(T id, V v) {
        barrier.put(id, v);
    }

    public boolean isKey(int address) {
        if (address <= Static.get())
            return true;
        return false;
    }

    @Override
    public V lookup(T id) {
        return barrier.get(id);
    }

    public V getVal(T addr) {
        return barrier.get(addr);
    }

    public String toString() {
        String output = "Heap:\n";
        for (Map.Entry<T, V> entry : barrier.entrySet())
            output = output + "[" + entry.getKey().toString() + " -> " + entry.getValue().toString() + "]\n";
        return output + "\n";
    }
}
