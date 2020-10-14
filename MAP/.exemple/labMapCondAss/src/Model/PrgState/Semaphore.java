package Model.PrgState;

import com.company.MyException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Semaphore<T, V> implements SemaphoreI<T, V> {

    private Map<T, V> sem;
    static AtomicInteger Static = new AtomicInteger();

    public Semaphore() {
        sem = new HashMap<T, V>();
    }

    @Override
    public int getFirstFree() {
        return Static.incrementAndGet();
    }

    @Override
    public void update(T id, V v) {
        sem.put(id, v);
    }

    @Override
    public void setContent(Map garbage) {

    }

    @Override
    public Map getContent() {
        return sem;
    }

    @Override
    public boolean isDefined(T id) {
        return sem.containsKey(id);
    }

    @Override
    public V lookup(T id) throws MyException {
        if (isDefined(id))
            return sem.get(id);
        throw new MyException("Variable" + id.toString() + "not defined");
    }


    @Override
    public boolean isKey(int address) {
        return false;
    }

    @Override
    public Object getVal(Object addr) {
        return null;
    }
}
