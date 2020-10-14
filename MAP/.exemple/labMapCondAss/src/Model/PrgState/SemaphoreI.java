package Model.PrgState;

import com.company.MyException;

import java.util.Map;

public interface SemaphoreI<T, V> {
    public int getFirstFree();

    public void update(T id, V v);

    public void setContent(Map<T, V> garbage);

    public Map<T, V> getContent();

    public boolean isDefined(T id);

    public V lookup(T id) throws MyException;

    boolean isKey(int address);

    V getVal(T addr);
}
