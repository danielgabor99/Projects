package Model.PrgState;

import com.company.MyException;

import java.util.Map;

public interface MyIDictionary<T, V> {
    public V getValue(T symbol);

    public void update(T id, V v);

    public boolean isDefined(T id);

    public V lookup(T id) throws MyException;

    public Map<T, V> getContent();

    public void remove(T key);
}
