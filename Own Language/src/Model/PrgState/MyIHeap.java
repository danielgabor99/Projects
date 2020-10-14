package Model.PrgState;

import Model.Value.Value;
import com.company.MyException;

import java.util.Collection;
import java.util.Map;

public interface MyIHeap<T, V> {
    public int getFirstFree();

    public void update(T id, V v);

    public void setContent(Map<T, V> garbage);

    public Map<T, V> getContent();

    boolean isKey(int address);

    V getVal(T addr);

    public boolean isDefined(T id);

    public V lookup(T id);
}
