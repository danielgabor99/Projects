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

    public synchronized boolean isDefined(T id) {
        return barrier.containsKey(id);
    }

    public synchronized void update(T id, V v) {
        barrier.put(id, v);
    }

    public synchronized V lookup(T id) {
        return barrier.get(id);
    }

    public V getVal(T addr) {
        return barrier.get(addr);
    }

    public String toString() {
        String output = "Barrier:\n";
        for (Map.Entry<T, V> entry : barrier.entrySet())
            output = output + "[" + entry.getKey().toString() + " -> " + entry.getValue().toString() + "]\n";
        return output + "\n";
    }
}
